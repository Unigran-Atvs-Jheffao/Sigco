package com.hakimen.persistance;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

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
        manager.getTransaction().begin();
        manager.persist(obj);
        manager.getTransaction().commit();
    }


    public <T> void remove(T obj){
        manager.getTransaction().begin();
        Object managed = manager.merge(obj);
        manager.remove(managed);
        manager.getTransaction().commit();
    }


    public <T> void update(T obj){
        manager.getTransaction().begin();
        manager.merge(obj);
        manager.getTransaction().commit();
    }

}
