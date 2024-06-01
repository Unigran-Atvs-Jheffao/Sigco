package com.hakimen.controllers.dto;

import com.hakimen.controllers.RoleController;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Login;
import com.hakimen.model.Role;

import javax.persistence.NoResultException;

public class LoginDTO implements DTO<Login> {
    private Integer id;

    private String username;
    private String password;
    private RoleDTO role;

    public Integer getId() {
        return id;
    }

    public LoginDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public RoleDTO getRole() {
        return role;
    }

    public LoginDTO setRole(RoleDTO role) {
        this.role = role;
        return this;
    }

    public LoginDTO(Login login) {
        id = login.getId();
        username = login.getUsername();
        password = login.getPassword();
        role = new RoleDTO(login.getRole());
    }

    public LoginDTO(){

    }

    @Override
    public Login build() throws InvalidValueException {
        Login login = new Login();

        login.setId(id != null && id > 0 ? id : null);

        if(username == null || username.isBlank()) throw new InvalidValueException("Nome de usuário inválido");
        login.setUsername(username);

        if(password == null || password.isBlank()) throw new InvalidValueException("Senha inválida");
        login.setPassword(password);

        login.setRole(role.build());

        return login;
    }
}
