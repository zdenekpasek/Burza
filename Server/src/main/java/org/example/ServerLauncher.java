package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class ServerLauncher
{
    public static void main( String[] args )
    {
        int portNumber = 36936;
        Socket clientSocket = null;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is running");
            while (true) {
                clientSocket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(clientSocket);
                System.out.println("New socket detected");
            }
        } catch (

                IOException e) {
            System.out.println(
                    "Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
