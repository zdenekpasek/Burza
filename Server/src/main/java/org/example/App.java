package org.example;

import org.example.DAO.OrderDAO;
import org.example.DAO.OrderProductDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        String userEmail = "zdnenek@gmail.com";
        String userName = "zdenek";
        String userPassword = "testpass";

        String productName = "Produkt2";
        int userID = 23;

        String categoryName = "Caaaat";
        String categoryDescription = "ssssss";
        Date date = new Date();

        int productID = 47;


        if(OrderProductDAO.addProductToOrder(46, 1)){
            System.out.println("Success adding product to order");
        } else{
            System.out.println("error");
        }


//        String loggedUser = "admin@gmail.com";
//        LocalDateTime localDate = LocalDateTime.now();
//        if(OrderDAO.addOrder(localDate, "pending", loggedUser)){
//            System.out.println("Successfully added order :]]]");
//        } else{
//            System.out.println("fuck off");
//        }




//        ProductDAO.updateProductByID(productID);



//        Users user = new Users(userEmail, userName, userPassword);
//
//
//        int categoryID = ProductDAO.selectCategoryID(productName);
//
////        System.out.println(categoryID);
//        if(CategoryDAO.deleteCategory(categoryID) && ProductDAO.removeProduct(productName, userID)){
//            System.out.println("success");
//        }


//        for(Product prod : Objects.requireNonNull(ProductDAO.selectAllProductsObj())){
//            System.out.println(prod.getProductName() + " desc " + prod.getProductDescription());
//        }



//        ProductDAO.selectAllProductsObj()
//        for(Product p : ProductDAO.selectAllProductsObj()){
//            System.out.println(p.getProductName());
//        }

//        UserDAO.getUserDetails(userEmail);
//
//        for(Object[] product : ProductDAO.selectAllProducts()){
//            System.out.println(product[0] + " : " + product[1] + " : " + product[2] + " : " + product[3]);
//        }


//        Category category = new Category(categoryName, categoryDescription);
//        File file = new File("C:\\Users\\zdenek\\Documents\\NetworkEshop\\Client\\src\\main\\resources\\img\\ic_email_24px.png");
//        byte[] bFile = new byte[(int) file.length()];
//        try{
//            FileInputStream fileInputStream = new FileInputStream(file);
//            fileInputStream.read(bFile);
//            fileInputStream.close();
//        } catch (Exception e){
//            e.printStackTrace();
//
//        }

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
