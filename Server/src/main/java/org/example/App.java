package org.example;

import Model.Entities.Category;
import Model.Entities.Product;
import Model.Entities.Users;
import org.example.DAO.CategoryDAO;
import org.example.DAO.ProductDAO;
import org.example.DAO.UserDAO;

import java.util.Date;
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        String userEmail = "zdnenekk@gmail.com";
        String userName = "zdenek";
        String userPassword = "testpass";

        String categoryName = "Kategorie1";
        String categoryDescription = "fsdfffffffffff";
        Date date = new Date();


        Users user = new Users(userEmail, userName, userPassword);
        Category category = new Category(categoryName, categoryDescription);
        Product product = new Product("New product", 11122);
        if(ProductDAO.addProduct(product.getProductName(),product.getProductPrice(), user, category)){
            System.out.println("success");
        } else{
            System.out.println("fuk");
        }
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
