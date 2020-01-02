package Services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneService {
    public static String scene1 = "sample.fxml";
    public static String loginScreen = "loginScreen.fxml";
    public static String registerScreen = "registerScreen.fxml";
    public static String mainScreen = "mainScreen.fxml";

    public static Scene controller;

    public static void switchScene(String scene) throws IOException {
        Parent root = FXMLLoader.load(SceneService.class.getClassLoader().getResource(scene));
        controller.setRoot(root);
    }

    public static void setController(Scene scene){
        controller = scene;
    }
}
