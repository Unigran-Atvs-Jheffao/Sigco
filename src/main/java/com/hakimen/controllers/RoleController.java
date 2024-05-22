package com.hakimen.controllers;


import com.hakimen.controllers.dto.RoleDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.role.RoleDAO;
import com.hakimen.persistance.dao.role.RoleDAOImpl;

public class RoleController implements Controller<RoleDTO> {
    private static RoleDAO ROLE_DAO = new RoleDAOImpl();
    public static RoleDAO getDAO() {
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
}
