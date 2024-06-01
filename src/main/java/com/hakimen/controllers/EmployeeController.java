package com.hakimen.controllers;

import com.hakimen.controllers.dto.EmployeeDTO;
import com.hakimen.controllers.dto.MaterialDTO;
import com.hakimen.controllers.dto.SchedulingDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Employee;
import com.hakimen.model.Material;
import com.hakimen.persistance.dao.main.employee.EmployeeDAO;
import com.hakimen.persistance.dao.main.employee.EmployeeDAOImpl;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.function.Predicate;

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
        try {
            return new EmployeeDTO(EMPLOYEE_DAO.getById(id));
        } catch (NoResultException e) {
            throw new InvalidValueException("Id não encontrado");
        }
    }

    public EmployeeDTO getByIdIfMatches(int id, Predicate<Integer> matching) throws InvalidValueException {
        try {
            Employee employee = EMPLOYEE_DAO.getById(id);
            if(matching.test(employee.getId())){
                return new EmployeeDTO(employee);
            } else throw new InvalidValueException("Id " + id + " não compatível");
        } catch (NoResultException e) {
            throw new InvalidValueException("Id não encontrado");
        }
    }

    public EmployeeDTO getByName(String name) throws InvalidValueException{
        try{
            return new EmployeeDTO(EMPLOYEE_DAO.getByName(name));
        } catch (NoResultException e){
            throw new InvalidValueException("Funcionario com Id não encontrado");
        }
    }

    public EmployeeDTO getByNameIfRoleIdMatches(String name, Predicate<Integer> matching) throws InvalidValueException {
        try {
            Employee employee = EMPLOYEE_DAO.getByName(name);
            if(matching.test(employee.getLogin().getRole().getId())){
                return new EmployeeDTO(employee);
            } else throw new InvalidValueException("Nome " + name + " não compatível");
        } catch (NoResultException e) {
            throw new InvalidValueException("Nome não encontrado");
        }
    }

    public List<EmployeeDTO> findAllFiltered(boolean asc, String key, String query){
        return EMPLOYEE_DAO.findAllFiltered(asc,key,query).stream().map(EmployeeDTO::new).toList();
    }

    public EmployeeDTO getByUserAndPassword(String username, String password) throws InvalidValueException {
        return new EmployeeDTO(EMPLOYEE_DAO.getByUserAndPassword(username,password));
    }
}
