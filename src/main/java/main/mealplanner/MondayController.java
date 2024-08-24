package main.mealplanner;

import data.scraper.GetNutritionByName;
import data.type.FoodNutriments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import plan.Read;
import plan.Write;

import java.io.IOException;
import java.util.Objects;

import static javafx.scene.paint.Color.*;

public class MondayController extends HomePageController{


    public Pane NutritionPane;
    public Pane MealPane;
    public Label caloriesLabel;
    public Label carbLabel;
    public Label proteinLabel;
    public Label fatLabel;
    public TextField breakfastDish1;
    public TextField breakfastDish2;
    public TextField breakfastDish3;
    public TextField dinnerDish1;
    public TextField dinnerDish2;
    public TextField dinnerDish3;
    public TextField lunchDish1;
    public TextField lunchDish2;
    public TextField lunchDish3;
    public Button saveBtn;
    public Label savingLabel;
    public Label warningLabel;
    public Label nutritionTitleLabel;
    public Button importBtn;

    public static void switchToMondayPage(ActionEvent event) throws IOException {
        // TODO: Switch to home page
        Parent root = FXMLLoader.load(Objects.requireNonNull(MondayController.class.getResource("monday.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Meal plan for Monday");
        stage.show();
    }

    FoodNutriments breakfast1 = null;
    FoodNutriments breakfast2 = null;
    FoodNutriments breakfast3 = null;
    FoodNutriments dinner1 = null;
    FoodNutriments dinner2 = null;
    FoodNutriments dinner3 = null;
    FoodNutriments lunch1 = null;
    FoodNutriments lunch2 = null;
    FoodNutriments lunch3 = null;

    boolean breakfast1Status = false;
    boolean breakfast2Status = false;
    boolean breakfast3Status = false;
    boolean lunch1Status = false;
    boolean lunch2Status = false;
    boolean lunch3Status = false;
    boolean dinner1Status = false;
    boolean dinner2Status = false;
    boolean dinner3Status = false;

    @FXML
    public void initialize() {
        super.initialize();
        breakfastDish1.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Handle Enter key press event
                warningLabel.setVisible(false);
                savingLabel.setVisible(false);
                breakfast1 = GetNutritionByName.get(breakfastDish1.getText());
                breakfast1Status = true;
                showDishesNutritionInfor(breakfast1);
                breakfastDish2.requestFocus();
            }
            else breakfast1Status = false;
        });
        breakfastDish2.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Handle Enter key press event
                warningLabel.setVisible(false);
                savingLabel.setVisible(false);
                breakfast2 = GetNutritionByName.get(breakfastDish2.getText());
                breakfast2Status = true;
                showDishesNutritionInfor(breakfast2);
                breakfastDish3.requestFocus();
            }
            else breakfast2Status = false;
        });
        breakfastDish3.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Handle Enter key press event
                warningLabel.setVisible(false);
                savingLabel.setVisible(false);
                breakfast3 = GetNutritionByName.get(breakfastDish3.getText());
                breakfast3Status = true;
                showDishesNutritionInfor(breakfast3);
                lunchDish1.requestFocus();
            }
            else breakfast3Status = false;
        });
        lunchDish1.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Handle Enter key press event
                warningLabel.setVisible(false);
                savingLabel.setVisible(false);
                lunch1 = GetNutritionByName.get(lunchDish1.getText());
                lunch1Status = true;
                showDishesNutritionInfor(lunch1);
                lunchDish2.requestFocus();
            }
            else lunch1Status = false;
        });
        lunchDish2.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Handle Enter key press event
                warningLabel.setVisible(false);
                savingLabel.setVisible(false);
                lunch2 = GetNutritionByName.get(lunchDish2.getText());
                lunch2Status = true;
                showDishesNutritionInfor(lunch2);
                lunchDish3.requestFocus();
            }
            else lunch2Status = false;
        });
        lunchDish3.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Handle Enter key press event
                warningLabel.setVisible(false);
                savingLabel.setVisible(false);
                lunch3 = GetNutritionByName.get(lunchDish3.getText());
                lunch3Status = true;
                showDishesNutritionInfor(lunch3);
                dinnerDish1.requestFocus();
            }
            else lunch3Status = false;
        });
        dinnerDish1.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Handle Enter key press event
                warningLabel.setVisible(false);
                savingLabel.setVisible(false);
                dinner1 = GetNutritionByName.get(dinnerDish1.getText());
                dinner1Status = true;
                showDishesNutritionInfor(dinner1);
                dinnerDish2.requestFocus();
            }
            else dinner1Status = false;
        });
        dinnerDish2.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Handle Enter key press event
                warningLabel.setVisible(false);
                savingLabel.setVisible(false);
                dinner2 = GetNutritionByName.get(dinnerDish2.getText());
                dinner2Status = true;
                showDishesNutritionInfor(dinner2);
                dinnerDish3.requestFocus();
            }
            else dinner2Status = false;
        });
        dinnerDish3.setOnKeyPressed(event -> {
            // Handle Enter key press event
            if (event.getCode().equals(KeyCode.ENTER)) {
                warningLabel.setVisible(false);
                savingLabel.setVisible(false);
                dinner3 = GetNutritionByName.get(dinnerDish3.getText());
                dinner3Status = true;
                showDishesNutritionInfor(dinner3);
            }
            else dinner3Status = false;
        });
        saveBtn.setOnAction(this::saveBtnClicked);
        importBtn.setOnAction(this::importBtnClicked); //Important: Must be set at every single day!
    }
    private void showDishesNutritionInfor(FoodNutriments foodNutriments) {
        // TODO: Show dishes nutrition information
        caloriesLabel.setText(foodNutriments.getEnergyValue() + " " + foodNutriments.getEnergyUnit());
        carbLabel.setText(foodNutriments.getCarbValue() + " " + foodNutriments.getCarbUnit());
        proteinLabel.setText(foodNutriments.getProteinValue() + " " + foodNutriments.getProteinUnit());
        fatLabel.setText(foodNutriments.getFatValue() + " " + foodNutriments.getFatUnit());
        nutritionTitleLabel.setText("Nutrition of " + foodNutriments.getFoodName());
        savingLabel.setVisible(true);
        savingLabel.setText("Getting information completed!");
        savingLabel.setTextFill(GREEN);
        if (GetNutritionByName.hasError) {
            GetNutritionByName.hasError = false;
            warningLabel.setVisible(true);
            warningLabel.setText("Warning: Some foods not found in the database, nutritional values may be incorrect.");
            warningLabel.setTextFill(RED);
        }
    }
    public void saveToPlan(String day) {
        Write.initialize();

        Write.changeDishName(day, "breakfast", 1, breakfast1.getFoodName());
        Write.changeDishName(day, "breakfast", 2, breakfast2.getFoodName());
        Write.changeDishName(day, "breakfast", 3, breakfast3.getFoodName());
        Write.changeDishName(day, "dinner", 1, dinner1.getFoodName());
        Write.changeDishName(day, "dinner", 2, dinner2.getFoodName());
        Write.changeDishName(day, "dinner", 3, dinner3.getFoodName());
        Write.changeDishName(day, "lunch", 1, lunch1.getFoodName());
        Write.changeDishName(day, "lunch", 2, lunch2.getFoodName());
        Write.changeDishName(day, "lunch", 3, lunch3.getFoodName());

        Write.changeDishNutrition(day, "breakfast", 1, breakfast1.getNutrition());
        Write.changeDishNutrition(day, "breakfast", 2, breakfast2.getNutrition());
        Write.changeDishNutrition(day, "breakfast", 3, breakfast3.getNutrition());
        Write.changeDishNutrition(day, "dinner", 1, dinner1.getNutrition());
        Write.changeDishNutrition(day, "dinner", 2, dinner2.getNutrition());
        Write.changeDishNutrition(day, "dinner", 3, dinner3.getNutrition());
        Write.changeDishNutrition(day, "lunch", 1, lunch1.getNutrition());
        Write.changeDishNutrition(day, "lunch", 2, lunch2.getNutrition());
        Write.changeDishNutrition(day, "lunch", 3, lunch3.getNutrition());
    }
    public void updateFoodNutriment() {
        if (!breakfast1Status) breakfast1 = GetNutritionByName.get(breakfastDish1.getText());
        if (!breakfast2Status) breakfast2 = GetNutritionByName.get(breakfastDish2.getText());
        if (!breakfast3Status) breakfast3 = GetNutritionByName.get(breakfastDish3.getText());

        if (!lunch1Status) lunch1 = GetNutritionByName.get(lunchDish1.getText());
        if (!lunch2Status) lunch2 = GetNutritionByName.get(lunchDish2.getText());
        if (!lunch3Status) lunch3 = GetNutritionByName.get(lunchDish3.getText());

        if (!dinner1Status) dinner1 = GetNutritionByName.get(dinnerDish1.getText());
        if (!dinner2Status) dinner2 = GetNutritionByName.get(dinnerDish2.getText());
        if (!dinner3Status) dinner3 = GetNutritionByName.get(dinnerDish3.getText());
    }
    public void displayNutritionLabels() {
        // TODO: Display nutrition values in labels
        assert breakfast1 != null;
        double calories = breakfast1.getEnergyValue() + breakfast2.getEnergyValue() + breakfast3.getEnergyValue() +
                dinner1.getEnergyValue() + dinner2.getEnergyValue() + dinner3.getEnergyValue() +
                lunch1.getEnergyValue() + lunch2.getEnergyValue() + lunch3.getEnergyValue();

        double protein = breakfast1.getProteinValue() + breakfast2.getProteinValue() + breakfast3.getProteinValue() +
                dinner1.getProteinValue() + dinner2.getProteinValue() + dinner3.getProteinValue() +
                lunch1.getProteinValue() + lunch2.getProteinValue() + lunch3.getProteinValue();


        double carbohydrates = breakfast1.getCarbValue() + breakfast2.getCarbValue() + breakfast3.getCarbValue() +
                dinner1.getCarbValue() + dinner2.getCarbValue() + dinner3.getCarbValue() +
                lunch1.getCarbValue() + lunch2.getCarbValue() + lunch3.getFatValue();

        double fats = breakfast1.getFatValue() + breakfast2.getFatValue() + breakfast3.getFatValue() +
                dinner1.getFatValue() + dinner2.getFatValue() + dinner3.getFatValue() +
                lunch1.getFatValue() + lunch2.getFatValue() + lunch3.getFatValue();



        caloriesLabel.setText(String.format("%.1f", calories) + " kcal");
        proteinLabel.setText(String.format("%.1f", protein) + " g");
        carbLabel.setText(String.format("%.1f", carbohydrates) + " g");
        fatLabel.setText(String.format("%.1f", fats) + " g");
    }
    public void displayMessage() {
        //TODO: Display message after saving meal plan
        savingLabel.setVisible(true);
        savingLabel.setText("Saving completed!");
        nutritionTitleLabel.setText("Total nutrition");
        if (GetNutritionByName.hasError) {
            GetNutritionByName.hasError = false;
            warningLabel.setVisible(true);
            warningLabel.setText("Warning: Some foods not found in the database, nutritional values may be incorrect.");
            warningLabel.setTextFill(RED);
        }
    }
    public void getDishNameFromJSON(String day) {
        breakfast1 = Read.getFoodNutriments(day, "breakfast", 1);
        breakfast2 = Read.getFoodNutriments(day, "breakfast", 2);
        breakfast3 = Read.getFoodNutriments(day, "breakfast", 3);

        dinner1 = Read.getFoodNutriments(day, "dinner", 1);
        dinner2 = Read.getFoodNutriments(day, "dinner", 2);
        dinner3 = Read.getFoodNutriments(day, "dinner", 3);

        lunch1 = Read.getFoodNutriments(day, "lunch", 1);
        lunch2 = Read.getFoodNutriments(day, "lunch", 2);
        lunch3 = Read.getFoodNutriments(day, "lunch", 3);

        breakfastDish1.setText(breakfast1.getFoodName());
        breakfastDish2.setText(breakfast2.getFoodName());
        breakfastDish3.setText(breakfast3.getFoodName());

        dinnerDish1.setText(dinner1.getFoodName());
        dinnerDish2.setText(dinner2.getFoodName());
        dinnerDish3.setText(dinner3.getFoodName());

        lunchDish1.setText(lunch1.getFoodName());
        lunchDish2.setText(lunch2.getFoodName());
        lunchDish3.setText(lunch3.getFoodName());

        breakfast1Status = true;
        breakfast2Status = true;
        breakfast3Status = true;
        lunch1Status = true;
        lunch2Status = true;
        lunch3Status = true;
        dinner1Status = true;
        dinner2Status = true;
        dinner3Status = true;
    }
    public void importBtnClicked(ActionEvent event) {
        // TODO: Import meal plan from JSON file and update UI

        getDishNameFromJSON("Monday");

        displayNutritionLabels();

        nutritionTitleLabel.setText("Total nutrition");
        savingLabel.setVisible(true);
        savingLabel.setText("Imported successfully!");
        savingLabel.setTextFill(GREEN);
    }
    public void saveBtnClicked(ActionEvent event) {
        // TODO: Save meal plan to JSON file and update nutrition values
        warningLabel.setVisible(false);

        updateFoodNutriment();
        displayNutritionLabels();
        saveToPlan("Monday");
        displayMessage();
    }
}
