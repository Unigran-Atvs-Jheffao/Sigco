package com.hakimen.persistance.dao.main.paymentMethod;

import com.hakimen.model.Employee;
import com.hakimen.model.PaymentMethod;
import com.hakimen.persistance.dao.DAO;

import java.util.List;

public interface PaymentMethodDAO extends DAO<Integer, PaymentMethod> {
    List<PaymentMethod> findAllFiltered(boolean ascendent, String key, String searchQuery);

    PaymentMethod findByScheduleId(int id);
}
