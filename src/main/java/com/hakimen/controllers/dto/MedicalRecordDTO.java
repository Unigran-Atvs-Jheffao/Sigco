package com.hakimen.controllers.dto;

import com.hakimen.controllers.AppointmentController;
import com.hakimen.controllers.PacientController;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Appointment;
import com.hakimen.model.MedicalRecord;
import com.hakimen.model.Pacient;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDTO implements DTO<MedicalRecord> {
    private Integer id;
    private PacientDTO forPacient;
    private AppointmentDTO history;

    public Integer getId() {
        return id;
    }

    public MedicalRecordDTO setId(Integer id) {
        this.id = id;
        return this;
    }


    public AppointmentDTO getHistory() {
        return history;
    }

    public MedicalRecordDTO setHistory(AppointmentDTO history) {
        this.history = history;
        return this;
    }

    public MedicalRecordDTO(MedicalRecord medicalRecord) {
        id = medicalRecord.getId();
        forPacient = new PacientDTO(medicalRecord.getForPacient());
        history = new AppointmentDTO(medicalRecord.getHistory());
    }

    public MedicalRecordDTO() {
    }

    public PacientDTO getForPacient() {
        return forPacient;
    }

    public MedicalRecordDTO setForPacient(PacientDTO forPacient) {
        this.forPacient = forPacient;
        return this;
    }

    @Override
    public MedicalRecord build() throws InvalidValueException {
        MedicalRecord record = new MedicalRecord();

        record.setId(id != null && id > 0 ? id : null);

        if (forPacient != null)
            record.setForPacient(forPacient.build());
        else
            throw new InvalidValueException("Paciente Inválido");

        if (history != null)
            record.setHistory(history.build());
        else
            throw new InvalidValueException("Consulta Inválida");

        return record;
    }


}
