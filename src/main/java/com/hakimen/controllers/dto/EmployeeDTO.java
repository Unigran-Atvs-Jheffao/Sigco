package com.hakimen.controllers.dto;

import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Employee;

public class EmployeeDTO implements DTO<Employee>{
    private Integer id;

    private String registration;
    private LoginDTO login;

    public EmployeeDTO(){

    }

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        registration = employee.getRegistration();
        login = new LoginDTO(employee.getLogin());
    }

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

    public LoginDTO getLogin() {
        return login;
    }

    public EmployeeDTO setLogin(LoginDTO login) {
        this.login = login;
        return this;
    }

    @Override
    public Employee build() throws InvalidValueException {

        Employee employee = new Employee();

        employee.setId(id != null && id > 0 ? id : null);

        if(registration == null || registration.isBlank()) throw new InvalidValueException("Registro Inválido");
        employee.setRegistration(registration);

        employee.setLogin(login.build());

        return employee;
    }
}
