package org.example.DAO;

import Model.Entities.Category;
import Model.Entities.Product;
import Model.Entities.Users;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductDAO {

    public static boolean addProduct(String productName, int productPrice, Users user, Category category){
        int productID = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Product product = new Product(productName, productPrice, user, category);
            productID = (Integer)session.save(product);
            tx.commit();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return productID > 0;
    }
}
