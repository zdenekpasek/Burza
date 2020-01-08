package org.example;

import Model.Entities.Adress;
import Model.Entities.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAO {

    public static boolean addUser(Users user){
        int userId = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
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
