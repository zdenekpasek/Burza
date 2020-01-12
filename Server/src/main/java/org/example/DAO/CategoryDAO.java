package org.example.DAO;

import Model.Entities.Category;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

// DATA ACESS OBJECT, třída která pracuje s databází, tabulka (Category)
public class CategoryDAO {

    // odstraní kategorii podle categoryID, vrátí true nebo false
    public static boolean deleteCategory(int categoryID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from Category where categoryID= :categoryID");
            query.setParameter("categoryID", categoryID);
            query.executeUpdate();
            tx.commit();

            return true;
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

}
