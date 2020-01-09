package org.example;

import Model.Entities.Category;
import Model.Entities.Product;
import Model.Entities.Users;
import org.example.DAO.CategoryDAO;
import org.example.DAO.ProductDAO;
import org.example.DAO.UserDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        String userEmail = "zdnenekk@gmail.com";
        String userName = "zdenek";
        String userPassword = "testpass";

        String categoryName = "Caaaat";
        String categoryDescription = "ssssss";
        Date date = new Date();


        Users user = new Users(userEmail, userName, userPassword);
        Category category = new Category(categoryName, categoryDescription);
        File file = new File("C:\\Users\\zdenek\\Documents\\NetworkEshop\\Client\\src\\main\\resources\\img\\ic_email_24px.png");
        byte[] bFile = new byte[(int) file.length()];
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e){
            e.printStackTrace();

        }

//        Product product = new Product("Produuuuct", 11122, "Popisek");
//        if(ProductDAO.addProduct(product.getProductName(),product.getProductPrice(),product.getProductDescription(), bFile, user, category)){
//            System.out.println("success");
//        } else{
//            System.out.println("fuk");
//        }
//
//        for(String o : Objects.requireNonNull(CategoryDAO.getCategories())){
//            System.out.println(o);
//        }

//        System.out.println(UserDAO.getHash(userEmail));


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
