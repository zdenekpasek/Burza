package org.example.DAO;

import Model.Entities.OrderProduct;
import Model.Entities.Orders;
import Model.Entities.Product;
import Model.Entities.Users;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

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


}
