package com.hakimen.model;

import com.hakimen.controllers.dto.SchedulingDTO;
import org.eclipse.persistence.jpa.config.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "for_pacient_id")
    private Pacient forPacient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scheduling")
    Scheduling history;

    public Pacient getForPacient() {
        return forPacient;
    }

    public MedicalRecord setForPacient(Pacient forPacient) {
        this.forPacient = forPacient;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public MedicalRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Scheduling getHistory() {
        return history;
    }

    public MedicalRecord setHistory(Scheduling history) {
        this.history = history;
        return this;
    }


}