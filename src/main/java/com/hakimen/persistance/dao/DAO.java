package com.hakimen.persistance.dao;

import javax.persistence.NoResultException;
import java.util.List;

public interface DAO<ID, T> {
    void insert(T obj);
    void remove(T obj);
    void update(T obj);
    T getById(ID id) throws NoResultException;
    List<T> getAll() throws NoResultException;
}
