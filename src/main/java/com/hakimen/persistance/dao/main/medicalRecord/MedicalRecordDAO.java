package com.hakimen.persistance.dao.main.medicalRecord;

import com.hakimen.controllers.dto.MedicalRecordDTO;
import com.hakimen.controllers.dto.PacientDTO;
import com.hakimen.model.Appointment;
import com.hakimen.model.MedicalRecord;
import com.hakimen.persistance.dao.DAO;

import java.util.List;

public interface MedicalRecordDAO extends DAO<Integer, MedicalRecord> {
    List<MedicalRecord> findAllFiltered(String pacientName, boolean ascendent, String key, String searchQuery);

    List<MedicalRecord> getByPacient(Integer pacientId);

    MedicalRecord getByAppointment(Integer appointmentId);
}
