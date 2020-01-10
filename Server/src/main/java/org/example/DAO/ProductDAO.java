package org.example.DAO;

import Model.Entities.Category;
import Model.Entities.Product;
import Model.Entities.Users;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {

    // přidá product do databáze, včetně obrázku produktu, cizího klíče usera a kategorie
    public static boolean addProduct(String productName, int productPrice, String productDescription, byte[] productPhoto, String userEmail, Category category){
        int productID = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Users user = UserDAO.selectUser(userEmail);
            Product product = new Product(productName, productPrice, productDescription, productPhoto, user, category);
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


    // selectuje z databáze product podle jména produktu, vrací celý objekt Product
    public static Product selectProduct(String productName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from Product where productName= :productName");
            query.setParameter("productName", productName);
            Product productFromDb = (Product)query.uniqueResult();
            tx.commit();

            return productFromDb;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return null;
    }

    public static List<Object[]> selectAllProducts(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("select productName, productDescription, productPrice, productPhoto from Product");
            List<Object[]> allProductsFromDb = (List<Object[]>)query.list();
            tx.commit();

            return allProductsFromDb;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return null;
    }

}
