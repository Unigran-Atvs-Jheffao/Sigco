package com.hakimen.controllers.dto;

import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Appointment;
import com.hakimen.model.Role;

public class AppointmentDTO implements DTO<Appointment> {


    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public AppointmentDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AppointmentDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Appointment build() throws InvalidValueException {
        return null;
    }
}
