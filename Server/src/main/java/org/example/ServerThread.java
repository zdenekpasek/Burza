package org.example;

import Model.Entities.Adress;
import Model.Entities.Category;
import Model.Entities.Product;
import Model.Entities.Users;
import Services.NetworkService;
import org.example.DAO.AdressDAO;
import org.example.DAO.CategoryDAO;
import org.example.DAO.ProductDAO;
import org.example.DAO.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


import javax.jws.soap.SOAPBinding;

public class ServerThread{

    public static final String REGISTER = "1";
    public static final String LOGIN = "2";
    public static final String ADD_USER = "3";
    public static final String ADD_PRODUCT = "4";
    public static final String REMOVE_PRODUCT = "5";
    public static final String CATEGORY_SEND = "6";
    public static final String BUY_PRODUCT = "7";
    public static final String GET_ALL_PRODUCTS = "8";
    public static final String SEND_ALL_PRODUCTS = "800";


    public static final String ADD_PRODUCT_SUCCESS = "400";
    public static final String ADD_PRODUCT_FAIL = "401";

    public static final String REMOVE_PRODUCT_SUCCESS = "500";
    public static final String REMOVE_PRODUCT_FAIL = "501";

    public static final String BUY_PRODUCT_SUCCESS = "700";
    public static final String BUY_PRODUCT_FAIL = "701";

