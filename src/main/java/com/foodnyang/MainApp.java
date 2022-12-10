package com.foodnyang;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login/login_view.fxml"));
        FlowController.createScene("LoginScene", new Scene(fxmlLoader.load(), 520, 400) );
        FlowController.createStage("MainStage", new Stage());
        FlowController.setStage("MainStage");
        FlowController.getStage();

        String css = this.getClass().getResource("css/style.css").toExternalForm();
        FlowController.setScene("LoginScene");
        FlowController.getScene().getStylesheets().add(css);
        FlowController.getStage().setTitle("FoodNyang");
        FlowController.getStage().show();
    }

    public static void main(String[] args) {
        launch();
    }
}