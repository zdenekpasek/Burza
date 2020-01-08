package org.example;

import Model.Entities.Category;
import Model.Entities.Order;
import Model.Entities.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class UserDAO {

    public static boolean addUser(String userEmail, String userName, String userPassword){
        int userId = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            User user = new User(userEmail,  userName, userPassword);
            userId = (Integer)session.save(user);
            tx.commit();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return userId > 0;

    }

    public static boolean authUser(){
        int userId = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();


            tx.commit();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return userId > 0;

    }


}
