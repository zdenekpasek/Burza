package Services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneService {
    public static String scene1 = "sample.fxml";
    public static String loginScreen = "loginScreen.fxml";
    public static String registerScreen = "registerScreen.fxml";
    public static String mainScreen = "mainScreen.fxml";
    public static String addProductScreen = "addProductScreen.fxml";
    public static String removeProductScreen = "removeProductScreen.fxml";
    public static String profileScreen = "profileScreen.fxml";



    public static Scene controller;

    public static void setDefaultScene(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(SceneService.class.getClassLoader().getResource("loginScreen.fxml"));
        primaryStage.setScene(new Scene(root));
        SceneService.setController(primaryStage.getScene());
        primaryStage.setTitle("E-Flea Market");
        primaryStage.show();
    }

    public static void switchScene(String scene) throws IOException {
        Parent root = FXMLLoader.load(SceneService.class.getClassLoader().getResource(scene));
        controller.setRoot(root);
    }

    public static void setController(Scene scene){
        controller = scene;
    }
}
