package main.mealplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.scene.paint.Color.GREEN;

public class SundayController extends MondayController{
    public static void switchToSundayPage(ActionEvent event) throws IOException {
        // TODO: Switch to home page
        Parent root = FXMLLoader.load(Objects.requireNonNull(MondayController.class.getResource("sunday.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Meal plan for Sunday");
        stage.show();
    }

    @Override
    public void saveBtnClicked(ActionEvent event) {
        // TODO: Save meal plan to JSON file and update nutrition values
        warningLabel.setVisible(false);

        displayNutritionLabels();
        saveToPlan("Sunday");
        displayMessage();
    }
    @Override
    public void importBtnClicked(ActionEvent event) {
        // TODO: Import meal plan from JSON file and update UI

        getDishNameFromJSON("Sunday");

        displayNutritionLabels();

        nutritionTitleLabel.setText("Total nutrition");
        savingLabel.setVisible(true);
        savingLabel.setText("Imported successfully!");
        savingLabel.setTextFill(GREEN);
    }
}
