package com.hakimen.model.auxiliar;

import javax.persistence.*;

@Entity
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    public Integer getId() {
        return id;
    }

    public PaymentType setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PaymentType setName(String name) {
        this.name = name;
        return this;
    }
}
