package org.example;

import Model.Entities.Category;
import Model.Entities.Users;
import org.example.DAO.AdressDAO;
import org.example.DAO.CategoryDAO;
import org.example.DAO.ProductDAO;
import org.example.DAO.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import Services.NetworkService;

public class ServerThread{

    public static final String REGISTER = "1";
    public static final String LOGIN = "2";
    public static final String ADD_USER = "3";
    public static final String ADD_PRODUCT = "4";
    public static final String REMOVE_PRODUCT = "5";
    public static final String CATEGORY_SEND = "6";

    public static final String ADD_PRODUCT_SUCCESS = "400";
    public static final String ADD_PRODUCT_FAIL = "401";

    public static final String REMOVE_PRODUCT_SUCCESS = "500";
    public static final String REMOVE_PRODUCT_FAIL = "501";



    public static final String LOGIN_SUCCESS = "200";
    public static final String LOGIN_FAIL = "201";
    public static final String REGISTER_SUCCESS = "100";
    public static final String REGISTER_FAIL = "101";

    private String loggedUser;

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

    private void sendCategories(PrintWriter out){
        NetworkService.sendMessage(NetworkService.CATEGORY_SEND);
        NetworkService.sendMessage(Objects.requireNonNull(CategoryDAO.getCategories()).toString());
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
                case ADD_PRODUCT:{
                    addProduct(out, in);
                    break;
                }
                default: {
                    System.out.println("Wrong message   " + message);
                }
            }
        }
    }

    private void addProduct(PrintWriter out, BufferedReader in) throws IOException{
        // productName == productData[0] ; productDescription == productData[1] ; productPrice == productData[2] ; productPhotoPath == productData[3]

        String[] productData = new String[4];
        for(int i = 0; i < 4; i++){
            productData[i] = in.readLine();
        }
        Category category = new Category("Hadasd", "dasdsd");
        System.out.println(Arrays.toString(productData[3].getBytes()));
        if(ProductDAO.addProduct(productData[0], Integer.parseInt(productData[2]), productData[1], productData[3].getBytes(), loggedUser, category )){
            out.println(ADD_PRODUCT_SUCCESS);
            out.println(Arrays.toString(ProductDAO.selectProduct(productData[0]).getProductPhoto()));
        }
        out.println(ADD_PRODUCT_FAIL);
    }

    private boolean validatePassword(String plainPassword, String hashPassword){
        if(BCrypt.checkpw(plainPassword, hashPassword)){
            return true;
        }
        return false;
    }

    private void login(PrintWriter out, BufferedReader in) throws IOException {
        // email == loginData[0] ; password == loginData[1]
        String[] loginData = new String[2];
        for(int i = 0; i < 2; i++){
            loginData[i] = in.readLine();
        }

        if(UserDAO.authUser(loginData[0]) && validatePassword(loginData[1], UserDAO.getHash(loginData[0]) )){
            System.out.println("User successfully authorized!");
            out.println(LOGIN_SUCCESS);
            loggedUser = loginData[0];
        } else{
            System.out.println("Error while authorizing user.");
            out.println(LOGIN_FAIL);
        }

    }

    private void register(PrintWriter out, BufferedReader in) throws IOException {
        // email == user[0] ; name == user[1] ; city == user[2] ; country == user[3] ; ZIP == user[4] ; password == [5]
        String[] user = new String[6];
        for(int i = 0; i < 6; i++){
            user[i] = in.readLine();
        }
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
