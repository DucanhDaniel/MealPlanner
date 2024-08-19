package data.type;

public class Ingredient {
    private final String ingredientName;
    private final String ingredientQuantity;
    private final String ingredientUnit;
    public Ingredient(String ingredientName, String ingredientQuantity, String ingredientUnit) {
        this.ingredientName = ingredientName.trim();
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientUnit = ingredientUnit;
    }
    public String getIngredientName() {
        return ingredientName;
    }
    public String getIngredientQuantity() {
        return ingredientQuantity;
    }
    public String getIngredientUnit() {
        return ingredientUnit;
    }

    @Override
    public String toString() {
        return ingredientName + " (" + ingredientQuantity + " " + ingredientUnit + ")";
    }
}
