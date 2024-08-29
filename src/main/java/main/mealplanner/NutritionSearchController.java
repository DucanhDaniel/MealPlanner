package main.mealplanner;

import data.scraper.GetNutritionByName;
import data.type.FoodNutriments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class NutritionSearchController extends HomePageController{
    public VBox dynamicContent;
    public Button doneBtn;

    public static void switchToSearchDishNutritionPage(ActionEvent event) throws IOException {
        // TODO: Switch to search dish nutrition page
        Parent root = FXMLLoader.load(Objects.requireNonNull(NutritionSearchController.class.getResource("nutrition_search.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Search dish nutrition");
        stage.show();
    }
    @FXML
    private Button addTextFieldButton;

    @FXML
    private ScrollPane dynamicContainer;

    private ArrayList<TextField> ingredientsName = new ArrayList<>();
    private ArrayList<Pane> panes = new ArrayList<>();
    private ArrayList<TextField> ingredientsAmount = new ArrayList<>();
    @FXML
    public void initialize() {
        // Initialize any necessary components or data
        super.initialize();
    }

    public void handleAddTextFieldButtonClick(ActionEvent event) {
        Pane newPane = new Pane();
        TextField ingredientName = new TextField();
        Label ingredientQuantity = new Label();
        TextField ingredientAmount = new TextField();
        Label label = new Label();
        newPane.getChildren().addAll(ingredientName, ingredientQuantity, ingredientAmount, label);

        label.setText((ingredientsName.size() + 1) + ".");
        label.setFont(new Font("Cambria", 27));
        ingredientQuantity.setFont(new Font("Cambria", 27));
        ingredientName.setPromptText("Ingredient Name");
        ingredientQuantity.setText("x100g");
        ingredientAmount.setPromptText("Amount");
        label.setAlignment(Pos.CENTER_RIGHT);

        label.setPrefSize(37, 41);
        ingredientName.setPrefSize(207, 40);
        ingredientQuantity.setPrefSize(146, 40);
        ingredientAmount.setPrefSize(94, 40);

        label.setLayoutX(11);
        label.setLayoutY(15);
        ingredientName.setLayoutX(56);
        ingredientName.setLayoutY(15);
        ingredientQuantity.setLayoutX(399);
        ingredientQuantity.setLayoutY(15);
        ingredientAmount.setLayoutX(285);
        ingredientAmount.setLayoutY(16);

        dynamicContent.getChildren().add(newPane);
        panes.add(newPane);
        ingredientsName.add(ingredientName);
        ingredientsAmount.add(ingredientAmount);
    }
    public void doneBtnClicked(ActionEvent event) {
        double calories = 0, protein = 0, carb = 0, fats = 0;
        for (int i = 0; i < ingredientsName.size(); i++) {
            String name = ingredientsName.get(i).getText();
            double amount = 0;
            try {
                amount = Double.parseDouble(ingredientsAmount.get(i).getText().replaceAll(",", "."));
            } catch (Exception e) {
                amount = 0;
            }
            FoodNutriments foodNutriment =  GetNutritionByName.get(name);
            calories += foodNutriment.getEnergyValue()*amount;
            protein += foodNutriment.getProteinValue()*amount;
            carb += foodNutriment.getCarbValue()*amount;
            fats += foodNutriment.getFatValue()*amount;
        }
    }
}
