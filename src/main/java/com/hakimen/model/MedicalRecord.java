package com.hakimen.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "pacient_id")
    private Pacient pacient;


    @OneToMany
    @JoinColumn(name = "appointment_id")
    List<Appointment> history;

    public Integer getId() {
        return id;
    }

    public MedicalRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public MedicalRecord setPacient(Pacient pacient) {
        this.pacient = pacient;
        return this;
    }

    public List<Appointment> getHistory() {
        return history;
    }

    public MedicalRecord setHistory(List<Appointment> history) {
        this.history = history;
        return this;
    }
}