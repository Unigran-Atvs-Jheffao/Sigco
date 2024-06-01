package com.hakimen.controllers.dto;

import com.hakimen.controllers.dto.auxiliar.PaymentTypeDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.PaymentMethod;

public class PaymentMethodDTO implements DTO<PaymentMethod>{
    private Integer id;
    private SchedulingDTO scheduling;
    private PaymentTypeDTO type;

    public SchedulingDTO getScheduling() {
        return scheduling;
    }

    public PaymentMethodDTO setScheduling(SchedulingDTO scheduling) {
        this.scheduling = scheduling;
        return this;
    }

    public PaymentTypeDTO getType() {
        return type;
    }

    public PaymentMethodDTO setType(PaymentTypeDTO type) {
        this.type = type;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public PaymentMethodDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public PaymentMethodDTO() {
    }

    public PaymentMethodDTO(PaymentMethod paymentMethod) {
        this.id = paymentMethod.getId();
        this.scheduling = new SchedulingDTO(paymentMethod.getScheduling());
        this.type = new PaymentTypeDTO(paymentMethod.getType());
    }

    @Override
    public PaymentMethod build() throws InvalidValueException {
        PaymentMethod method = new PaymentMethod();

        method.setId(id != null && id > 0 ? id : null);

        method.setType(type.build());
        method.setScheduling(scheduling.build());

        return method;
    }

}
