package main.mealplanner;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import plan.Read;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static main.mealplanner.FridayController.switchToFridayPage;
import static main.mealplanner.MondayController.switchToMondayPage;
import static main.mealplanner.NutritionTargetController.switchToNutritionTargetPage;
import static main.mealplanner.SaturdayController.switchToSaturdayPage;
import static main.mealplanner.SearchRecipesController.switchToSearchRecipesPage;
import static main.mealplanner.SundayController.switchToSundayPage;
import static main.mealplanner.ThursdayController.switchToThursdayPage;
import static main.mealplanner.TuesdayController.switchToTuesdayPage;
import static main.mealplanner.WednesdayController.switchToWednesdayPage;


public class HomePageController {

    public HBox hbox;
    public Button MenuBtn;
    public Button ExportBtn;
    public Button ExitBtn;
    public VBox vbox;
    public Button PlanBtn;
    public Button NutritionTargetBtn;
    public Button MondayBtn;
    public Button TuesdayBtn;
    public Button Wednesday;
    public Button ThursdayBtn;
    public Button FridayBtn;
    public Button SaturdayBtn;
    public Button SundayBtn;
    public Button IngredientsListBtn;
    public Button SearchBtn;
    public Button SearchRecipesBtn;
    public Button SearchDishNutritionBtn;
    public Button BarcodeBtn;
    public VBox planListVBox;

    public static void switchToHomePage(ActionEvent event) throws IOException {
        // TODO: Switch to home page
        Parent root = FXMLLoader.load(Objects.requireNonNull(HomePageController.class.getResource("home_page.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.show();
    }

    private boolean isDaysOpen = false;
    @FXML
    public void initialize() {
        // TODO: Initialize home page components
        ExitBtn.setOnAction(event -> {
            System.exit(0);
        });

        NutritionTargetBtn.setOnAction(this::NutritionTargetBtnClicked);
        MondayBtn.setOnAction(this::MondayBtnClicked);
        TuesdayBtn.setOnAction(this::TuesdayBtnClicked);
        Wednesday.setOnAction(this::WednesdayBtnClicked);
        ThursdayBtn.setOnAction(this::ThursdayBtnClicked);
        FridayBtn.setOnAction(this::FridayBtnClicked);
        SaturdayBtn.setOnAction(this::SaturdayBtnClicked);
        SundayBtn.setOnAction(this::SundayBtnClicked);
        ExportBtn.setOnAction(this::ExportBtnClicked);
        SearchRecipesBtn.setOnAction(this::SearchRecipesBtnClicked);
    }


    public void NutritionTargetBtnClicked(ActionEvent event) {
        try {
            switchToNutritionTargetPage(event);
        } catch (IOException e) {
            System.out.println("Error switching to nutrition target page: " + e.getMessage());
        }
    }
    public void MondayBtnClicked(ActionEvent event) {
        try {
            switchToMondayPage(event);
        } catch (IOException e) {
            System.out.println("Error switching to monday page: " + e.getMessage());
        }
    }
    public void TuesdayBtnClicked(ActionEvent event) {
        try {
            switchToTuesdayPage(event);
        } catch (IOException e) {
            System.out.println("Error switching to tuesday page: " + e.getMessage());
        }
    }
    public void WednesdayBtnClicked(ActionEvent event) {
        try {
            switchToWednesdayPage(event);
        } catch (IOException e) {
            System.out.println("Error switching to wednesday page: " + e.getMessage());
        }
    }
    public void ThursdayBtnClicked(ActionEvent event) {
        // TODO: Implement Thursday button click event
        try {
            switchToThursdayPage(event);
        } catch (IOException e) {
            System.out.println("Error switching to thursday page: " + e.getMessage());
        }
    }
    public void FridayBtnClicked(ActionEvent event) {
        // TODO: Implement Friday button click event
        try {
            switchToFridayPage(event);
        } catch (IOException e) {
            System.out.println("Error switching to friday page: " + e.getMessage());
        }
    }
    public void SaturdayBtnClicked(ActionEvent event) {
        // TODO: Implement Saturday button click event
        try {
            switchToSaturdayPage(event);
        } catch (IOException e) {
            System.out.println("Error switching to saturday page: " + e.getMessage());
        }
    }
    public void SundayBtnClicked(ActionEvent event) {
        // TODO: Implement Sunday button click event
        try {
            switchToSundayPage(event);
        } catch (IOException e) {
            System.out.println("Error switching to sunday page: " + e.getMessage());
        }
    }
    public void ExportBtnClicked(ActionEvent event) {
        // TODO: Implement export plan to a text file
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory to save meal plan");
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory!= null) {
            String directoryPath = selectedDirectory.getAbsolutePath();
            Read.exportPlanToTxtFile(directoryPath + "/meal_plan.txt");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Export Successful!");

            alert.setContentText("Your meal plan has been exported to the selected directory.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Export Failed!");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }
    public void SearchRecipesBtnClicked(ActionEvent event) {
        try {
            switchToSearchRecipesPage(event);
        } catch (IOException e) {
            System.out.println("Error switching to search recipes page: " + e.getMessage());
        }
    }
}