package api;

import org.json.simple.JSONObject;

import java.net.HttpURLConnection;
import java.util.Scanner;

public class Read {
    public static String readApiResponse(HttpURLConnection apiConnection) {
        try {
            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(apiConnection.getInputStream());

            while (scanner.hasNextLine()) {
                resultJson.append(scanner.nextLine());
            }
            scanner.close();

            return resultJson.toString();
        } catch (Exception e) {
            System.out.println("Error reading API response: " + e.getMessage());
        }
        return null;
    }
    public static double readDouble(JSONObject jsonObject, String fieldName) {
        if (jsonObject == null) return 0;
        if (!jsonObject.containsKey(fieldName)) return 0;
        return ((Number) jsonObject.get(fieldName)).doubleValue();
    }
    public static String readString(JSONObject jsonObject, String fieldName, int needNull) {
        if (jsonObject == null) return (needNull == 1? "null" : "");
        if (!jsonObject.containsKey(fieldName)) return (needNull == 1? "null" : "");
        return (String) jsonObject.get(fieldName);
    }
}
