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

    public MedicalRecordDTO(MedicalRecord medicalRecord) {
        id = medicalRecord.getId();
        pacient = medicalRecord.getPacient().getId();
        history = medicalRecord.getHistory().stream().map(Appointment::getId).toList();
    }

    public MedicalRecordDTO() {
    }

    @Override
    public MedicalRecord build() throws InvalidValueException {
        MedicalRecord record = new MedicalRecord();

        record.setId(id != null && id > 0 ? id : null);

        try{
            Pacient pacient = PacientController.INSTANCE.getById(this.pacient).build();
            record.setPacient(pacient);
        } catch (NoResultException e){
            throw new InvalidValueException("Paciente Inválido", e);
        }

        if (history != null) {
            record.setHistory(new ArrayList<>());
            for (Integer ids : history) {
                Appointment appointment;
                try {
                    appointment = AppointmentController.INSTANCE.getById(ids).build();
                } catch (NoResultException e) {
                    throw new InvalidValueException("A consulta com id %s é Inválida".formatted(ids), e);
                }
                record.getHistory().add(appointment);
            }
        }

        return record;
    }
}
