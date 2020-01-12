package org.example.DAO;

import Model.Entities.Orders;
import Model.Entities.Users;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

// DATA ACESS OBJECT, třída která pracuje s databází, tabulka (Order)
public class OrderDAO {

    // přidává objednávku do databáze
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

    // selectuje objednávku podle orderNumber, vraíc objekt Orders
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

    // selectuje objednávku podle userID a vrací List Orders
    public static List<Orders> selectAllOrdersObj(int userID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from Orders where userID = :userID");
            query.setParameter("userID", userID);
            List<Orders> allOrdersFromDb = (List<Orders>)query.list();
            tx.commit();

            return allOrdersFromDb;
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
