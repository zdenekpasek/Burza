package org.example.DAO;

import Model.Entities.Adress;
import Model.Entities.Users;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAO {


    public static Users selectUser(String email){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from Users where userEmail= :email");
            query.setParameter("email", email);
            Users userFromDb = (Users)query.uniqueResult();
            tx.commit();

            return userFromDb;
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


    public static boolean authUser(String email){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("select userEmail from Users where userEmail= :email");
            query.setParameter("email", email);
            String emailFromDb = (String)query.uniqueResult();
            tx.commit();
            if(email.equals(emailFromDb)){
                return true;
            }
            return false;
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

    public static String getHash(String email){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("select userPassword from Users where userEmail= :email");
            query.setParameter("email", email);
            String hashFromDb = (String)query.uniqueResult();
            tx.commit();
            return hashFromDb;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return "false";
    }

}
