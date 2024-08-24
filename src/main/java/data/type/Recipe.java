package data.type;

import java.util.ArrayList;

public class Recipe {
    private RecipeTitle title;
    private String preparationTime;
    private String processingTime;
    private String difficulty;
    private String imageURL;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;
    public Recipe(RecipeTitle title, String preparationTime, String processingTime, String difficulty,
                  ArrayList<Ingredient> ingredients, ArrayList<Step> steps, String imageUrl) {
        this.title = title;
        this.preparationTime = preparationTime;
        this.processingTime = processingTime;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
        this.steps = steps;
        this.imageURL = imageUrl;
    }
    public Recipe(){
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
        preparationTime = null;
        processingTime = null;
        difficulty = null;
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
    public void addStep(Step step) {
        steps.add(step);
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public String getImageURL() {
        return imageURL;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Recipe Title: " + title + "\n" +
                "Preparation Time: " + preparationTime + "\n" +
                "Processing Time: " + processingTime + "\n" +
                "Difficulty: " + difficulty + "\n");
        result.append("Image URL: ").append(imageURL).append("\n");
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
