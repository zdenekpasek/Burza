import Services.SceneService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import Controller;
import Services.NetworkService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class ClientLauncher extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        SceneService.setDefaultScene(primaryStage);

    }


    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int portNumber = 36936;

        try (Socket echoSocket = new Socket(hostName, portNumber);
             BufferedReader in = new BufferedReader((new InputStreamReader(echoSocket.getInputStream())));
             PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);) {
            NetworkService.INPUT = in;
            NetworkService.OUTPUT = out;
            if (in.readLine().equals("USERCONNECTED")) {
                launch(args);
            }else{
                System.exit(1);
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
