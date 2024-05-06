package com.hakimen.persistance.dao.manager;

import com.hakimen.model.Login;
import com.hakimen.model.Manager;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ManagerDAOImpl implements ManagerDAO {
    @Override
    public Manager getById(Integer id) throws NoResultException {
        TypedQuery<Manager> query = JPAInstance.INSTANCE.getManager().createQuery("select manager from Manager manager where manager.id = :id", Manager.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Manager> getAll() throws NoResultException {
        TypedQuery<Manager> query = JPAInstance.INSTANCE.getManager().createQuery("select manager from Manager manager", Manager.class);
        return query.getResultList();
    }
}
