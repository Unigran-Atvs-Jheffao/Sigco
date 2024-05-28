package com.hakimen.controllers;


import com.hakimen.controllers.dto.RoleDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.main.role.RoleDAO;
import com.hakimen.persistance.dao.main.role.RoleDAOImpl;

import java.util.List;

public class RoleController implements Controller<RoleDTO> {

    public static RoleController INSTANCE = new RoleController();

    private RoleController(){

    };


    private RoleDAO ROLE_DAO = new RoleDAOImpl();
    public RoleDAO getDAO() {
        return ROLE_DAO;
    }

    @Override
    public void insert(RoleDTO type) throws InvalidValueException {
        ROLE_DAO.insert(type.build());
    }

    @Override
    public void remove(RoleDTO type) throws InvalidValueException {
        ROLE_DAO.remove(type.build());
    }

    @Override
    public void update(RoleDTO type) throws InvalidValueException {
        ROLE_DAO.update(type.build());
    }

    @Override
    public List<RoleDTO> getAll() {
        return ROLE_DAO.getAll().stream().map(RoleDTO::new).toList();
    }

    @Override
    public RoleDTO getById(int id) throws InvalidValueException {
        return new RoleDTO(ROLE_DAO.getById(id));
    }
}
