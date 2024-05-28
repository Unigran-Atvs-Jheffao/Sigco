package com.hakimen.controllers;

import com.hakimen.controllers.dto.PacientDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.main.pacient.PacientDAO;
import com.hakimen.persistance.dao.main.pacient.PacientDAOImpl;

import java.util.List;

public class PacientController implements Controller<PacientDTO> {

    public static PacientController INSTANCE = new PacientController();

    private PacientController(){

    };


    private PacientDAO PACIENT_DAO = new PacientDAOImpl();

    public PacientDAO getDAO() {
        return PACIENT_DAO;
    }
    @Override
    public void update(PacientDTO type) throws InvalidValueException {
        PACIENT_DAO.update(type.build());
    }

    @Override
    public void remove(PacientDTO type) throws InvalidValueException {
        PACIENT_DAO.remove(type.build());
    }

    @Override
    public void insert(PacientDTO type) throws InvalidValueException {
        PACIENT_DAO.insert(type.build());
    }

    @Override
    public List<PacientDTO> getAll() {
        return PACIENT_DAO.getAll().stream().map(PacientDTO::new).toList();
    }

    @Override
    public PacientDTO getById(int id) throws InvalidValueException {
        return new PacientDTO(PACIENT_DAO.getById(id));
    }
}
