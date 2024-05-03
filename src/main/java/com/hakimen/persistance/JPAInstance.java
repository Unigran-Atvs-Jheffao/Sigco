package com.hakimen.persistance;

import org.osgi.service.jpa.EntityManagerFactoryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAInstance {
    public static final JPAInstance INSTANCE = new JPAInstance();
    EntityManager manager;
    EntityTransaction transaction;
    private JPAInstance() {
        manager = Persistence.createEntityManagerFactory("sigcoPU").createEntityManager();
        transaction = manager.getTransaction();
    }

    public EntityManager getManager() {
        return manager;
    }

    public EntityTransaction getTransaction() {
        return transaction;
    }

    public <T> void save(T obj){
        transaction.begin();
        manager.persist(obj);
        transaction.commit();
    }


    public <T> void remove(T obj){
        transaction.begin();
        manager.remove(obj);
        transaction.commit();
    }


    public <T> void update(T obj){
        transaction.begin();
        manager.merge(obj);
        transaction.commit();
    }
}
