package plan;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

//TODO: Create a class for reading a JSON plan file
// This class should be able to read JSON plan file and return JSON object representing the plan
public class Read {
    public static int getInteger(String key, JSONObject jsonObject) {
        if (jsonObject == null) return 0;
        if (!jsonObject.containsKey(key)) return 0;
        return ((Number) jsonObject.get(key)).intValue();
    }
    public static String getString(String key, JSONObject jsonObject) {
        if (jsonObject == null) return "";
        if (!jsonObject.containsKey(key)) return "";
        return (String) jsonObject.get(key);
    }
    public static double getDouble(String key, JSONObject jsonObject) {
        if (jsonObject == null) return 0.0;
        if (!jsonObject.containsKey(key)) return 0.0;
        return ((Number) jsonObject.get(key)).doubleValue();
    }
    public static JSONObject getWeeklyPlan() {
        // Initialize the JSON object from the JSON file
        JSONObject weeklyPlan = null;
        try {
            JSONParser parser = new JSONParser();
            weeklyPlan = (JSONObject) parser.parse(new FileReader("src/main/java/plan/plan.json"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return weeklyPlan;
    }

    // TODO: Print weekly plan to the console
    public static void printWeeklyPlan() {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] meals = {"breakfast", "lunch", "dinner"};
        JSONObject weeklyPlan = Read.getWeeklyPlan();
        JSONObject nutritionTarget = (JSONObject) weeklyPlan.get("NutritionTarget");
        System.out.println("Nutrition target this week: ");
        System.out.println("\tCalories: " + nutritionTarget.get("calories"));
        System.out.println("\tProtein: " + nutritionTarget.get("protein"));
        System.out.println("\tCarbohydrates: " + nutritionTarget.get("carbohydrates"));
        System.out.println("\tFats: " + nutritionTarget.get("fats"));

        System.out.println("Weekly plan:");
        for (int i = 0; i < 7; i++) {
            System.out.println("Day: " + days[i]);
            JSONObject plan = (JSONObject) weeklyPlan.get(days[i]);
            for (int j = 0; j < 3; j++) {
                System.out.println("\t" + meals[j] + ": ");
                for (Object dish : ((JSONArray) plan.get(meals[j])).toArray()) {
                    int number = getInteger("number", (JSONObject) dish);
                    String dishName = getString("name", (JSONObject) dish);
                    System.out.println("\t\tDish: " + dishName + ", Number: " + number);
                    JSONObject nutrition = (JSONObject) ((JSONObject) dish).get("nutrition");
                    System.out.println("\t\t\tCalories: " + nutrition.get("calories"));
                    System.out.println("\t\t\tProtein: " + nutrition.get("protein"));
                    System.out.println("\t\t\tCarbohydrates: " + nutrition.get("carbohydrates"));
                    System.out.println("\t\t\tFats: " + nutrition.get("fats"));
                }
            }
        }
        System.out.println(weeklyPlan);
    }
    public static void main(String[] args) {
        printWeeklyPlan();
    }
}
