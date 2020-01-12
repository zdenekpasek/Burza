package org.example.DAO;

import Model.Entities.Orders;
import Model.Entities.Product;
import Model.Entities.Users;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.Date;

public class OrderDAO {


    public static Orders addOrder(LocalDateTime orderDate, String orderStatus, String userEmail){
        int productID = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Users user = UserDAO.selectUser(userEmail);
            Orders order = new Orders(orderDate, orderStatus, user);
            productID = (Integer)session.save(order);
            tx.commit();
            return order;
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

    public static Orders selectOrderByOrderNumber(int orderNumber){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from Orders WHERE orderNumber = :orderNumber");
            query.setParameter("orderNumber", orderNumber);
            Orders order = (Orders)query.uniqueResult();
            tx.commit();

            return order;
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
}
