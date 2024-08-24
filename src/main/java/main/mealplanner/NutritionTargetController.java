package main.mealplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import plan.Read;
import plan.Write;

import java.io.IOException;
import java.util.Objects;

public class NutritionTargetController extends HomePageController  {


    public Pane pane;
    public Button changeBtn;
    public Label caloriesLabel;
    public Label proteinLabel;
    public TextField caloriesTextField;
    public TextField proteinTextField;
    public Label carbLabel;
    public TextField carbTextField;
    public Label fatLabel;
    public TextField fatTextField;

    @FXML
    public void initialize() {
        super.initialize();
        changeBtn.setOnAction(this::changeBtnClicked);
        getNutritionTarget();
    }
    public void getNutritionTarget() {
        JSONObject nutrition = Read.getNutritionTarget();
        double calories = Read.getDouble("calories", nutrition);
        double protein = Read.getDouble("protein", nutrition);
        double carbohydrates = Read.getDouble("carbohydrates", nutrition);
        double fats = Read.getDouble("fats", nutrition);
        caloriesLabel.setText(calories + "");
        proteinLabel.setText(protein + "");
        carbLabel.setText(carbohydrates + "");
        fatLabel.setText(fats + "");
    }
    public static void switchToNutritionTargetPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(NutritionTargetController.class.getResource("nutrition_target.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("nutrition target");
        stage.show();
    }

    public void switchToChangingMode() {
        isChangingNutritionTarget = true;
        changeBtn.setText("Save!");
        caloriesLabel.setVisible(false);
        proteinLabel.setVisible(false);
        carbLabel.setVisible(false);
        fatLabel.setVisible(false);
        caloriesTextField.setVisible(true);
        proteinTextField.setVisible(true);
        carbTextField.setVisible(true);
        fatTextField.setVisible(true);
    }
    public void switchToNormalMode() {
        isChangingNutritionTarget = false;
        changeBtn.setText("Change nutrition target");
        caloriesLabel.setVisible(true);
        proteinLabel.setVisible(true);
        carbLabel.setVisible(true);
        fatLabel.setVisible(true);
        caloriesTextField.setVisible(false);
        proteinTextField.setVisible(false);
        carbTextField.setVisible(false);
        fatTextField.setVisible(false);
    }

    public boolean isChangingNutritionTarget = false;
    public void changeBtnClicked(ActionEvent event) {
        if (!isChangingNutritionTarget) {
            switchToChangingMode();
        }
        else {
            try {
                // Save new target to JSON plan
                double calories = Double.parseDouble(proteinTextField.getText());
                double protein = Double.parseDouble(proteinTextField.getText());
                double carbohydrates = Double.parseDouble(carbTextField.getText());
                double fats = Double.parseDouble(fatTextField.getText());
                Write.changeNutritionTarget(calories, protein, carbohydrates, fats);

                // Change nutrition labels
                caloriesLabel.setText(caloriesTextField.getText());
                proteinLabel.setText(proteinTextField.getText());
                carbLabel.setText(carbTextField.getText());
                fatLabel.setText(fatTextField.getText());
                switchToNormalMode();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Invalid Input!");
                alert.setContentText("Please enter valid numbers.");
                alert.showAndWait();
            }
        }
    }
}
