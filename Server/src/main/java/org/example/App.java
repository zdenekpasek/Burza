package org.example;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        String userEmail = "zdnenekk@gmail.com";
        String userName = "zdenek";
        String userPassword = "testpass";

        String categoryName = "dsdsd";
        String categoryDescription = "desc";
        Date date = new Date();

        System.out.println(UserDAO.getHash(userEmail));


//        if(UserDAO.authUser(userEmail)){
//            System.out.println("User authorized.");
//        } else{
//            System.out.println("User is not in db.");
//        }

//        if(UserDAO.addUser(userEmail, userName, userPassword)){
//            System.out.println("User added");
//        } else{
//            System.out.println("Error while adding user");
//        }
    }
}
