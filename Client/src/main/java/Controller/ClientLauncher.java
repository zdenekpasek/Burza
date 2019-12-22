package Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
       // Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        StackPane pane = new StackPane();
        primaryStage.setTitle("Hello World");
        Button button = new Button();
        button.setText("Test");
        pane.getChildren().add(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NetworkService.OUTPUT.println("BUY;PC;1;user");
                try {
                    if(NetworkService.INPUT.readLine().equals("CONFIRM")){
                        System.out.println("Product bought");
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(primaryStage);
                        VBox dialogVbox = new VBox(20);
                        dialogVbox.getChildren().add(new Text("Product bought"));
                        Scene dialogScene = new Scene(dialogVbox, 300, 200);
                        dialog.setScene(dialogScene);
                        dialog.show();
                    }else{
                        System.out.println("Error when buying product");
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(primaryStage);
                        VBox dialogVbox = new VBox(20);
                        dialogVbox.getChildren().add(new Text("Error when buying product"));
                        Scene dialogScene = new Scene(dialogVbox, 300, 200);
                        dialog.setScene(dialogScene);
                        dialog.show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.setScene(new Scene(pane, 300, 275));
        primaryStage.show();
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
                System.out.println("User connected");

                System.out.println("Reading: " + System.currentTimeMillis());

                byte[] sizeAr = new byte[4];
                echoSocket.getInputStream().read(sizeAr);
                int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                byte[] imageAr = new byte[size];
                echoSocket.getInputStream().read(imageAr);

                BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

                System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
                ImageIO.write(image, "png", new File("D:\\NetworkEshop\\Client\\src\\main\\resources\\test2.png"));

                launch(args);
            }else{
                System.exit(1);
            }
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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
