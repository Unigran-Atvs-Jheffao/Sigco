package com.hakimen.controllers;

import com.hakimen.controllers.dto.MedicalRecordDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.MedicalRecord;
import com.hakimen.persistance.dao.medicalRecord.MedicalRecordDAO;
import com.hakimen.persistance.dao.medicalRecord.MedicalRecordDAOImpl;

import javax.persistence.NoResultException;
import java.util.List;

public class MedicalRecordController implements Controller<MedicalRecordDTO>{
    private static MedicalRecordDAO MEDICALRECORD_DAO = new MedicalRecordDAOImpl();

    @Override
    public void insert(MedicalRecordDTO type) throws InvalidValueException {

    }

    @Override
    public void remove(MedicalRecordDTO type) throws InvalidValueException {

    }

    @Override
    public void update(MedicalRecordDTO type) throws InvalidValueException {

    }
}
