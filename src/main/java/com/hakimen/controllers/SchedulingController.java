package com.hakimen.controllers;

import com.hakimen.controllers.dto.MedicalRecordDTO;
import com.hakimen.controllers.dto.SchedulingDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.medicalRecord.MedicalRecordDAO;
import com.hakimen.persistance.dao.medicalRecord.MedicalRecordDAOImpl;
import com.hakimen.persistance.dao.scheduling.SchedulingDAO;
import com.hakimen.persistance.dao.scheduling.SchedulingDAOImpl;

public class SchedulingController implements Controller<SchedulingDTO>{


    private static SchedulingDAO SCHEDULING_DAO = new SchedulingDAOImpl();
    public static SchedulingDAO getSchedulingDao(){
        return SCHEDULING_DAO;
    }

    @Override
    public void insert(SchedulingDTO type) throws InvalidValueException {

    }

    @Override
    public void remove(SchedulingDTO type) throws InvalidValueException {

    }

    @Override
    public void update(SchedulingDTO type) throws InvalidValueException {

    }
}
