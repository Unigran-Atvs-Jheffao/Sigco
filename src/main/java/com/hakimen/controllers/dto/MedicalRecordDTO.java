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
    private List<AppointmentDTO> history;

    public Integer getId() {
        return id;
    }

    public MedicalRecordDTO setId(Integer id) {
        this.id = id;
        return this;
    }


    public List<AppointmentDTO> getHistory() {
        return history;
    }

    public MedicalRecordDTO setHistory(List<AppointmentDTO> history) {
        this.history = history;
        return this;
    }

    public MedicalRecordDTO(MedicalRecord medicalRecord) {
        id = medicalRecord.getId();
        history = medicalRecord.getHistory().stream().map(AppointmentDTO::new).toList();
    }

    public MedicalRecordDTO() {
    }

    @Override
    public MedicalRecord build() throws InvalidValueException {
        MedicalRecord record = new MedicalRecord();

        record.setId(id != null && id > 0 ? id : null);

        if (history != null) {
            record.setHistory(new ArrayList<>());
            for (AppointmentDTO appointmentDTO : history) {
                record.getHistory().add(appointmentDTO.build());
            }
        }

        return record;
    }
}
