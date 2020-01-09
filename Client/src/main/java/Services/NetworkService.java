package Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class NetworkService {

    public static BufferedReader INPUT;
    public static PrintWriter OUTPUT;
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

    public static String readMessage() throws IOException {
        return INPUT.readLine();
    }

    public static void sendMessage(final String message){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                OUTPUT.println(message);
            }
        });
        t.start();
    }

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

}
