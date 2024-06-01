package com.hakimen.controllers.dto.auxiliar;

import com.hakimen.controllers.dto.DTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.auxiliar.PaymentType;
import com.hakimen.model.auxiliar.State;

public class PaymentTypeDTO implements DTO<PaymentType> {

    private Integer id;
    private String name;

    public PaymentTypeDTO(PaymentType paymentType) {
        this.id = paymentType.getId();
        this.name = paymentType.getName();
    }

    public PaymentTypeDTO() {
    }

    public Integer getId() {
        return id;
    }

    public PaymentTypeDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PaymentTypeDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public PaymentType build() throws InvalidValueException {
        PaymentType paymentType = new PaymentType();

        paymentType.setId(id != null && id > 0 ? id : null);

        if(name == null || name.isBlank()) throw new InvalidValueException("Nome Inválido");
        paymentType.setName(name);


        return paymentType;
    }
}
