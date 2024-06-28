package com.lab.dao;

import com.lab.models.Owner;
import com.lab.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.TransactionException;

import java.util.List;
import java.util.Optional;

public class OwnerDao {

    public Optional<Owner> findById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Owner owner = null;
        try {
            owner = session.get(Owner.class, id);
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
        return Optional.ofNullable(owner);
    }

    public List<Owner> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Owner> ownerList = List.of();
        try {
           ownerList = session.createQuery("from owner",Owner.class).getResultList();
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
        return ownerList;
    }

    public List<Owner> findByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Owner> ownerList = List.of();
        try {
            ownerList = session.createQuery("from owner where name=:name",Owner.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
        return ownerList;
    }

    public void insert(Owner owner) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(owner);
            session.getTransaction().commit();
        } catch (TransactionException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
    }

    public void update(Owner owner) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.merge(owner);
            session.getTransaction().commit();
        } catch (TransactionException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
    }

    public void delete(Owner owner) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.remove(owner);
            session.getTransaction().commit();
        } catch (TransactionException ex){
            System.out.println(ex.getMessage());
        }
        session.close();
    }
}
