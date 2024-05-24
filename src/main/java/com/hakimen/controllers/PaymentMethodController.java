package com.hakimen.controllers;

import com.hakimen.controllers.dto.PacientDTO;
import com.hakimen.controllers.dto.PaymentMethodDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.pacient.PacientDAO;
import com.hakimen.persistance.dao.pacient.PacientDAOImpl;
import com.hakimen.persistance.dao.paymentMethod.PaymentMethodDAO;
import com.hakimen.persistance.dao.paymentMethod.PaymentMethodDAOImpl;

public class PaymentMethodController implements Controller<PaymentMethodDTO> {

    private static PaymentMethodDAO PAYMENTMETHOD_DAO = new PaymentMethodDAOImpl();
    public static PaymentMethodDAO gePaymentMethodDAO(){
        return PAYMENTMETHOD_DAO;
    }

    @Override
    public void insert(PaymentMethodDTO type) throws InvalidValueException {

    }

    @Override
    public void remove(PaymentMethodDTO type) throws InvalidValueException {

    }

    @Override
    public void update(PaymentMethodDTO type) throws InvalidValueException {

    }
}
