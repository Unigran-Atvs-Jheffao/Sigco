package com.hakimen.controllers;

import com.hakimen.controllers.dto.EmployeeDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Employee;
import com.hakimen.persistance.dao.employee.EmployeeDAO;
import com.hakimen.persistance.dao.employee.EmployeeDAOImpl;

public class EmployeeController implements Controller<EmployeeDTO> {
    private static EmployeeDAO EMPLOYEE_DAO = new EmployeeDAOImpl();

    public static EmployeeDAO getDAO(){
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
}
