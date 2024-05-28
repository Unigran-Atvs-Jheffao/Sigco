package com.hakimen.persistance.dao.auxiliar.city;

import com.hakimen.model.auxiliar.City;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class CityDAOImpl implements CityDAO {
    @Override
    public City getById(Integer id) throws NoResultException {
        TypedQuery<City> query = JPAInstance.INSTANCE.getManager().createQuery("select a from City a where a.id = :id", City.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<City> getAll() {
        TypedQuery<City> query = JPAInstance.INSTANCE.getManager().createQuery("select a from City a", City.class);
        return query.getResultList();
    }
}
