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

    @Override
    public MedicalRecord build() throws InvalidValueException {
        //TODO implementar build  MedicalRecord
        return null;
    }
}
