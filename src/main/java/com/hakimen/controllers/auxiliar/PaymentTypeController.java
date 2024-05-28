package com.hakimen.controllers.auxiliar;

import com.hakimen.controllers.Controller;
import com.hakimen.controllers.dto.auxiliar.PaymentTypeDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.auxiliar.PaymentType;
import com.hakimen.persistance.dao.auxiliar.paymentType.PaymentTypeDAO;
import com.hakimen.persistance.dao.auxiliar.paymentType.PaymentTypeDAOImpl;

import java.util.List;

public class PaymentTypeController implements Controller<PaymentTypeDTO> {
    private PaymentTypeDAO PAYMENT_TYPE_DAO = new PaymentTypeDAOImpl();

    public PaymentTypeDAO getDAO(){
        return PAYMENT_TYPE_DAO;
    }

    public static PaymentTypeController INSTANCE = new PaymentTypeController();

    private PaymentTypeController() {
    }

    @Override
    public void insert(PaymentTypeDTO type) throws InvalidValueException {
        PAYMENT_TYPE_DAO.insert(type.build());
    }

    @Override
    public void remove(PaymentTypeDTO type) throws InvalidValueException {
        PAYMENT_TYPE_DAO.remove(type.build());
    }

    @Override
    public void update(PaymentTypeDTO type) throws InvalidValueException {
        PAYMENT_TYPE_DAO.update(type.build());
    }

    @Override
    public List<PaymentTypeDTO> getAll() {
        return PAYMENT_TYPE_DAO.getAll().stream().map(PaymentTypeDTO::new).toList();
    }

    @Override
    public PaymentTypeDTO getById(int id) throws InvalidValueException {
        return new PaymentTypeDTO(PAYMENT_TYPE_DAO.getById(id));
    }
}
