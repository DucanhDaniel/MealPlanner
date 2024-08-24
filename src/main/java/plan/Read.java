package plan;

import data.type.FoodNutriments;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    public static JSONObject getNutritionTarget() {
        return (JSONObject) getWeeklyPlan().get("NutritionTarget");
    }
    public static JSONObject getDailyPlan(String day) {
        return (JSONObject) getWeeklyPlan().get(day);
    }
    public static JSONArray getDishList(String day, String meal) {
        return (JSONArray) getDailyPlan(day).get(meal);

    }
    public static FoodNutriments getFoodNutriments(String day, String meal, int dishNumber) {
        JSONArray foodList = getDishList(day, meal);
        JSONObject food = (JSONObject) foodList.get(dishNumber - 1);
        JSONObject nutrition = (JSONObject) food.get("nutrition");
        double calories = getDouble("calories", nutrition);
        double carbohydrates = getDouble("carbohydrates", nutrition);
        double protein = getDouble("protein", nutrition);
        double fats = getDouble("fats", nutrition);
        String foodName = getString("name", food);
        return new FoodNutriments(foodName, carbohydrates, "g", calories, "kcal", fats, "g", 0, "g",
                0, "", protein, "g", null);
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

    public static void exportPlanToTxtFile(String filepath) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] meals = {"breakfast", "lunch", "dinner"};
        StringBuilder stringBuilder = new StringBuilder();
        JSONObject weeklyPlan = Read.getWeeklyPlan();
        JSONObject nutritionTarget = (JSONObject) weeklyPlan.get("NutritionTarget");
        stringBuilder.append("Nutrition target this week: " + "\n");
        stringBuilder.append("\tCalories: ").append(nutritionTarget.get("calories")).append("\n");
        stringBuilder.append("\tProtein: ").append(nutritionTarget.get("protein")).append("\n");
        stringBuilder.append("\tCarbohydrates: ").append(nutritionTarget.get("carbohydrates")).append("\n");
        stringBuilder.append("\tFats: ").append(nutritionTarget.get("fats")).append("\n");

        stringBuilder.append("Weekly plan:");
        for (int i = 0; i < 7; i++) {
            stringBuilder.append("Day: ").append(days[i]).append("\n");
            JSONObject plan = (JSONObject) weeklyPlan.get(days[i]);
            for (int j = 0; j < 3; j++) {
                stringBuilder.append("\t").append(meals[j]).append(": ").append("\n");
                for (Object dish : ((JSONArray) plan.get(meals[j])).toArray()) {
                    int number = getInteger("number", (JSONObject) dish);
                    String dishName = getString("name", (JSONObject) dish);
                    stringBuilder.append("\t\tDish: ").append(dishName).append("\n");
                    stringBuilder.append("\t\tDish nutrition:\n");
                    JSONObject nutrition = (JSONObject) ((JSONObject) dish).get("nutrition");
                    stringBuilder.append("\t\t\tCalories: ").append(nutrition.get("calories")).append("\n");
                    stringBuilder.append("\t\t\tProtein: ").append(nutrition.get("protein")).append("\n");
                    stringBuilder.append("\t\t\tCarbohydrates: ").append(nutrition.get("carbohydrates")).append("\n");
                    stringBuilder.append("\t\t\tFats: ").append(nutrition.get("fats")).append("\n");
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filepath);
            System.out.println(e.getMessage());
        }
    }


        public static void main(String[] args) {
        exportPlanToTxtFile("asd");
//        System.out.println(getFoodNutriments("Monday", "lunch", 1));
    }
}
