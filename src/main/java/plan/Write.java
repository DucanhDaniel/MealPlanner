package plan;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;

public class Write {
    private static JSONObject weeklyPlan;
    public static void initialize() {
        weeklyPlan = Read.getWeeklyPlan();
    }
    @SuppressWarnings("unchecked")
    public static void changeNutritionTarget(double calories, double protein, double carbohydrates, double fats) {
        JSONObject newNutritionTarget = Create.getNutritionObject(calories, protein, carbohydrates, fats);
        weeklyPlan.put("NutritionTarget", newNutritionTarget);
        save();
    }

    @SuppressWarnings("unchecked")
    public static void changeDishNutrition(String day, String meal, int dishNumber, JSONObject newNutrition) {
        ((JSONObject) ((JSONArray) ((JSONObject) weeklyPlan.get(day)).get(meal)).get(dishNumber - 1)).put("nutrition", newNutrition);
        save();
    }

    @SuppressWarnings("unchecked")
    public static void changeDishName(String day, String meal, int dishNumber, String newName) {
        ((JSONObject) ((JSONArray) ((JSONObject) weeklyPlan.get(day)).get(meal)).get(dishNumber - 1)).put("name", newName);
        save();
    }

    @SuppressWarnings("unchecked")
    public static void addDish(String day, String meal, JSONObject newDish) {
        newDish.put("number", ((JSONArray) ((JSONObject) weeklyPlan.get(day)).get(meal)).size() + 1);
        ((JSONArray) ((JSONObject) weeklyPlan.get(day)).get(meal)).add(newDish);
        save();
    }

    @SuppressWarnings("unchecked")
    public static void removeDish(String day, String meal, int dishNumber) {
        ((JSONArray) ((JSONObject) weeklyPlan.get(day)).get(meal)).remove(dishNumber - 1);
        for (int i = dishNumber - 1; i < ((JSONArray) ((JSONObject) weeklyPlan.get(day)).get(meal)).size(); i++) {
            ((JSONObject) ((JSONArray) ((JSONObject) weeklyPlan.get(day)).get(meal)).get(i)).put("number", i + 1);
        }
        save();
    }

    public static void save() {
        try (FileWriter writer = new FileWriter("src/main/java/plan/plan.json")) {
            writer.write(weeklyPlan.toJSONString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        initialize();
        removeDish("Monday", "breakfast", 2);
    }
}
