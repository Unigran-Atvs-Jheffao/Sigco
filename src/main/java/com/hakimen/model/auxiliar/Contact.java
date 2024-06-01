package com.hakimen.model.auxiliar;

import com.sun.jdi.IntegerType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String value;
    private Integer type;


    public Integer getId() {
        return id;
    }

    public Contact setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Contact setValue(String value) {
        this.value = value;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Contact setType(Integer type) {
        this.type = type;
        return this;
    }
}
