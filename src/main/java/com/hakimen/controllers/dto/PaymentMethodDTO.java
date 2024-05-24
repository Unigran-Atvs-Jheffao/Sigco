package com.hakimen.controllers.dto;

import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Appointment;
import com.hakimen.model.PaymentMethod;

public class PaymentMethodDTO implements DTO<PaymentMethod>{
    private Integer id;
    private Integer appointment;

    public Integer getAppointment() {
        return appointment;
    }

    public PaymentMethodDTO setAppointment(Integer appointment) {
        this.appointment = appointment;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public PaymentMethodDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public PaymentMethod build() throws InvalidValueException {
        //TODO IMPLEMENTAR BUILD
        return null;
    }

}
