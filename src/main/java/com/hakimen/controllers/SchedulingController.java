package com.hakimen.controllers;

import com.hakimen.controllers.dto.MedicalRecordDTO;
import com.hakimen.controllers.dto.SchedulingDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Scheduling;
import com.hakimen.persistance.dao.main.medicalRecord.MedicalRecordDAO;
import com.hakimen.persistance.dao.main.medicalRecord.MedicalRecordDAOImpl;
import com.hakimen.persistance.dao.main.scheduling.SchedulingDAO;
import com.hakimen.persistance.dao.main.scheduling.SchedulingDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class SchedulingController implements Controller<SchedulingDTO>{

    public static SchedulingController INSTANCE = new SchedulingController();

    private SchedulingController(){

    };

    private SchedulingDAO SCHEDULING_DAO = new SchedulingDAOImpl();
    public SchedulingDAO getDAO(){
        return SCHEDULING_DAO;
    }

    @Override
    public void insert(SchedulingDTO type) throws InvalidValueException {
        SCHEDULING_DAO.insert(type.build());
    }

    @Override
    public void remove(SchedulingDTO type) throws InvalidValueException {
        SCHEDULING_DAO.remove(type.build());
    }

    @Override
    public void update(SchedulingDTO type) throws InvalidValueException {
        SCHEDULING_DAO.update(type.build());
    }

    @Override
    public List<SchedulingDTO> getAll() {
        return SCHEDULING_DAO.getAll().stream().map(SchedulingDTO::new).toList();
    }

    @Override
    public SchedulingDTO getById(int id) throws InvalidValueException {
        return new SchedulingDTO(SCHEDULING_DAO.getById(id));
    }

    public List<SchedulingDTO> findAllFiltered(boolean asc, String key, String query){
        return SCHEDULING_DAO.findAllFiltered(asc,key,query).stream().map(SchedulingDTO::new).toList();
    }
}
