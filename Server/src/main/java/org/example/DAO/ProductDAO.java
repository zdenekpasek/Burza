package org.example.DAO;

import Model.Entities.Category;
import Model.Entities.Product;
import Model.Entities.Users;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

// DATA ACESS OBJECT, třída která pracuje s databází, tabulka (Product)
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

    // odstraní produkt z databáze podle productName a userID
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

    // vrácí categoryID kde productName je parametr
    public static int selectCategoryID(String productName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("select category.categoryID from Product WHERE productName = :productName");
            query.setParameter("productName", productName);
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

    // vrátí objekt productu, kde productID je parametr
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

    // updatuje product když byl prodaný na productSold = true
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

    // vrací list produktů
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
