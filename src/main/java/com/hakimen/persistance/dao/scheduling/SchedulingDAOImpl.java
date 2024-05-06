package com.hakimen.persistance.dao.scheduling;

import com.hakimen.model.Scheduling;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class SchedulingDAOImpl implements SchedulingDAO {
    @Override
    public Scheduling getById(Integer id) throws NoResultException {
        TypedQuery<Scheduling> query = JPAInstance.INSTANCE.getManager().createQuery("select scheduling from Scheduling scheduling where scheduling.id = :id", Scheduling.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Scheduling> getAll() throws NoResultException {
        TypedQuery<Scheduling> query = JPAInstance.INSTANCE.getManager().createQuery("select scheduling from Scheduling scheduling", Scheduling.class);
        return query.getResultList();
    }
}
