package com.hakimen.controllers;

import com.hakimen.controllers.dto.DTO;
import com.hakimen.controllers.dto.LoginDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Login;
import com.hakimen.persistance.dao.DAO;
import com.hakimen.persistance.dao.main.login.LoginDAO;
import com.hakimen.persistance.dao.main.login.LoginDAOImpl;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public class LoginController implements Controller<LoginDTO> {

    public static LoginController INSTANCE = new LoginController();

    private LoginController(){

    };

    private LoginDAO LOGIN_DAO = new LoginDAOImpl();
    public LoginDAO getDAO() {
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

    @Override
    public List<LoginDTO> getAll() {
        return LOGIN_DAO.getAll().stream().map(LoginDTO::new).toList();
    }

    @Override
    public LoginDTO getById(int id) throws InvalidValueException {
        return new LoginDTO(LOGIN_DAO.getById(id));
    }
}
