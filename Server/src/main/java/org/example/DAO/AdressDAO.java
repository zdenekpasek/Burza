package org.example.DAO;

import Model.Entities.Adress;
import Model.Entities.Users;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdressDAO {

    // přídá adresu do databáze včetně cizího klíče Usera, podle kterého vím, která adresa patří určitému uživateli
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

    public static List<String> getAdressDetails(String userID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("select adressCity, adressCountry from Adress where userID= : userID");
            query.setParameter("userID", userID);
            List<String> userDetails = (List<String>)query.list();
            tx.commit();
            return userDetails;
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
