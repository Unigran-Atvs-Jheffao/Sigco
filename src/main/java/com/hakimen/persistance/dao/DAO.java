package com.hakimen.persistance.dao;

import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import java.util.List;

public interface DAO<ID, T> {
    default void insert(T obj) {
        JPAInstance.INSTANCE.save(obj);
    }

    default void remove(T obj) {
        JPAInstance.INSTANCE.remove(obj);
    }

    default void update(T obj) {
        JPAInstance.INSTANCE.update(obj);
    }

    T getById(ID id) throws NoResultException;

    List<T> getAll() throws NoResultException;
}
