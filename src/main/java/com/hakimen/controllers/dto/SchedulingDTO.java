package com.hakimen.controllers.dto;

import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Appointment;
import com.hakimen.model.Employee;
import com.hakimen.model.Pacient;
import com.hakimen.model.Scheduling;

import java.util.Date;

public class SchedulingDTO implements DTO<Scheduling>{
    private Integer id;
    private Date date;
    private String appointmentTime;
    private Integer pacient;
    private Integer dentist;
    private Integer receptionist;
    private Integer appointment;

    public Integer getId() {
        return id;
    }

    public SchedulingDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public SchedulingDTO setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public SchedulingDTO setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
        return this;
    }

    public Integer getPacient() {
        return pacient;
    }

    public SchedulingDTO setPacient(Integer pacient) {
        this.pacient = pacient;
        return this;
    }

    public Integer getDentist() {
        return dentist;
    }

    public SchedulingDTO setDentist(Integer dentist) {
        this.dentist = dentist;
        return this;
    }

    public Integer getReceptionist() {
        return receptionist;
    }

    public SchedulingDTO setReceptionist(Integer receptionist) {
        this.receptionist = receptionist;
        return this;
    }

    public Integer getAppointment() {
        return appointment;
    }

    public SchedulingDTO setAppointment(Integer appointment) {
        this.appointment = appointment;
        return this;
    }

    @Override
    public Scheduling build() throws InvalidValueException {
        //TODO IMPLEMENTAR BUILD
        return null;
    }
}
