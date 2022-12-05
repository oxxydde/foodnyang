package com.foodnyang;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class FlowController {
    private static HashMap<String, Stage> stages = new HashMap<>();
    private static HashMap<String, Scene> scenes = new HashMap<>();

    private static Stage stage;
    private static Scene scene;
    public static void setStage(String stageName) {
        stage = stages.get(stageName);
    }
    public static void createStage(String stageName, Stage newStage) {
        stages.put(stageName, newStage);
    }
    public static void removeStage(String stageName) {
        stages.remove(stageName);
    }
    public static void setScene(String sceneName) {
        scene = scenes.get(sceneName);
        stage.setScene(scene);
    }
    public static void createScene(String sceneName, Scene newScene) {
        scenes.put(sceneName, newScene);
    }
    public static void removeScene(String sceneName) {
        scenes.remove(sceneName);
    }
    public static Scene getScene() {
        return scene;
    }
    public static Stage getStage() {
        return stage;
    }
    public static Scene getSceneByKey(String sceneName) {
        return scenes.get(sceneName);
    }
    public static Stage getStageByKey(String stageName) {
        return stages.get(stageName);
    }
}
