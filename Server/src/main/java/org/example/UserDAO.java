package org.example;

import Model.Entities.Category;
import Model.Entities.Order;
import Model.Entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class UserDAO {

    public static boolean addUser(String categoryName, String categoryDescription){
        int userId = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Category cat = new Category(categoryName,  categoryDescription);
            userId = (Integer)session.save(cat);
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
