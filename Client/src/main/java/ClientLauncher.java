import Services.SceneService;
import javafx.application.Application;

import javafx.stage.Stage;

import Services.NetworkService;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientLauncher extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        SceneService.setDefaultScene(primaryStage);

    }

    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int portNumber = 36936;

        try (Socket echoSocket = new Socket(hostName, portNumber);
             InputStream is = echoSocket.getInputStream();
             OutputStream os = echoSocket.getOutputStream();
             BufferedReader in = new BufferedReader((new InputStreamReader(is)));
             PrintWriter out = new PrintWriter(os, true);) {
            NetworkService.INPUT = in;
            NetworkService.OUTPUT = out;
            NetworkService.OBJOUTPUT = new ObjectOutputStream(os);
            NetworkService.OBJINPUT = new ObjectInputStream(is);
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
