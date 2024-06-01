package com.hakimen.model;

import javax.persistence.*;
import java.util.Date;

@Entity

public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;
    private String appointmentTime;

    @ManyToOne
    @JoinColumn(name = "pacient_id")
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private Employee dentist;

    @ManyToOne
    @JoinColumn(name = "receptionist_id")
    private Employee receptionist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;


    public Integer getId() {
        return id;
    }

    public Scheduling setId(Integer id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Scheduling setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public Scheduling setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
        return this;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public Scheduling setPacient(Pacient pacient) {
        this.pacient = pacient;
        return this;
    }

    public Employee getDentist() {
        return dentist;
    }

    public Scheduling setDentist(Employee dentist) {
        this.dentist = dentist;
        return this;
    }

    public Employee getReceptionist() {
        return receptionist;
    }

    public Scheduling setReceptionist(Employee receptionist) {
        this.receptionist = receptionist;
        return this;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Scheduling setAppointment(Appointment appointment) {
        this.appointment = appointment;
        return this;
    }
}