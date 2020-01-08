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

    public static boolean addUser(String userEmail, String userName, String userPassword){
        int userId = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Users user = new Users(userEmail,  userName, userPassword);
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

    public static boolean addAdress(String adressCity, String adressCountry, String adressZIP){
        int userId = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Adress adress = new Adress(adressCity,  adressCountry, adressZIP);
            userId = (Integer)session.save(adress);
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

    public static boolean addAdress(String adressCity, String adressCountry, String adressZIP, Users user){
        int userId = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Adress adress = new Adress(adressCity,  adressCountry, adressZIP, user);
            userId = (Integer)session.save(adress);
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
