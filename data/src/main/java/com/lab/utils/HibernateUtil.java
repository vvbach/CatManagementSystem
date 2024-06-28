package com.lab.utils;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null){
            try {
                sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            } catch (RuntimeException ex) {
                System.out.println("Session factory created failed");
            }
        }

        return sessionFactory;
    }
    public static void shutdown(){
        getSessionFactory().close();
    }
}
