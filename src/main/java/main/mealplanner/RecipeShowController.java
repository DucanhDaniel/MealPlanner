package main.mealplanner;

import data.type.Ingredient;
import data.type.Recipe;
import data.type.Step;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.min;

public class RecipeShowController extends HomePageController {
    public Label recipeNameLabel1;
    public Label recipeNameLabel2;
    public Label listIngredientsLabel;
    public Label stepTextLabel;
    public ImageView image1;
    public ImageView image2;
    public ImageView image3;
    public ImageView image4;
    public Label stepNumberLabel;
    public Label stepNameLabel;
    public ImageView previousImg;
    public ImageView nextImg;
    public Label preparationTime;
    public Label processingTime;
    public Label difficulty;
    public Label listIngredientsLabel1;

    @FXML
    public void initialize() {
        super.initialize();
    }

    public static void switchToRecipeShowPage(ActionEvent event) throws IOException {
        // TODO: Switch to recipe show page
        Parent root = FXMLLoader.load(Objects.requireNonNull(RecipeShowController.class.getResource("recipe_show.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Recipe show");
        stage.show();
    }
    private Recipe recipe;
    private ArrayList<Step> steps;
    private int stepIndex = 0;
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        steps = recipe.getSteps();
        pushDataToGUI();
    }
    public void pushDataToGUI() {
        // TODO: Populate recipe data to GUI elements
        String recipeName = recipe.getTitle().getTitle();
        recipeNameLabel1.setText(recipeName);
        recipeNameLabel2.setText(recipeName);

        preparationTime.setText(recipe.getPreparationTime());
        processingTime.setText(recipe.getProcessingTime());
        difficulty.setText(recipe.getDifficulty());

        ArrayList<Ingredient> ingredients = recipe.getIngredients();

        StringBuilder stringBuilder = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            stringBuilder.append("• ").append(ingredient.toString()).append("\n");}
        listIngredientsLabel.setText(stringBuilder.toString());


        pushStepData(1, steps.getFirst());
        previousImg.setVisible(false);
    }

    public void pushStepData(int index, Step step) {
        stepNumberLabel.setText(index + "");
        stepNameLabel.setText(step.getStepTitle());
        stepTextLabel.setText(step.getStepDescription());
        ArrayList<String> urls = step.getImageURLs();
        if (!urls.isEmpty()) setImageUrl(urls.getFirst(), image1);
        else image1.setImage(null);
        if (urls.size() >= 2) setImageUrl(urls.get(1), image2);
        else image2.setImage(null);
        if (urls.size() >= 3) setImageUrl(urls.get(2), image3);
        else image3.setImage(null);
        if (urls.size() >= 4) setImageUrl(urls.get(3), image4);
        else image4.setImage(null);

    }
    public void setImageUrl(String url, ImageView imageView) {
        Image image = new Image(url);
        imageView.setImage(image);
    }
    public void handleNext(MouseEvent event) {
        stepIndex++;
        pushStepData(stepIndex + 1, steps.get(stepIndex));
        updateNextPreviousImgStatus();
    }
    public void handlePrevious(MouseEvent event) {
        stepIndex--;
        pushStepData(stepIndex + 1, steps.get(stepIndex));
        updateNextPreviousImgStatus();
    }
    public void updateNextPreviousImgStatus() {
        if (stepIndex == steps.size() - 1) {
            nextImg.setVisible(false);
            previousImg.setVisible(true);
        }
        else if (stepIndex == 0) {
            nextImg.setVisible(true);
            previousImg.setVisible(false);
        }
        else {
            nextImg.setVisible(true);
            previousImg.setVisible(true);
        }
    }
}
