package data.type;

public record Ingredient(String ingredientName, String ingredientQuantity, String ingredientUnit) {
    public Ingredient(String ingredientName, String ingredientQuantity, String ingredientUnit) {
        this.ingredientName = ingredientName.trim();
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientUnit = ingredientUnit;
    }

    @Override
    public String toString() {
        return ingredientName + " (" + ingredientQuantity + " " + ingredientUnit + ")";
    }
}
