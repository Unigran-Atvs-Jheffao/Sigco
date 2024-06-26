package com.hakimen.persistance.dao.main.login;

import com.hakimen.model.Login;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoginDAOImpl implements LoginDAO{

    @Override
    public Login getById(Integer id) throws NoResultException {
        TypedQuery<Login> query = JPAInstance.INSTANCE.getManager().createQuery("select l from Login l where l.id = :id", Login.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Login> getAll() {
        TypedQuery<Login> query = JPAInstance.INSTANCE.getManager().createQuery("select l from Login l", Login.class);
        return query.getResultList();
    }
}
