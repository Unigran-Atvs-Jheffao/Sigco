package com.hakimen.controllers;

import com.hakimen.controllers.dto.LoginDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.login.LoginDAO;
import com.hakimen.persistance.dao.login.LoginDAOImpl;

public class LoginController implements Controller<LoginDTO> {
    private static LoginDAO LOGIN_DAO = new LoginDAOImpl();
    public static LoginDAO getDAO() {
        return LOGIN_DAO;
    }


    @Override
    public void insert(LoginDTO type) throws InvalidValueException {
        LOGIN_DAO.insert(type.build());
    }

    @Override
    public void remove(LoginDTO type) throws InvalidValueException {
        LOGIN_DAO.remove(type.build());
    }

    @Override
    public void update(LoginDTO type) throws InvalidValueException {
        LOGIN_DAO.update(type.build());
    }
}
