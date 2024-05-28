package com.hakimen.controllers.dto;

import com.hakimen.controllers.AppointmentController;
import com.hakimen.controllers.auxiliar.PaymentTypeController;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Appointment;
import com.hakimen.model.PaymentMethod;
import com.hakimen.model.auxiliar.PaymentType;

import javax.persistence.NoResultException;

public class PaymentMethodDTO implements DTO<PaymentMethod>{
    private Integer id;
    private Integer appointmentId;
    private Integer paymentTypeId;

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public PaymentMethodDTO setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
        return this;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public PaymentMethodDTO setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public PaymentMethodDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public PaymentMethodDTO(PaymentMethod paymentMethod) {
        this.id = paymentMethod.getId();
        this.appointmentId = paymentMethod.getAppointment().getId();
        this.paymentTypeId = paymentMethod.getType().getId();
    }

    @Override
    public PaymentMethod build() throws InvalidValueException {
        PaymentMethod method = new PaymentMethod();

        method.setId(id != null && id > 0 ? id : null);

        try {
            Appointment appointment = AppointmentController.INSTANCE.getById(appointmentId).build();
            method.setAppointment(appointment);
        } catch (NoResultException e) {
            throw new InvalidValueException("Consulta Inválida", e);
        }

        try {
            PaymentType paymentType = PaymentTypeController.INSTANCE.getById(paymentTypeId).build();
            method.setType(paymentType);
        } catch (NoResultException e) {
            throw new InvalidValueException("Tipo de Pagamento Inválido", e);
        }

        return null;
    }

}
