package org.example.DAO;

import Model.Entities.OrderProduct;
import Model.Entities.Orders;
import Model.Entities.Product;
import Model.Entities.Users;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class OrderProductDAO {

    public static boolean addProductToOrder(int productID, int orderNumber){


        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Product product = ProductDAO.selectProductByProductId(productID);
            Orders order = OrderDAO.selectOrderByOrderNumber(orderNumber);
            OrderProduct orderProduct = new OrderProduct(order, product);
            session.save(orderProduct);
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

    public static List<OrderProduct> selectAllOrderProductsObj(int orderNumber){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("select productID from OrderProduct where orderNumber = :orderNumber");
            query.setParameter("orderNumber", orderNumber);
            List<OrderProduct> allOrderProductsFromDb = (List<OrderProduct>)query.list();
            tx.commit();

            return allOrderProductsFromDb;
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

    public static int selectProductID(int orderNumber){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("select product.productID from OrderProduct where orderNumber = :orderNumber");
            query.setParameter("orderNumber", orderNumber);
            int productID = (int)query.uniqueResult();
            tx.commit();

            return productID;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return 0;
    }


}
