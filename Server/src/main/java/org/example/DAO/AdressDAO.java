package org.example.DAO;

import Model.Entities.Adress;
import Model.Entities.Users;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdressDAO {

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
}
