package com.hakimen.controllers.dto;

import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.MedicalRecord;

public class MedicalRecordDTO implements DTO<MedicalRecord> {
    private Integer id;
    private PacientDTO forPacient;
    private SchedulingDTO history;

    public Integer getId() {
        return id;
    }

    public MedicalRecordDTO setId(Integer id) {
        this.id = id;
        return this;
    }


    public SchedulingDTO getHistory() {
        return history;
    }

    public MedicalRecordDTO setHistory(SchedulingDTO history) {
        this.history = history;
        return this;
    }

    public MedicalRecordDTO(MedicalRecord medicalRecord) {
        id = medicalRecord.getId();
        forPacient = new PacientDTO(medicalRecord.getForPacient());
        history = new SchedulingDTO(medicalRecord.getHistory());
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
