package com.hakimen.persistance.dao.main.paymentMethod;

import com.hakimen.model.Employee;
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

    @Override
    public List<PaymentMethod> findAllFiltered(boolean ascendent, String key, String searchQuery) {
        String builtQuery = "select paymentMethod from PaymentMethod paymentMethod where lower(paymentMethod.scheduling.pacient.name) like :search";

        builtQuery += " order by paymentMethod." + key;
        builtQuery += ascendent ? " asc" : " desc";


        TypedQuery<PaymentMethod> query = JPAInstance.INSTANCE.getManager().createQuery(builtQuery, PaymentMethod.class);

        query.setParameter("search", "%" + searchQuery + "%");
        return query.getResultList();
    }

    @Override
    public PaymentMethod findByScheduleId(int id) {
        TypedQuery<PaymentMethod> query = JPAInstance.INSTANCE.getManager().createQuery("select pay_method from PaymentMethod pay_method where pay_method.scheduling.id = :id", PaymentMethod.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
