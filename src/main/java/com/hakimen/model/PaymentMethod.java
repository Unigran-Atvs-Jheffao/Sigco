package com.hakimen.model;

import com.hakimen.model.auxiliar.PaymentType;

import javax.persistence.*;

@Entity
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PaymentType type;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;


    public Integer getId() {
        return id;
    }

    public PaymentMethod setId(Integer id) {
        this.id = id;
        return this;
    }


    public PaymentType getType() {
        return type;
    }

    public PaymentMethod setType(PaymentType type) {
        this.type = type;
        return this;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public PaymentMethod setAppointment(Appointment appointment) {
        this.appointment = appointment;
        return this;
    }
}
