package com.hakimen.controllers.dto;

import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Scheduling;

import java.util.Date;

public class SchedulingDTO implements DTO<Scheduling>{
    private Integer id;
    private Date date;
    private String appointmentTime;
    private PacientDTO pacient;
    private EmployeeDTO dentist;
    private EmployeeDTO receptionist;
    private AppointmentDTO appointment;

    public SchedulingDTO() {

    }

    public Integer getId() {
        return id;
    }

    public SchedulingDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public EmployeeDTO getDentist() {
        return dentist;
    }

    public SchedulingDTO setDentist(EmployeeDTO dentist) {
        this.dentist = dentist;
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

    public PacientDTO getPacient() {
        return pacient;
    }

    public SchedulingDTO setPacient(PacientDTO pacient) {
        this.pacient = pacient;
        return this;
    }

    public EmployeeDTO getReceptionist() {
        return receptionist;
    }

    public SchedulingDTO setReceptionist(EmployeeDTO receptionist) {
        this.receptionist = receptionist;
        return this;
    }

    public AppointmentDTO getAppointment() {
        return appointment;
    }

    public SchedulingDTO setAppointment(AppointmentDTO appointment) {
        this.appointment = appointment;
        return this;
    }

    public SchedulingDTO(Scheduling scheduling) {
        this.id = scheduling.getId();
        this.date = scheduling.getDate();
        this.appointmentTime = scheduling.getAppointmentTime();
        this.dentist = new EmployeeDTO(scheduling.getDentist());
        this.pacient = new PacientDTO(scheduling.getPacient());
        this.receptionist = new EmployeeDTO(scheduling.getReceptionist());
        this.appointment = new AppointmentDTO(scheduling.getAppointment());
    }

    @Override
    public Scheduling build() throws InvalidValueException {
        Scheduling scheduling = new Scheduling();

        scheduling.setId(id != null && id > 0 ? id : null);

        if (date == null) throw new InvalidValueException("Data Inválido");
        scheduling.setDate(date);

        if(appointmentTime == null || appointmentTime.isBlank()) throw new InvalidValueException("Horário Inválido");

        int hours = Integer.parseInt(appointmentTime.substring(0,2));
        int minutes = Integer.parseInt(appointmentTime.substring(3,5));

        if(hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new InvalidValueException("Horário Inválido");
        }

        scheduling.setAppointmentTime(appointmentTime);

        scheduling.setReceptionist(receptionist.build());
        scheduling.setDentist(dentist.build());
        scheduling.setPacient(pacient.build());
        scheduling.setAppointment(appointment.build());

        return scheduling;
    }
}
