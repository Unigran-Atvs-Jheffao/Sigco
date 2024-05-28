package com.hakimen.persistance.dao.main.pacient;

import com.hakimen.model.Pacient;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class PacientDAOImpl implements PacientDAO {
    @Override
    public Pacient getById(Integer id) throws NoResultException {
        TypedQuery<Pacient> query = JPAInstance.INSTANCE.getManager().createQuery("select pacient from Pacient pacient where pacient.id = :id", Pacient.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Pacient> getAll() throws NoResultException {
        TypedQuery<Pacient> query = JPAInstance.INSTANCE.getManager().createQuery("select pacient from Pacient pacient", Pacient.class);
        return query.getResultList();
    }
}