    public static final String GET_ALL_PRODUCTS_SUCCESS = "800";
    public static final String GET_ALL_PRODUCTS_FAIL = "801";

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
                    ObjectInputStream objIn = new ObjectInputStream(client.getInputStream());
                    ObjectOutputStream objOut = new ObjectOutputStream(client.getOutputStream());
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
                    handleUserAction(out, in, objIn, objOut);
                } catch (IOException | ClassNotFoundException e) {
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


    // metoda pro obsluhu klienta, příjmá zprávy od klienta a podle nich se volají specifické metody
    private void handleUserAction(PrintWriter out, BufferedReader in, ObjectInputStream objIn,  ObjectOutputStream objOut) throws IOException, ClassNotFoundException {
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
                    addProduct(out, in, objIn, objOut);
                    break;
                }
                case REMOVE_PRODUCT:{
                    removeProduct(out, in);
                    break;
                }
                case GET_ALL_PRODUCTS:{
                    getAllProducts(objOut, out);
                    break;
                }
                case BUY_PRODUCT:{
                    buyProduct(out, in);
                    break;
                }
                default: {
                    System.out.println("Wrong message   " + message);
                }
            }
        }
    }

    private void buyProduct(PrintWriter out, BufferedReader in) {
    }

    private void getAllProducts(ObjectOutputStream objOut, PrintWriter out) {
        try {
            List<Product> products = ProductDAO.selectAllProductsObj();
            List<String[]> productStrings = new ArrayList<>();
            for(Product product : products){
                String[] convertedProducts = new String[5];
                convertedProducts[0] = product.getProductID() + "";
                convertedProducts[1] = product.getProductName();
                convertedProducts[2] = product.getCategory().getCategoryName();
                convertedProducts[3] = product.getProductDescription();
                convertedProducts[4] = product.getProductPrice() + "";
                productStrings.add(convertedProducts);
            }
            objOut.writeObject(productStrings);
        }catch(Exception e){
            System.out.println(e.getMessage());
            out.println(GET_ALL_PRODUCTS_FAIL);
        }
        out.println(GET_ALL_PRODUCTS_SUCCESS);
    }

    private void removeProduct(PrintWriter out, BufferedReader in) throws IOException {
        String data = in.readLine();
        Product prodToRemove = ProductDAO.selectProduct(data);
        String productName = prodToRemove.getProductName();
        int userID = UserDAO.selectUserID(loggedUser);
        int categoryID = ProductDAO.selectCategoryID(productName);

        if(ProductDAO.removeProduct(productName, userID) && CategoryDAO.deleteCategory(categoryID)){
            out.println(REMOVE_PRODUCT_SUCCESS);
            System.out.println("success removing product");
        } else{
            out.println(REMOVE_PRODUCT_FAIL);
            System.out.println("Fail removing product");
        }
    }

    // metoda přijme data o produktu od klienta ve formě pole Stringů, tyto data naplní do metody addProduct v Data Access Objectu
    // a ta data pošle do databáze, jakmile tato metoda proběhne úspěšně, pošle klientovi zprávu o úspěchu a naopak
    private void addProduct(PrintWriter out, BufferedReader in, ObjectInputStream objIn, ObjectOutputStream objOut) throws IOException, ClassNotFoundException {
        // productName == productData[0] ; productDescription == productData[1] ; productPrice == productData[2] ; productPhotoPath == productData[3]
        String[] productData = new String[4];
        for(int i = 0; i < 4; i++){
            productData[i] = in.readLine();
        }
        byte[] image = (byte[])objIn.readObject();
        Category category = new Category(productData[3], "");
        if(ProductDAO.addProduct(productData[0], Integer.parseInt(productData[2]), productData[1], image, loggedUser, category )){
            out.println(ADD_PRODUCT_SUCCESS);
        } else{
            out.println(ADD_PRODUCT_FAIL);
        }

    }

    // metoda porovnává plain text heslo zadané z user inputu s zahashovaným heslem z databáze, vrací true při úspšchu
    private boolean validatePassword(String plainPassword, String hashPassword){
        if(BCrypt.checkpw(plainPassword, hashPassword)){
            return true;
        }
        return false;
    }

    // metoda přijme email a heslo od klienta ve formě pole Stringů, email předá do Data Access Object metody authUser,
    // která porovná zda v databázi existuje uživatel se zadaným emailem.
    // dále vezme plain text heslo a porovná ho s zahashovaným heslem v databázi, jakmile splní všechny podmínky
    // pošle klientovi zprávu o úspěšném loginu a naopak
    private void login(PrintWriter out, BufferedReader in) throws IOException {
        // email == loginData[0] ; password == loginData[1]
        String[] loginData = new String[2];
        for(int i = 0; i < 2; i++){
            loginData[i] = in.readLine();
        }

        if(UserDAO.authUser(loginData[0]) && validatePassword(loginData[1], UserDAO.getHash(loginData[0]) )){
            out.println(LOGIN_SUCCESS);
            loggedUser = loginData[0];
            System.out.println("User successfully authorized!");
            Users user = UserDAO.selectUser(loginData[0]);
            int userID = UserDAO.selectUserID(loginData[0]);
            Object[] adress = AdressDAO.selectAdress(userID);
            String[] userData = {user.getUserEmail(), user.getUserName()};
            String []adressDataArray = Arrays.copyOf(adress, adress.length, String[].class);
            for(String userValue : userData){
                out.println(userValue);
            }
            for(String adressValue : adressDataArray){
                out.println(adressValue);
            }


            for(int i = 0; i < adressDataArray.length; i++){
                System.out.println(adressDataArray[i]);
            }

            for(int i = 0; i < 2; i++){
                System.out.println(userData[i]);
            }

//            out.println(loggedUser);
        } else{
            System.out.println("Error while authorizing user.");
            out.println(LOGIN_FAIL);
        }

    }

    // metoda přijme data o Userovi od klienta ve formě pole Stringů, tyto data naplní do metody addUser v Data Access Objectu
    // a ta data pošle do databáze, jakmile tato metoda proběhne úspěšně, pošle klientovi zprávu o úspěchu a naopak
    private void register(PrintWriter out, BufferedReader in) throws IOException {
        // email == user[0] ; name == user[1] ; city == user[2] ; country == user[3] ; ZIP == user[4] ; password == [5]
        String[] user = new String[6];
        for(int i = 0; i < 6; i++){
            user[i] = in.readLine();
        }
        String[] profileData = new String[]{user[0], user[1], user[2], user[3], user[4]};
        Users userTest = new Users(user[0],  user[1], user[5]);
        if(!UserDAO.authUser(user[0])){
            UserDAO.addUser(userTest);
            AdressDAO.addAdress(user[2], user[3], user[4], userTest);
            out.println(REGISTER_SUCCESS);
            for(String profileInfo : profileData){
                out.println(profileInfo);
            }
        } else{
            System.out.println("Error while adding user");
            out.println(REGISTER_FAIL);
        }
    }

}
