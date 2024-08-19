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
        return Double.parseDouble(number);
    }
    public static String getUnit(String text) {
        for (int i = text.length() - 1; i >= 0; i--)
            if (text.charAt(i) == ' ') {
                return text.substring(i + 1);
            }
        return null;
    }
    public static FoodNutriments get(String name) {
        name = name.replaceAll(" ", "+");
        String url = "https://www.google.com/search?q=giá+trị+dinh+dưỡng+của" + name;
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        FoodNutriments foodNutriments = null;
        try {
            HtmlPage page = webClient.getPage(url);
            DomNode nutritionNode = page.querySelector("#main > div:nth-child(6) > div > div > div.CgE3Ac.I9mEQ > table > tbody");
            DomNode caloNode = nutritionNode.querySelector("tr:nth-child(2)");
            DomNode carbNode = nutritionNode.querySelector("tr:nth-child(9) > td.sjsZvd.IxZjcf.OE1use");
            DomNode fatNode = nutritionNode.querySelector("tr:nth-child(4) > td.sjsZvd.IxZjcf.OE1use");
            DomNode sugarNode = nutritionNode.querySelector("tr:nth-child(11) > td.sjsZvd.IxZjcf.OE1use");
            DomNode sodiumNode = nutritionNode.querySelector("tr:nth-child(7) > td.sjsZvd.IxZjcf.OE1use");
            DomNode proteinNode = nutritionNode.querySelector("tr:nth-child(12) > td.sjsZvd.IxZjcf.OE1use");
            foodNutriments = new FoodNutriments(
                    name.replaceAll("\\+", " "),
                    getDouble(carbNode.getTextContent().trim()), getUnit(carbNode.getTextContent().trim()),
                    getDouble(caloNode.getTextContent().trim()), "kcal",
                    getDouble(fatNode.getTextContent().trim()), getUnit(fatNode.getTextContent().trim()),
                    getDouble(sugarNode.getTextContent().trim()), getUnit(sugarNode.getTextContent().trim()),
                    getDouble(sodiumNode.getTextContent().trim()), getUnit(sodiumNode.getTextContent().trim()),
                    getDouble(proteinNode.getTextContent().trim()), getUnit(proteinNode.getTextContent().trim()),
                    null
            );
        } catch (IOException | FailingHttpStatusCodeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        webClient.close();
        return foodNutriments;
    }
    public static void main(String[] args) {
        FoodNutriments foodNutriments = GetNutritionByName.get("gà rán");
        System.out.println(foodNutriments);
    }
}
