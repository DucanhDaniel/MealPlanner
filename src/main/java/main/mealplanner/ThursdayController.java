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

public class ThursdayController extends MondayController{
    public static void switchToThursdayPage(ActionEvent event) throws IOException {
        // TODO: Switch to home page
        Parent root = FXMLLoader.load(Objects.requireNonNull(MondayController.class.getResource("thursday.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Meal plan for Thursday");
        stage.show();
    }

    @Override
    public void saveBtnClicked(ActionEvent event) {
        // TODO: Save meal plan to JSON file and update nutrition values
        warningLabel.setVisible(false);

        displayNutritionLabels();
        saveToPlan("Thursday");
        displayMessage();
    }
    @Override
    public void importBtnClicked(ActionEvent event) {
        // TODO: Import meal plan from JSON file and update UI

        getDishNameFromJSON("Thursday");

        displayNutritionLabels();

        nutritionTitleLabel.setText("Total nutrition");
        savingLabel.setVisible(true);
        savingLabel.setText("Imported successfully!");
        savingLabel.setTextFill(GREEN);
    }
}
