package com.hakimen.persistance.dao.dentist;

import com.hakimen.model.Dentist;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class DentistDAOImpl implements DentistDAO {
    @Override
    public Dentist getById(Integer id) throws NoResultException {
        TypedQuery<Dentist> query = JPAInstance.INSTANCE.getManager().createQuery("select dent from Dentist dent where dent.cro = :id", Dentist.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Dentist> getAll() {
        TypedQuery<Dentist> query = JPAInstance.INSTANCE.getManager().createQuery("select dent from Dentist dent", Dentist.class);
        return query.getResultList();
    }
}
