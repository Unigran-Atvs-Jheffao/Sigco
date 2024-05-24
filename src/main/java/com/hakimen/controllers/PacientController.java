package com.hakimen.controllers;

import com.hakimen.controllers.dto.PacientDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.pacient.PacientDAO;
import com.hakimen.persistance.dao.pacient.PacientDAOImpl;

public class PacientController implements Controller<PacientDTO> {
    private static PacientDAO PACIENT_DAO = new PacientDAOImpl();

    public static PacientDAO getDAO() {
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

}
