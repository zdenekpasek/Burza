package org.example.DAO;

import Model.Entities.Orders;
import Model.Entities.Users;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class OrderDAO {


    public static boolean addOrder(Date orderDate, String orderStatus, String userEmail){
        int productID = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Users user = UserDAO.selectUser(userEmail);
            Orders order = new Orders(orderDate, orderStatus, user);
            productID = (Integer)session.save(order);
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
