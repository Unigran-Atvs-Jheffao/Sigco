package com.hakimen.controllers.dto;

import com.hakimen.controllers.AppointmentController;
import com.hakimen.controllers.EmployeeController;
import com.hakimen.controllers.PacientController;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Appointment;
import com.hakimen.model.Employee;
import com.hakimen.model.Pacient;
import com.hakimen.model.Scheduling;

import javax.persistence.NoResultException;
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

    public SchedulingDTO(Scheduling scheduling) {
        this.id = scheduling.getId();
        this.date = scheduling.getDate();
        this.appointmentTime = scheduling.getAppointmentTime();

        this.pacient = scheduling.getPacient().getId();
        this.dentist = scheduling.getDentist().getId();
        this.receptionist = scheduling.getReceptionist().getId();
        this.appointment = scheduling.getAppointment().getId();
    }

    @Override
    public Scheduling build() throws InvalidValueException {
        Scheduling scheduling = new Scheduling();

        scheduling.setId(id != null && id > 0 ? id : null);

        if (date == null) throw new InvalidValueException("Data Inválida");
        scheduling.setDate(date);

        if(appointmentTime == null || appointmentTime.isBlank()) throw new InvalidValueException("Horário Inválido");

        int hours = Integer.parseInt(appointmentTime.substring(0,2));
        int minutes = Integer.parseInt(appointmentTime.substring(3,5));

        if(hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new InvalidValueException("Horário Inválido");
        }

        scheduling.setAppointmentTime(appointmentTime);

        try {
            Employee dentist = EmployeeController.INSTANCE.getById(this.dentist).build();
            if (dentist.getLogin().getRole().getId() == 2)
                scheduling.setDentist(dentist);
            else
                throw new InvalidValueException("O funcionário não é um dentista");
        } catch (NoResultException e) {
            throw new InvalidValueException("Id de funcionário inválido", e);
        }

        try {
            Employee receptionist = EmployeeController.INSTANCE.getById(this.dentist).build();
            if (receptionist.getLogin().getRole().getId() == 3)
                scheduling.setReceptionist(receptionist);
            else
                throw new InvalidValueException("O funcionário não é um recepcionista");
        } catch (NoResultException e) {
            throw new InvalidValueException("Id de funcionário inválido", e);
        }

        try {
            Appointment appointment = AppointmentController.INSTANCE.getById(this.appointment).build();
            scheduling.setAppointment(appointment);
        } catch (NoResultException e) {
            throw new InvalidValueException("Consulta Inválida", e);
        }

        try{
            Pacient pacient = PacientController.INSTANCE.getById(this.pacient).build();
            scheduling.setPacient(pacient);
        } catch (NoResultException e){
            throw new InvalidValueException("Paciente Inválido", e);
        }

        return scheduling;
    }
}
