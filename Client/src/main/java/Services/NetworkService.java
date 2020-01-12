package Services;

import java.io.*;

// třída, která slouží pro komunikaci mezi klientem a serverem
// klient posílá zprávy serveru ve formě stringů, server na tyto zprávy reaguje
// a podle nich vykonává určité operace
// dále obsahuje metody pro čtení a posílání zpráv
public class NetworkService {

    public static BufferedReader INPUT;
    public static PrintWriter OUTPUT;
    public static ObjectInputStream OBJINPUT;
    public static ObjectOutputStream OBJOUTPUT;
    public static final String REGISTER = "1";
    public static final String LOGIN = "2";
    public static final String ADD_USER = "3";
    public static final String ADD_PRODUCT = "4";
    public static final String REMOVE_PRODUCT = "5";
    public static final String CATEGORY_SEND = "6";
    public static final String BUY_PRODUCT = "7";
    public static final String GET_ALL_PRODUCTS = "8";
    public static final String GET_ALL_ORDERS = "9";
    public static final String SEND_ALL_PRODUCTS = "800";


    public static final String ADD_PRODUCT_SUCCESS = "400";
    public static final String ADD_PRODUCT_FAIL = "401";

    public static final String REMOVE_PRODUCT_SUCCESS = "500";
    public static final String REMOVE_PRODUCT_FAIL = "501";

    public static final String BUY_PRODUCT_SUCCESS = "700";
    public static final String BUY_PRODUCT_FAIL = "701";

    public static final String GET_ALL_PRODUCTS_SUCCESS = "800";
    public static final String GET_ALL_PRODUCTS_FAIL = "801";

    public static final String GET_ALL_ORDERS_SUCCESS = "900";
    public static final String GET_ALL_ORDERS_FAIL = "901";

    public static final String LOGIN_SUCCESS = "200";
    public static final String LOGIN_FAIL = "201";
    public static final String REGISTER_SUCCESS = "100";
    public static final String REGISTER_FAIL = "101";

    public static final String SHOW_PICTURE = "900";

    // metoda pro čtení zpráv (Stringů)
    public static String readMessage() throws IOException {
        return INPUT.readLine();
    }

    // metoda pro čtení zpráv (Objektů)
    public static Object readObjectMessage() throws IOException, ClassNotFoundException {
        return OBJINPUT.readObject();
    }

    // metoda pro posílná zpráv v samostatném threadu (Stringů)
    public static void sendMessage(final String message){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                OUTPUT.println(message);
            }
        });
        t.start();
    }
    // metoda pro posílná zpráv v samostatném threadu (pole Stringů)
    public static void sendMessage(final String[] messages){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(String message : messages ){
                    OUTPUT.println(message);
                }
            }
        });
        t.start();
    }

    // metoda pro posílná zpráv v samostatném threadu (Objektů)
    public static void sendMessage(final Object messages){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OBJOUTPUT.writeObject(messages);
                } catch(IOException e){
                    System.out.println(e);
                }

            }
        });
        t.start();
    }
}
