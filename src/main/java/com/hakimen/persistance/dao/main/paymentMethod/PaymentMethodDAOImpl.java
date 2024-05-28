package com.hakimen.persistance.dao.main.paymentMethod;

import com.hakimen.model.PaymentMethod;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class PaymentMethodDAOImpl implements PaymentMethodDAO {
    @Override
    public PaymentMethod getById(Integer id) throws NoResultException {
        TypedQuery<PaymentMethod> query = JPAInstance.INSTANCE.getManager().createQuery("select pay_method from PaymentMethod pay_method where pay_method.id = :id", PaymentMethod.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<PaymentMethod> getAll() {
        TypedQuery<PaymentMethod> query = JPAInstance.INSTANCE.getManager().createQuery("select pay_method from PaymentMethod pay_method", PaymentMethod.class);
        return query.getResultList();
    }
}
