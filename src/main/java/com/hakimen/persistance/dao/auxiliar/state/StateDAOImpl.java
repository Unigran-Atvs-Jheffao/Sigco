package com.hakimen.persistance.dao.auxiliar.state;

import com.hakimen.model.auxiliar.State;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class StateDAOImpl implements StateDAO{
    @Override
    public State getById(Integer id) throws NoResultException {
        TypedQuery<State> query = JPAInstance.INSTANCE.getManager().createQuery("select a from State a where a.id = :id", State.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<State> getAll() {
        TypedQuery<State> query = JPAInstance.INSTANCE.getManager().createQuery("select a from State a", State.class);
        return query.getResultList();
    }
}
