package data.scraper;

import data.type.Ingredient;
import data.type.Recipe;
import data.type.RecipeTitle;
import data.type.Step;
import org.htmlunit.WebClient;
import org.htmlunit.html.DomNode;
import org.htmlunit.html.DomNodeList;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;

public class GetRecipesByName {
    public static String getImageURL(String text) {
        try {
            text = text.substring(text.indexOf("src=\""));
            if (text.contains(".jpg")) {
                return text.substring(5, text.indexOf(".jpg") + 4);
            }
            else if (text.contains(".png")) {
                return text.substring(5, text.indexOf(".png") + 4);
            }
            else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    public static String getPageURL(String text) {
        text = text.substring(text.indexOf("href=\"") + 6);
        text = text.substring(0, text.indexOf("\">"));
        return "https://www.dienmayxanh.com" + text;
    }
    public static String url;
    public static ArrayList<RecipeTitle> getListRecipes(String name) {
        name = name.replaceAll(" ", "-");
        name = Normalizer.normalize(name, Normalizer.Form.NFD);
        name = name.replaceAll("\\p{M}", "");
        url = "https://www.dienmayxanh.com/vao-bep/tim-kiem/" + name;
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setRedirectEnabled(false);
        ArrayList<RecipeTitle> recipeList = new ArrayList<>();
        try {
            HtmlPage page = webClient.getPage(url);
            DomNodeList<DomNode> recipesList = page.querySelectorAll("body > section > div > ul > li");
            for (DomNode recipe : recipesList) {
                String title = recipe.querySelector("div > a > strong").getTextContent().trim();
                if (title.charAt(0) != 'C')
                    continue;
                String link = getPageURL(recipe.querySelector("a").asXml());
                String totalView = recipe.querySelector("div > div > small:nth-child(2)").getTextContent().trim();
                recipeList.add(new RecipeTitle(getRecipeName(title), link, totalView));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        webClient.close();
        return recipeList;
    }
    static String[] listWords = {"thơm", "ngon", "giòn", "cực", "đậm", "đơn", "chuẩn", "bằng", "siêu"};
    public static String getRecipeName(String recipe)  {
        for (String listWord : listWords)
            if (recipe.contains(listWord)) {
                recipe = recipe.substring(0, recipe.indexOf(listWord));
            }
//        if (recipe.contains("Cách làm") || recipe.contains("Cách nấu"))
        recipe = recipe.substring(recipe.indexOf(" ") + 1);
        recipe = recipe.substring(recipe.indexOf(" ") + 1);
        StringBuilder stringBuilder = new StringBuilder(recipe);
        stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
        return stringBuilder.toString();
    }
    public static Ingredient getIngredient(String text) {
        text = text.replaceAll("\\s{2,}", " ").trim();
        text = text.replaceAll("\n", "");
        String ingredientName = "";
        String ingredientUnit = "";
        String ingredientQuanity = "";
        int numberIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= '0' && text.charAt(i) <= '9') {
                ingredientName = text.substring(0, i - 1);
                numberIndex = i;
                break;
            }
        }
        for (int i = text.length() - 1; i >= 0; i--) {
            if (text.charAt(i) >= '0' && text.charAt(i) <= '9') {
                ingredientUnit = text.substring(i + 2);
                ingredientQuanity = text.substring(numberIndex, i + 1);
                break;
            }
        }
        return new Ingredient(ingredientName, ingredientQuanity, ingredientUnit);
    }
    public static Recipe getRecipe(RecipeTitle recipeTitle) {
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setRedirectEnabled(false);
        Recipe recipe = new Recipe();
        try {
            HtmlPage page = webClient.getPage(recipeTitle.getUrl());

            DomNode preparationNode = page.querySelector("#tongquan > ul > li:nth-child(1) > span");
            DomNode processingNode = page.querySelector("#tongquan > ul > li:nth-child(2) > span");
            DomNode difficultyNode = page.querySelector("#tongquan > ul > li:nth-child(3) > span");

            recipe.setTitle(recipeTitle);
            recipe.setImageURL(getImageURL(page.querySelector("#tongquan > div.video > img").asXml()));
            if (preparationNode != null)
                recipe.setPreparationTime(preparationNode.getTextContent().trim());
            if (processingNode!= null)
                recipe.setProcessingTime(processingNode.getTextContent().trim());
            if (difficultyNode!= null)
                recipe.setDifficulty(difficultyNode.getTextContent().trim());

            DomNodeList<DomNode> ingredients = page.querySelectorAll("#chuanbi > div > span");
            for (DomNode ingredient : ingredients) {
                recipe.addIngredient(getIngredient(ingredient.getTextContent().trim()));
            }

            DomNodeList<DomNode> steps = page.querySelectorAll("#step > div > ul > li");
//            System.out.println(steps.size());
            for (DomNode stepNode : steps) {
                Step step = new Step();


                step.setNumber(Integer.parseInt(stepNode.querySelector("label").getTextContent().trim()));

                step.setStepTitle(stepNode.querySelector("div > h3").getTextContent().trim());

                DomNodeList<DomNode> stepDescriptionNode = stepNode.querySelectorAll("div > p");
                StringBuilder desc = new StringBuilder();
                for (DomNode node : stepDescriptionNode) {
                    if (node.getTextContent().contains("Mách")) continue;
                    if (node.getTextContent().contains("Cách")) continue;
                    if (node.getTextContent().contains("Xem chi tiết")) continue;
                    desc.append(node.getTextContent().trim()).append("\n");
                }
                step.setStepDescription(desc.toString());

                DomNodeList<DomNode> gallery = stepNode.querySelectorAll("div > div > div");
                for (DomNode node : gallery) {
                    DomNode imgNode = node.querySelector("img");
                    String imgURL = getImageURL(imgNode.asXml());
                    step.addImageURL(imgURL);
                }
                recipe.addStep(step);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            return null;
        }
        webClient.close();
        return recipe;
    }

    public static void main(String[] args) {
        ArrayList<RecipeTitle> recipeList = getListRecipes("thịt kho tàu");
        for (RecipeTitle recipeTitle : recipeList) {
            System.out.println(recipeTitle);
        }
        for (RecipeTitle recipeTitle : recipeList) {
            System.out.println(getRecipe(recipeTitle));
        }
    }
}
