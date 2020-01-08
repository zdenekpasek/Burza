import Services.NetworkService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {

    String hostName;
    int portNumber;

    public Client(String hostName, int portNumber){
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    @Override
    public void run() {
        try (Socket echoSocket = new Socket(hostName, portNumber);
             BufferedReader in = new BufferedReader((new InputStreamReader(echoSocket.getInputStream())));
             PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);) {
            NetworkService.INPUT = in;
            NetworkService.OUTPUT = out;
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
