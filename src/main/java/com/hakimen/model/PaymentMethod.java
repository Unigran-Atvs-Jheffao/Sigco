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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scheduling_id")
    private Scheduling scheduling;


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

    public Scheduling getScheduling() {
        return scheduling;
    }

    public PaymentMethod setScheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
        return this;
    }
}
