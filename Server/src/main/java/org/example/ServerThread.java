package org.example;

import Model.Entities.Users;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.net.Socket;

public class ServerThread{

    public static final String REGISTER = "1";
    public static final String LOGIN = "2";
    public static final String ADD_USER = "3";
    public static final String ADD_PRODUCT = "40";
    public static final String REMOVE_PRODUCT = "50";


    public static final String LOGIN_SUCCESS = "200";
    public static final String LOGIN_FAIL = "201";
    public static final String REGISTER_SUCCESS = "100";
    public static final String REGISTER_FAIL = "101";

    public ServerThread(final Socket client){
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("Thread running");
                try {
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    BufferedReader in = new BufferedReader((new InputStreamReader(client.getInputStream())));
                    out.println("USERCONNECTED");
//                    BufferedImage image = ImageIO.read(new File("D:\\NetworkEshop\\Server\\src\\main\\resources\\unknown.png"));
//
//                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                    ImageIO.write(image, "png", byteArrayOutputStream);
//
//                    byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
//                    client.getOutputStream().write(size);
//                    client.getOutputStream().write(byteArrayOutputStream.toByteArray());
//
//                    client.getOutputStream().flush();
//                    client.getOutputStream().close();
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
            switch(message){
                case REGISTER:{
                    register(out, in);
                    break;
                }
                case LOGIN:{
                    login(out, in);
                    break;
                }
            }
        }
    }

    private boolean validatePassword(String plainPassword, String hashPassword){
        if(BCrypt.checkpw(plainPassword, hashPassword)){
            return true;
        }
        return false;
    }

    private void login(PrintWriter out, BufferedReader in) throws IOException {
        // tady je plain password retarde, takze ho jen porovnej s hashPwd v db xd
        String[] loginData = new String[2];
        for(int i = 0; i < 2; i++){
            loginData[i] = in.readLine();
        }


    }

    private void register(PrintWriter out, BufferedReader in) throws IOException {
        String[] user = new String[6];
        for(int i = 0; i < 6; i++){
            user[i] = in.readLine();
        }
        //User newUser = new User(user[0], user[1], user[3]);
        Users userTest = new Users(user[0],  user[1], user[5]);
        if(UserDAO.addUser(userTest) && (AdressDAO.addAdress(user[2], user[3], user[4], userTest))){
            System.out.println("User added");
            System.out.println("Adress added");
            out.println(REGISTER_SUCCESS);
        } else{
            System.out.println("Error while adding user");
            out.println(REGISTER_FAIL);
        }
    }



}
