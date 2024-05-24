package com.hakimen.controllers.dto;

import com.hakimen.controllers.LoginController;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Employee;
import com.hakimen.model.Login;

import javax.persistence.NoResultException;

public class EmployeeDTO implements DTO<Employee>{
    private Integer id;

    private String registration;
    private Integer loginId;

    public Integer getId() {
        return id;
    }

    public EmployeeDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getRegistration() {
        return registration;
    }

    public EmployeeDTO setRegistration(String registration) {
        this.registration = registration;
        return this;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public EmployeeDTO setLoginId(Integer loginId) {
        this.loginId = loginId;
        return this;
    }

    @Override
    public Employee build() throws InvalidValueException {

        Employee employee = new Employee();

        if(registration == null || registration.isBlank()) throw new InvalidValueException("Registro Inválido");
        employee.setRegistration(registration);

        try {
            Login login = LoginController.getDAO().getById(loginId);
            employee.setLogin(login);
        } catch (NoResultException exception){
            throw new InvalidValueException("Login Inválido", exception);
        }

        return employee;
    }
}
