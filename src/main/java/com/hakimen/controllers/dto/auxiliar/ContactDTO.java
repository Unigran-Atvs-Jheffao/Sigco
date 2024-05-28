package com.hakimen.controllers.dto.auxiliar;

import com.hakimen.controllers.dto.DTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.auxiliar.Contact;

import java.io.Serializable;

public class ContactDTO implements DTO<Contact> {

    private Integer id;
    private String value;
    private Integer type;

    public ContactDTO() {
    }

    public ContactDTO(Contact contact) {
        this.id = contact.getId();
        this.value = contact.getValue();
        this.type = contact.getType();
    }

    public Integer getId() {
        return id;
    }

    public ContactDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ContactDTO setValue(String value) {
        this.value = value;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ContactDTO setType(Integer type) {
        this.type = type;
        return this;
    }

    @Override
    public Contact build() throws InvalidValueException {
        return null;
    }
}
