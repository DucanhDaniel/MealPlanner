package data.type;

import java.util.ArrayList;

public class Recipe {
    private RecipeTitle title;
    private String preparationTime;
    private String processingTime;
    private String difficulty;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;
    public Recipe(RecipeTitle title, String preparationTime, String processingTime, String difficulty,
                  ArrayList<Ingredient> ingredients, ArrayList<Step> steps) {
        this.title = title;
        this.preparationTime = preparationTime;
        this.processingTime = processingTime;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
        this.steps = steps;
    }
    public Recipe(){
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }
    public RecipeTitle getTitle() {
        return title;
    }
    public void setTitle(RecipeTitle title) {
        this.title = title;
    }
    public String getPreparationTime() {
        return preparationTime;
    }
    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }
    public String getProcessingTime() {
        return processingTime;
    }
    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    public ArrayList<Step> getSteps() {
        return steps;
    }
    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Recipe Title: " + title + "\n" +
                "Preparation Time: " + preparationTime + "\n" +
                "Processing Time: " + processingTime + "\n" +
                "Difficulty: " + difficulty + "\n");
        result.append("Ingredients: \n");
        for (Ingredient ingredient : ingredients) {
            result.append(ingredient).append("\n");
        }
        result.append("Steps: \n");
        for (Step step : steps) {
            result.append(step).append("\n");
        }
        return result.toString();
    }
}
