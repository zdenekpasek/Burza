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

    public static boolean removeProduct(String productName, int userID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from Product where productName= :productName AND userID= :userID");
            query.setParameter("productName", productName);
            query.setParameter("userID", userID);
            query.executeUpdate();
            tx.commit();

            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return false;
    }

    public static int selectCategoryID(String productName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("select category.categoryID from Product WHERE productName = :productName");
            query.setParameter("productName", productName);
//            Category test = (Category) query.uniqueResult();
            int categoryID = (int)query.uniqueResult();
            tx.commit();

            return categoryID;
        } catch (Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return 0;
    }

    public static Product selectProductByProductId(int productID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from Product WHERE productID = :productID");
            query.setParameter("productID", productID);
            Product product = (Product)query.uniqueResult();
            tx.commit();

            return product;
        } catch (Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return null;
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

    public static boolean updateProductByID(int productID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("update Product set productSold= 'true' where productID = :productID");
            query.setParameter("productID", productID);
            query.executeUpdate();
            tx.commit();

            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return false;
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

    public static List<Product> selectAllProductsObj(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from Product");
            List<Product> allProductsFromDb = (List<Product>)query.list();
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
