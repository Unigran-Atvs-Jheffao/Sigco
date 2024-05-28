package com.hakimen.persistance.dao.auxiliar.address;

import com.hakimen.model.auxiliar.Address;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AddressDAOImpl implements AddressDAO{
    @Override
    public Address getById(Integer id) throws NoResultException {
        TypedQuery<Address> query = JPAInstance.INSTANCE.getManager().createQuery("select a from Address a where a.id = :id", Address.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Address> getAll() {
        TypedQuery<Address> query = JPAInstance.INSTANCE.getManager().createQuery("select a from Address a", Address.class);
        return query.getResultList();
    }
    
}
