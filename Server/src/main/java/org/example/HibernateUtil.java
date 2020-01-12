package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// třída pro vytvoření sessionFactory
// session potřebná pro Hibernate dotazy
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static{
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex){
            System.err.println("Err" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
