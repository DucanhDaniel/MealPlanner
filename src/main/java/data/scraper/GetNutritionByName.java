package data.scraper;

import data.type.FoodNutriments;
import org.htmlunit.FailingHttpStatusCodeException;
import org.htmlunit.WebClient;
import org.htmlunit.html.DomNode;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;

public class GetNutritionByName {
    public static double getDouble(String text) {
        text = text.replaceAll(",", ".");
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= '0' && text.charAt(i) <= '9') {
                text = text.substring(i);
            }
        }
        String number = text.substring(0, (!text.contains(" ") ? text.length() : text.indexOf(" ")));
        try {
            return Double.parseDouble(number);
        }
        catch (Exception e) {
            return 0;
        }
    }
    public static String getUnit(String text) {
        for (int i = text.length() - 1; i >= 0; i--)
            if (text.charAt(i) == ' ') {
                return text.substring(i + 1);
            }
        return null;
    }
    public static String getTextContentEvenNull(DomNode node) {
        if (node == null) return "null";
        return node.getTextContent();
    }
    public static boolean hasError = false;
    public static FoodNutriments get(String name) {
        name = name.replaceAll(" ", "+");
        String url = "https://www.google.com/search?q=giá+trị+dinh+dưỡng+của+" + name;
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        FoodNutriments foodNutriments = null;
        try {
            HtmlPage page = webClient.getPage(url);
            DomNode nutritionNode = page.querySelector("div.CgE3Ac.I9mEQ > table > tbody");
            DomNode caloNode = nutritionNode.querySelector("tr:nth-child(2)");

            DomNode carbNode = null;
            DomNode fatNode = null;
            DomNode sugarNode = null;
            DomNode sodiumNode = null;
            DomNode proteinNode = null;

            for (DomNode node : nutritionNode.querySelectorAll("tr")) {
                if (node.asNormalizedText().contains("Carb"))
                    carbNode = node.querySelector("td.sjsZvd.IxZjcf.OE1use");
                else if (node.asNormalizedText().contains("Lipid"))
                    fatNode = node.querySelector("td.sjsZvd.IxZjcf.OE1use");
                else if (node.asNormalizedText().contains("Đường"))
                    sugarNode = node.querySelector("td.sjsZvd.IxZjcf.OE1use");
                else if (node.asNormalizedText().contains("Natri"))
                    sodiumNode = node.querySelector("td.sjsZvd.IxZjcf.OE1use");
                else if (node.asNormalizedText().contains("Protein"))
                    proteinNode = node.querySelector("td.sjsZvd.IxZjcf.OE1use");
            }

            foodNutriments = new FoodNutriments(
                    name.replaceAll("\\+", " "),
                    getDouble(getTextContentEvenNull(carbNode).trim()), getUnit(getTextContentEvenNull(carbNode).trim()),
                    getDouble(getTextContentEvenNull(caloNode).trim()), "kcal",
                    getDouble(getTextContentEvenNull(fatNode).trim()), getUnit(getTextContentEvenNull(fatNode).trim()),
                    getDouble(getTextContentEvenNull(sugarNode).trim()), getUnit(getTextContentEvenNull(sugarNode).trim()),
                    getDouble(getTextContentEvenNull(sodiumNode).trim()), getUnit(getTextContentEvenNull(sodiumNode).trim()),
                    getDouble(getTextContentEvenNull(proteinNode).trim()), getUnit(getTextContentEvenNull(proteinNode).trim()),
                    null
            );
        } catch (IOException | FailingHttpStatusCodeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (Exception e) {
            hasError = true;
            return new FoodNutriments(
                    name.replaceAll("\\+", " "), 0, null, 0, "kcal", 0, "g", 0, "g", 0, "g", 0, "g", null
            );
        }
        webClient.close();
        return foodNutriments;
    }
    public static void main(String[] args) {
        FoodNutriments foodNutriments = GetNutritionByName.get("cá hồi");
        System.out.println(foodNutriments);
    }
}
