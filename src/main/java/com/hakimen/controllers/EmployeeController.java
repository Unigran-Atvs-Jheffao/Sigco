package com.hakimen.controllers;

import com.hakimen.controllers.dto.EmployeeDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Employee;
import com.hakimen.persistance.dao.main.employee.EmployeeDAO;
import com.hakimen.persistance.dao.main.employee.EmployeeDAOImpl;

import java.util.List;

public class EmployeeController implements Controller<EmployeeDTO> {

    public static EmployeeController INSTANCE = new EmployeeController();

    private EmployeeController(){

    };

    private EmployeeDAO EMPLOYEE_DAO = new EmployeeDAOImpl();

    public EmployeeDAO getDAO(){
        return EMPLOYEE_DAO;
    }

    @Override
    public void insert(EmployeeDTO dto) throws InvalidValueException {
        EMPLOYEE_DAO.insert(dto.build());
    }

    @Override
    public void remove(EmployeeDTO dto) throws InvalidValueException {
        EMPLOYEE_DAO.remove(dto.build());
    }

    @Override
    public void update(EmployeeDTO dto) throws InvalidValueException {
        EMPLOYEE_DAO.update(dto.build());
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return EMPLOYEE_DAO.getAll().stream().map(EmployeeDTO::new).toList();
    }

    @Override
    public EmployeeDTO getById(int id) throws InvalidValueException {
        return new EmployeeDTO(EMPLOYEE_DAO.getById(id));
    }
}
