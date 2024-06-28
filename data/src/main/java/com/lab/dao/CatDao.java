package com.lab.dao;

import com.lab.models.Color;
import com.lab.models.Cat;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.TransactionException;
import com.lab.utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class CatDao {

    public Optional<Cat> findByID(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Cat cat = null;
        try {
            cat = session.find(Cat.class, id);
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
        return Optional.ofNullable(cat);
    }

    public List<Cat> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cat> catList = List.of();
        try {
            catList = session.createQuery("from cat",Cat.class).getResultList();
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
        return catList;
    }

    public List<Cat> findByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cat> catList = List.of();
        try {
            catList = session.createQuery("from cat where name=:name",Cat.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
        return catList;
    }

    public List<Cat> findByBreed(String breed){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cat> catList = List.of();
        try {
            catList = session.createQuery("from cat where breed=:breed",Cat.class)
                    .setParameter("breed", breed)
                    .getResultList();
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
        return catList;
    }

    public List<Cat> findByColor(Color color){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cat> catList = List.of();
        try {
            catList = session.createQuery("from cat where color=:color",Cat.class)
                    .setParameter("color", color.name())
                    .getResultList();
        } catch (HibernateException ex){
            System.out.print(ex.getMessage());
        }
        session.close();
        return catList;
    }


    public void insert(Cat cat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(cat);
            session.getTransaction().commit();
        } catch (TransactionException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
    }

    public void update(Cat cat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.merge(cat);
            session.getTransaction().commit();
        } catch (TransactionException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
    }

    public void delete(Cat cat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.remove(cat);
            session.getTransaction().commit();
        } catch (TransactionException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
    }

    public void addFriend(Cat cat1, Cat cat2){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            cat1.getFriendList().add(cat2);
            cat2.getFriendList().add(cat1);
            session.merge(cat1);
            session.merge(cat2);
            session.getTransaction().commit();
        } catch (TransactionException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
    }

    public void removeFriend(Cat cat1, Cat cat2){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            cat1.getFriendList().remove(cat2);
            cat2.getFriendList().remove(cat1);
            session.merge(cat1);
            session.merge(cat2);
            session.getTransaction().commit();
        } catch (TransactionException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
    }
}
