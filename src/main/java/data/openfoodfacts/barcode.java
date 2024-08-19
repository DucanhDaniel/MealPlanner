package data.openfoodfacts;

import api.Fetch;
import api.Read;
import data.type.FoodNutriments;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;

// Get product data from Open FoodFacts API
// Need barcode to get data
public class barcode {
    public static FoodNutriments getProductData(String barcode) {
        String url = "https://world.openfoodfacts.net/api/v2/product/" + barcode +
                "?fields=product_name,nutriscore_data,nutriments";
        HttpURLConnection connection = Fetch.fetchApiResponse(url);
        try {
            assert connection != null;
            if (connection.getResponseCode() != 200) {
                System.out.println("Connection error: " + connection.getResponseCode());
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String jsonResponse = Read.readApiResponse(connection);

        JSONParser parser = new JSONParser();
        FoodNutriments foodNutriments;
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            String response = (String) jsonObject.get("status_verbose");
            if (response.equals("product not found")) {
                System.out.println("Product not found in Open FoodFacts database. Please check the barcode and try again. ");
                return null;
            }
            JSONObject product = (JSONObject) jsonObject.get("product");
            String productName = (String) product.get("product_name");
            JSONObject nutriments = (JSONObject) product.get("nutriments");
            JSONObject nutriscoreData = (JSONObject) product.get("nutriscore_data");
            foodNutriments = new FoodNutriments(
                    productName,
                    Read.readDouble(nutriments, "carbohydrates_100g"), Read.readString(nutriments, "carbohydrates_unit", 0),
                    Read.readDouble(nutriments, "energy-kcal_100g"), Read.readString(nutriments, "energy-kcal_unit", 0),
                    Read.readDouble(nutriments, "fat_100g"), Read.readString(nutriments, "fat_unit", 0),
                    Read.readDouble(nutriments, "sugars_100g"), Read.readString(nutriments, "sugars_unit", 0),
                    Read.readDouble(nutriments, "sodium_100g"), Read.readString(nutriments, "sodium_unit", 0),
                    Read.readDouble(nutriments, "proteins_100g"), Read.readString(nutriments, "proteins_unit", 0),
                    Read.readString(nutriscoreData, "grade", 1)
            );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return foodNutriments;
    }
    public static void main(String[] args) {
        System.out.println(getProductData("8934588012112"));
    }
}
