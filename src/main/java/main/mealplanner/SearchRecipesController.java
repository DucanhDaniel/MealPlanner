package main.mealplanner;

import data.scraper.GetRecipesByName;
import data.type.Recipe;
import data.type.RecipeTitle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

import static data.scraper.GetRecipesByName.getListRecipes;
import static data.scraper.GetRecipesByName.getRecipe;
import static main.mealplanner.RecipeShowController.switchToRecipeShowPage;

public class SearchRecipesController extends HomePageController{
    public Pane pane2;
    public Pane pane3;
    public Pane pane1;
    public Label title;
    public TextField searchTextField;

    public Label title1;
    public Button browseBtn1;
    public Label processLabel1;
    public Label diffLabel1;
    public ImageView image1;
    public Label prepareLabel1;
    public Label title2;
    public Button browseBtn2;
    public Label processLabel2;
    public Label diffLabel2;
    public ImageView image2;
    public Label prepareLabel2;
    public Label title3;
    public Button browseBtn3;
    public Label processLabel3;
    public Label diffLabel3;
    public ImageView image3;
    public Label prepareLabel3;
    public Button moreBtn;
//    public Button testBbtn;

    @Override
    public void initialize() {
        super.initialize();
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        moreBtn.setVisible(false);

        searchTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Handle Enter key press event
                ArrayList<RecipeTitle> recipeList = getListRecipes(searchTextField.getText());
                Recipe recipe;
                int i = 0;
                while (i < recipeList.size()) {
                    recipe = getRecipe(recipeList.get(i));
                    if (recipe != null && recipe.getPreparationTime() != null) {
                        pushDataToGUI(pane1, title1, prepareLabel1, processLabel1, diffLabel1, browseBtn1, image1, recipe);
                        i++;
                        break;
                    }
                    i++;
                }
                while (i < recipeList.size()) {
                    recipe = getRecipe(recipeList.get(i));
//                    if ((recipe.getTitle().getTitle()).toLowerCase().contains(searchTextField.getText().toLowerCase()))
                    if (recipe != null && recipe.getPreparationTime() != null) {
                        pushDataToGUI(pane2, title2, prepareLabel2, processLabel2, diffLabel2, browseBtn2, image2, recipe);
                        i++;
                        break;
                    }
                    i++;
                }
                while (i < recipeList.size()) {
                    recipe = getRecipe(recipeList.get(i));
                    if (recipe != null && recipe.getPreparationTime() != null) {
                        pushDataToGUI(pane3, title3, prepareLabel3, processLabel3, diffLabel3, browseBtn3, image3, recipe);

                        moreBtn.setVisible(true);
                        moreBtn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                try {
                                    Desktop.getDesktop().browse(new URI(GetRecipesByName.url));
                                } catch (IOException | URISyntaxException e1) {
                                    System.out.println(e1.getMessage());
                                }
                            }
                        });
                        break;
                    }
                    i++;
                }

            }
        });
    }

    public Parent root;
    public Stage stage;
    public Scene scene;

    public void pushDataToGUI(Pane pane, Label paneTitle, Label prepareLabel, Label processLabel, Label diffLabel, Button btn, ImageView imageView, Recipe recipe) {
        pane.setVisible(true);
//        paneTitle.setText(recipe.getTitle().getTitle());
        String name = recipe.getTitle().getTitle();
        paneTitle.setText(name);
        prepareLabel.setText(recipe.getPreparationTime());
        processLabel.setText(recipe.getProcessingTime());
        diffLabel.setText(recipe.getDifficulty());
        imageView.setImage(new Image(recipe.getImageURL()));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("recipe_show.fxml"));
                    root = loader.load();
                    RecipeShowController recipeShowController = loader.getController();
                    recipeShowController.setRecipe(recipe);
                    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.out.println("Error showing recipe!" + ex.getMessage());
                }
            }
        });

    }
    public static void switchToSearchRecipesPage(ActionEvent event) throws IOException {
        // TODO: Switch to home page
        Parent root = FXMLLoader.load(Objects.requireNonNull(MondayController.class.getResource("search_recipes.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Search recipes");
        stage.show();
    }

}
