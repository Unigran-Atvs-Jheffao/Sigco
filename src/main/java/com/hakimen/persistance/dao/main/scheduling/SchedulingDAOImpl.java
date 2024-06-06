package com.hakimen.persistance.dao.main.scheduling;

import com.hakimen.model.Material;
import com.hakimen.model.Scheduling;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.Query;
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

    @Override
    public List<Scheduling> findAllFiltered(boolean ascendent, String key, String searchQuery) {
        String builtQuery = "select scheduling from Scheduling scheduling where lower(scheduling.pacient.name) like :search";

        builtQuery += " order by scheduling." + key;
        builtQuery += ascendent ? " asc" : " desc";


        TypedQuery<Scheduling> query = JPAInstance.INSTANCE.getManager().createQuery(builtQuery, Scheduling.class);

        query.setParameter("search", "%" + searchQuery + "%");
        return query.getResultList();
    }

}
