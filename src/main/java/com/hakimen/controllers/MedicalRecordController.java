package com.hakimen.controllers;

import com.hakimen.controllers.dto.MedicalRecordDTO;
import com.hakimen.controllers.dto.PacientDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.MedicalRecord;
import com.hakimen.persistance.dao.main.medicalRecord.MedicalRecordDAO;
import com.hakimen.persistance.dao.main.medicalRecord.MedicalRecordDAOImpl;

import java.util.List;

public class MedicalRecordController implements Controller<MedicalRecordDTO>{

    public static MedicalRecordController INSTANCE = new MedicalRecordController();

    private MedicalRecordController(){

    };


    private MedicalRecordDAO MEDICAL_RECORD_DAO = new MedicalRecordDAOImpl();
    public MedicalRecordDAO getDAO(){
        return MEDICAL_RECORD_DAO;
    }


    @Override
    public void insert(MedicalRecordDTO type) throws InvalidValueException {
        MEDICAL_RECORD_DAO.insert(type.build());
    }

    @Override
    public void remove(MedicalRecordDTO type) throws InvalidValueException {
        MEDICAL_RECORD_DAO.remove(type.build());
    }

    @Override
    public void update(MedicalRecordDTO type) throws InvalidValueException {
        MEDICAL_RECORD_DAO.update(type.build());
    }

    @Override
    public List<MedicalRecordDTO> getAll() {
        return MEDICAL_RECORD_DAO.getAll().stream().map(MedicalRecordDTO::new).toList();
    }

    @Override
    public MedicalRecordDTO getById(int id) throws InvalidValueException {
        return new MedicalRecordDTO(MEDICAL_RECORD_DAO.getById(id));
    }

    public List<MedicalRecordDTO> findAllFiltered(String pacientName, boolean asc, String key, String query){
        return MEDICAL_RECORD_DAO.findAllFiltered(pacientName, asc,key,query).stream().map(MedicalRecordDTO::new).toList();
    }

    public List<MedicalRecordDTO> getByPacient(Integer pacientId) {
        return MEDICAL_RECORD_DAO.getByPacient(pacientId).stream().map(MedicalRecordDTO::new).toList();
    }

    public MedicalRecordDTO getByAppointment(Integer appoitmentId) {
        return new MedicalRecordDTO(MEDICAL_RECORD_DAO.getByAppointment(appoitmentId));
    }
}
