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
    public static ArrayList<RecipeTitle> getListRecipes(String name) {
        name = name.replaceAll(" ", "-");
        name = Normalizer.normalize(name, Normalizer.Form.NFD);
        name = name.replaceAll("\\p{M}", "");
        String url = "https://www.dienmayxanh.com/vao-bep/tim-kiem/" + name;
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setRedirectEnabled(false);
        ArrayList<RecipeTitle> recipeList = new ArrayList<>();
        try {
            HtmlPage page = webClient.getPage(url);
            DomNodeList<DomNode> recipesList = page.querySelectorAll("body > section > div > ul > li");
            for (DomNode recipe : recipesList) {
                String title = recipe.querySelector("div > a > strong").getTextContent().trim();
                String link = "https://www.dienmayxanh.com" + recipe.querySelector("a").getAttributes().getNamedItem("href").getNodeValue();
                String totalView = recipe.querySelector("div > div > small:nth-child(2)").getTextContent().trim();
                recipeList.add(new RecipeTitle(title, link, totalView));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        webClient.close();
        return recipeList;
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
//            DomNode pageNode = page.querySelector("body > section > div.detail");
//            System.out.println(pageNode.asNormalizedText().replaceAll("\\*", ""));

            DomNode preparationNode = page.querySelector("#tongquan > ul > li:nth-child(1) > span");
            DomNode processingNode = page.querySelector("#tongquan > ul > li:nth-child(2) > span");
            DomNode difficultyNode = page.querySelector("#tongquan > ul > li:nth-child(3) > span");

            recipe.setTitle(recipeTitle);
            recipe.setPreparationTime(preparationNode.getTextContent().trim());
            recipe.setProcessingTime(processingNode.getTextContent().trim());
            recipe.setDifficulty(difficultyNode.getTextContent().trim());

            DomNodeList<DomNode> ingredients = page.querySelectorAll("#chuanbi > div > span");
            for (DomNode ingredient : ingredients) {
                recipe.addIngredient(getIngredient(ingredient.getTextContent().trim()));
            }

            DomNodeList<DomNode> steps = page.querySelectorAll("#step > div > ul > li");
            System.out.println(steps.size());
            for (DomNode stepNode : steps) {
                Step step = new Step();

                System.out.println(stepNode.asXml());

                step.setNumber(Integer.parseInt(stepNode.querySelector("label").getTextContent().trim()));

                step.setStepTitle(stepNode.querySelector("div > h3").getTextContent().trim());

                DomNodeList<DomNode> stepDescriptionNode = stepNode.querySelectorAll("div > p");
                StringBuilder desc = new StringBuilder();
                for (DomNode node : stepDescriptionNode) {
                    desc.append(node.getTextContent().trim()).append("\n");
                }
                step.setStepDescription(desc.toString());

//                DomNodeList<DomNode> gallery = stepNode.querySelectorAll("div > div > div");
//                for (DomNode node : gallery) {
//                    DomNode imgNode = node.querySelector("img");
//                    String imgURL = imgNode.getAttributes().getNamedItem("data-src").getNodeValue();
//                    step.addImageURL(imgURL);
//                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        webClient.close();
        return recipe;
    }

    public static void main(String[] args) {
        ArrayList<RecipeTitle> recipeList = getListRecipes("thịt");
        for (RecipeTitle recipeTitle : recipeList) {
            System.out.println(recipeTitle);
        }
        System.out.println(getRecipe(recipeList.get(1)));
    }
}
