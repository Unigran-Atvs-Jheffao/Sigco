package com.hakimen.persistance.dao.auxiliar.paymentType;

import com.hakimen.model.auxiliar.PaymentType;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class PaymentTypeDAOImpl implements PaymentTypeDAO{
    @Override
    public PaymentType getById(Integer id) throws NoResultException {
        TypedQuery<PaymentType> query = JPAInstance.INSTANCE.getManager().createQuery("select a from PaymentType a where a.id = :id", PaymentType.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<PaymentType> getAll() {
        TypedQuery<PaymentType> query = JPAInstance.INSTANCE.getManager().createQuery("select a from PaymentType a", PaymentType.class);
        return query.getResultList();
    }
    
}
