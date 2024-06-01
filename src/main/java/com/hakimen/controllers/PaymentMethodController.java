package com.hakimen.controllers;

import com.hakimen.controllers.dto.PacientDTO;
import com.hakimen.controllers.dto.PaymentMethodDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.main.pacient.PacientDAO;
import com.hakimen.persistance.dao.main.pacient.PacientDAOImpl;
import com.hakimen.persistance.dao.main.paymentMethod.PaymentMethodDAO;
import com.hakimen.persistance.dao.main.paymentMethod.PaymentMethodDAOImpl;

import java.util.List;

public class PaymentMethodController implements Controller<PaymentMethodDTO> {

    public static PaymentMethodController INSTANCE = new PaymentMethodController();

    private PaymentMethodController(){

    };

    private PaymentMethodDAO PAYMENT_METHOD_DAO = new PaymentMethodDAOImpl();
    public PaymentMethodDAO getDAO(){
        return PAYMENT_METHOD_DAO;
    }

    @Override
    public void insert(PaymentMethodDTO type) throws InvalidValueException {
        PAYMENT_METHOD_DAO.insert(type.build());
    }

    @Override
    public void remove(PaymentMethodDTO type) throws InvalidValueException {
        PAYMENT_METHOD_DAO.remove(type.build());
    }

    @Override
    public void update(PaymentMethodDTO type) throws InvalidValueException {
        PAYMENT_METHOD_DAO.update(type.build());
    }

    @Override
    public List<PaymentMethodDTO> getAll() {
        return PAYMENT_METHOD_DAO.getAll().stream().map(PaymentMethodDTO::new).toList();
    }

    @Override
    public PaymentMethodDTO getById(int id) throws InvalidValueException {
        return new PaymentMethodDTO(PAYMENT_METHOD_DAO.getById(id));
    }

    public List<PaymentMethodDTO> findAllFiltered(boolean asc, String key, String search) {
        return PAYMENT_METHOD_DAO.findAllFiltered(asc,key,search).stream().map(PaymentMethodDTO::new).toList();
    }

    public PaymentMethodDTO findByScheduleId(int scheduleId) throws InvalidValueException {
        return new PaymentMethodDTO(PAYMENT_METHOD_DAO.findByScheduleId(scheduleId));
    }
}
