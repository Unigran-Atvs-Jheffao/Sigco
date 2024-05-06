package com.hakimen.persistance.dao.receptionist;

import com.hakimen.model.Receptionist;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReceptionistDAOImpl implements ReceptionistDAO {
    @Override
    public Receptionist getById(Integer id) throws NoResultException {
        TypedQuery<Receptionist> query = JPAInstance.INSTANCE.getManager().createQuery("select receptionist from Receptionist receptionist where receptionist.id = :id", Receptionist.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Receptionist> getAll() throws NoResultException {
        TypedQuery<Receptionist> query = JPAInstance.INSTANCE.getManager().createQuery("select receptionist from Receptionist receptionist", Receptionist.class);
        return query.getResultList();
    }
}
