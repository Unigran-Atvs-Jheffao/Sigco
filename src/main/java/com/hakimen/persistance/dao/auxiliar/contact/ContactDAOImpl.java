package com.hakimen.persistance.dao.auxiliar.contact;

import com.hakimen.model.auxiliar.Contact;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ContactDAOImpl implements ContactDAO{
    @Override
    public Contact getById(Integer id) throws NoResultException {
        TypedQuery<Contact> query = JPAInstance.INSTANCE.getManager().createQuery("select a from Contact a where a.id = :id", Contact.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Contact> getAll() {
        TypedQuery<Contact> query = JPAInstance.INSTANCE.getManager().createQuery("select a from Contact a", Contact.class);
        return query.getResultList();
    }
}
