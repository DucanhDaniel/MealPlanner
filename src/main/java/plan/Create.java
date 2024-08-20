package plan;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.ArrayList;


//TODO: Create a class for creating a new plan as a JSON file
// This JSON file should contain daily nutrition targets (calories, protein, carbohydrates, fats) and daily meal plans
public class Create {

    @SuppressWarnings("unchecked")
    public static JSONObject getNutritionObject(double calories, double protein, double carbohydrates, double fats) {
        JSONObject nutrition = new JSONObject();
        nutrition.put("calories", calories);
        nutrition.put("protein", protein);
        nutrition.put("carbohydrates", carbohydrates);
        nutrition.put("fats", fats);
        return nutrition;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject getDishObject(String name, int number, JSONObject nutrition) {
        JSONObject dish = new JSONObject();
        dish.put("name", name);
        dish.put("number", number);
        dish.put("nutrition", nutrition);
        return dish;
    }
    @SuppressWarnings("unchecked")
    public static JSONArray getMealObject(ArrayList<JSONObject> dishes) {
        JSONArray meal = new JSONArray();
        meal.addAll(dishes);
        return meal;
    }
    @SuppressWarnings("unchecked")
    public static JSONObject getDayObject(JSONArray breakfast, JSONArray lunch, JSONArray dinner) {
        JSONObject day = new JSONObject();
        day.put("breakfast", breakfast);
        day.put("lunch", lunch);
        day.put("dinner", dinner);
        return day;
    }
    @SuppressWarnings("unchecked")
    public static JSONObject getDailyNutritionTarget(double calories, int protein, int carbohydrates, int fats) {
        JSONObject dailyNutritionTargets = new JSONObject();
        dailyNutritionTargets.put("calories", calories);
        dailyNutritionTargets.put("protein", protein);
        dailyNutritionTargets.put("carbohydrates", carbohydrates);
        dailyNutritionTargets.put("fats", fats);
        return dailyNutritionTargets;
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        //TODO: Create nutrition target
        JSONObject nutritionTarget = getDailyNutritionTarget(2000, 1000, 300, 500);

        //TODO: Create meals for a day
        ArrayList<JSONObject> breakfastDishes = new ArrayList<>();
        breakfastDishes.add(getDishObject("Breakfast 1", 1, getNutritionObject(100, 10, 10, 5)));
        breakfastDishes.add(getDishObject("Breakfast 2", 2, getNutritionObject(150, 5, 10, 10)));
        JSONArray breakfast = getMealObject(breakfastDishes);

        ArrayList<JSONObject> lunchDishes = new ArrayList<>();
        lunchDishes.add(getDishObject("Lunch 1", 1, getNutritionObject(100, 10, 10, 5)));
        lunchDishes.add(getDishObject("Lunch 2", 2, getNutritionObject(150, 5, 10, 10)));
        JSONArray lunch = getMealObject(lunchDishes);

        ArrayList<JSONObject> dinnerDishes = new ArrayList<>();
        dinnerDishes.add(getDishObject("Dinner 1", 1, getNutritionObject(120, 15, 30, 10)));
        dinnerDishes.add(getDishObject("Dinner 2", 2, getNutritionObject(300, 0, 0, 0)));
        JSONArray dinner = getMealObject(dinnerDishes);

        // TODO: Create plan for every day in a week
        JSONObject monday = getDayObject(breakfast, lunch, dinner);
        JSONObject tuesday = getDayObject(breakfast, lunch, dinner);
        JSONObject wednesday = getDayObject(breakfast, lunch, dinner);
        JSONObject thursday = getDayObject(breakfast, lunch, dinner);
        JSONObject friday = getDayObject(breakfast, lunch, dinner);
        JSONObject saturday = getDayObject(breakfast, lunch, dinner);
        JSONObject sunday = getDayObject(breakfast, lunch, dinner);
        
        JSONObject weeklyPlan = new JSONObject();
        weeklyPlan.put("NutritionTarget", nutritionTarget);
        weeklyPlan.put("Monday", monday);
        weeklyPlan.put("Tuesday", tuesday);
        weeklyPlan.put("Wednesday", wednesday);
        weeklyPlan.put("Thursday", thursday);
        weeklyPlan.put("Friday", friday);
        weeklyPlan.put("Saturday", saturday);
        weeklyPlan.put("Sunday", sunday);
        
        try (FileWriter file = new FileWriter("src/main/java/plan/plan.json")) {
            file.write(weeklyPlan.toJSONString());
            file.flush();
        } catch (Exception e) {
            System.out.println("Error writing JSON object to file: " + e.getMessage());
        }

    }
}
