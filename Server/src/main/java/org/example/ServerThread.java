package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ServerThread{


    public ServerThread(final Socket client){
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("Thread running");
                try {
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    BufferedReader in = new BufferedReader((new InputStreamReader(client.getInputStream())));
                    out.println("USERCONNECTED");

                    BufferedImage image = ImageIO.read(new File("D:\\NetworkEshop\\Server\\src\\main\\resources\\unknown.png"));

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ImageIO.write(image, "png", byteArrayOutputStream);

                    byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
                    client.getOutputStream().write(size);
                    client.getOutputStream().write(byteArrayOutputStream.toByteArray());

                    client.getOutputStream().flush();
                    client.getOutputStream().close();
                    handleUserAction(out, in);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }

    private void handleUserAction(PrintWriter out, BufferedReader in) throws IOException {
        while(true){
            String message = in.readLine();
            if(message.contains("BUY")){
                out.println("CONFIRM");
            }else if(message.contains("SELL")){

            }else{

            }
        }
    }
}
