package data.type;

public class FoodNutriments {
    private String foodName;
    private double carbValue;
    private String carbUnit;
    private double energyValue;
    private String energyUnit;
    private double fatValue;
    private String fatUnit;
    private double sugarValue;
    private String sugarUnit;
    private double sodiumValue;
    private String sodiumUnit;
    private double proteinValue;
    private String proteinUnit;
    private String nutrimentScore;
    public FoodNutriments(String foodName,
                          double carbValue, String carbUnit,
                          double energyValue, String energyUnit,
                          double fatValue, String fatUnit,
                          double sugarValue, String sugarUnit,
                          double sodiumValue, String sodiumUnit,
                          double proteinValue, String proteinUnit,
                          String nutrimentScore) {
        this.foodName = foodName;
        this.carbValue = carbValue;
        this.carbUnit = carbUnit;
        this.energyValue = energyValue;
        this.energyUnit = energyUnit;
        this.fatValue = fatValue;
        this.fatUnit = fatUnit;
        this.sugarValue = sugarValue;
        this.sugarUnit = sugarUnit;
        this.sodiumValue = sodiumValue;
        this.sodiumUnit = sodiumUnit;
        this.proteinValue = proteinValue;
        this.proteinUnit = proteinUnit;
        this.nutrimentScore = nutrimentScore;
    }
    @Override
    public String toString() {
        return "Product Name: " + foodName + "\n" +
                "Carbohydrate: " + carbValue + " " + carbUnit + "\n" +
                "Energy: " + energyValue + " " + energyUnit + "\n" +
                "Fat: " + fatValue + " " + fatUnit + "\n" +
                "Sugar: " + sugarValue + " " + sugarUnit + "\n" +
                "Sodium: " + sodiumValue + " " + sodiumUnit + "\n" +
                "Protein: " + proteinValue + " " + proteinUnit + "\n" +
                "Nutriment Score: " + nutrimentScore;
    }
}
