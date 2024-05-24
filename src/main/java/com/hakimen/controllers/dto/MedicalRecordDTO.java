package com.hakimen.controllers.dto;

import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Appointment;
import com.hakimen.model.MedicalRecord;
import com.hakimen.model.Pacient;

import java.util.List;

public class MedicalRecordDTO implements DTO<MedicalRecord> {
    private Integer id;
    private Integer pacient;
    private List<Integer> history;

    public Integer getId() {
        return id;
    }

    public MedicalRecordDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPacient() {
        return pacient;
    }

    public MedicalRecordDTO setPacient(Integer pacient) {
        this.pacient = pacient;
        return this;
    }

    public List<Integer> getHistory() {
        return history;
    }

    public MedicalRecordDTO setHistory(List<Integer> history) {
        this.history = history;
        return this;
    }

    @Override
    public MedicalRecord build() throws InvalidValueException {
        //TODO implementar build  MedicalRecord
        return null;
    }
}
