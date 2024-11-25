package com.example.demo.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Shop {

    private final Stage stage; 

    private static final String SHOP_BACKGROUND_IMAGE = "/com/example/demo/images/pause.png";
    private static final String PIXEL_FONT = "/com/example/demo/fonts/pixelFont.ttf";
    private static final String CLOSE_BUTTON_IMAGE = "/com/example/demo/images/x_button.png";
    private static final String HEARTS_IMAGE = "/com/example/demo/images/hearts.png";
    private static final String GRASS_BUTTON_IMAGE = "/com/example/demo/images/grass_button.png";

    public Shop(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        Stage shopStage = new Stage();
        shopStage.initStyle(StageStyle.TRANSPARENT);
        shopStage.initOwner(stage);

        BorderPane rootLayout = new BorderPane();
        try {
            BackgroundImage backgroundImage = new BackgroundImage(
                new Image(this.getClass().getResource(SHOP_BACKGROUND_IMAGE).toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
            );
            rootLayout.setBackground(new Background(backgroundImage));
        } catch (NullPointerException e) {
            System.err.println("Background image not found: " + SHOP_BACKGROUND_IMAGE);
        }

       //Place button at top right 
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPadding(new Insets(10));

        //Create close button
        Button closeButton = createCloseButton(shopStage);
        topBar.getChildren().add(closeButton);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Font buttonFont = loadFont(PIXEL_FONT, 20);

        //Item1 
        HBox item1 = new HBox(10);
        item1.setAlignment(Pos.CENTER);
        //Hearts image
        ImageView heartsImageView = new ImageView();
        try {
            Image heartsImage = new Image(this.getClass().getResource(HEARTS_IMAGE).toExternalForm());
            heartsImageView.setImage(heartsImage);
            heartsImageView.setFitWidth(50); 
            heartsImageView.setFitHeight(50); 
        } catch (NullPointerException e) {
            System.err.println("Hearts image not found: " + HEARTS_IMAGE);
        }

        //Item 1 Button
        Button item1Button = new Button("Buy Item 1");
        item1Button.setFont(buttonFont);
        item1Button.setPrefWidth(200);
        item1Button.setPrefHeight(50);
        item1Button.setStyle(
            "-fx-background-image: url('" + this.getClass().getResource(GRASS_BUTTON_IMAGE).toExternalForm() + "');" +
            "-fx-background-size: 100% 100%;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-text-fill: #FFFFFF;" +
            "-fx-alignment: center;" +
            "-fx-background-color: transparent;" +
            "-fx-border-width: 0;"
        );

        item1.getChildren().addAll(item1Button, heartsImageView);

        layout.getChildren().addAll(item1);
        rootLayout.setTop(topBar);
        rootLayout.setCenter(layout);

        Scene shopScene = new Scene(rootLayout, 600, 400);
        shopScene.setFill(null);
        shopStage.setScene(shopScene);
        shopStage.show();
    }

    //Create close button
    private Button createCloseButton(Stage shopStage) {
        Button closeButton = new Button();
        closeButton.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-background-size: 50px 40px;");
        try {
            ImageView closeButtonImage = new ImageView(
                new Image(this.getClass().getResource(CLOSE_BUTTON_IMAGE).toExternalForm())
            );
            closeButtonImage.setFitWidth(30);
            closeButtonImage.setFitHeight(30);
            closeButton.setGraphic(closeButtonImage);
        } catch (NullPointerException e) {
            System.err.println("Close button image not found: " + CLOSE_BUTTON_IMAGE);
        }

        closeButton.setOnAction(event -> shopStage.close());
        return closeButton;
    }

    private Font loadFont(String fontPath, int size) {
        try {
            return Font.loadFont(this.getClass().getResource(fontPath).toExternalForm(), size);
        } catch (NullPointerException e) {
            System.err.println("Font not found: " + fontPath);
            return Font.font("Arial", size); 
        }
    }
}
