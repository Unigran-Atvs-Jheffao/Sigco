package com.hakimen.controllers;

import com.hakimen.controllers.dto.AppointmentDTO;
import com.hakimen.controllers.dto.EmployeeDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.main.appointment.AppointmentDAO;
import com.hakimen.persistance.dao.main.appointment.AppointmentDAOImpl;
import com.hakimen.persistance.dao.main.employee.EmployeeDAO;
import com.hakimen.persistance.dao.main.employee.EmployeeDAOImpl;

import java.util.List;

public class AppointmentController implements Controller<AppointmentDTO> {

    public static AppointmentController INSTANCE = new AppointmentController();

    private AppointmentController(){

    };

    private AppointmentDAO APPOINTMENT_DAO = new AppointmentDAOImpl();

    public AppointmentDAO getDAO(){
        return APPOINTMENT_DAO;
    }

    @Override
    public void insert(AppointmentDTO dto) throws InvalidValueException {
        APPOINTMENT_DAO.insert(dto.build());
    }

    @Override
    public void remove(AppointmentDTO dto) throws InvalidValueException {
        APPOINTMENT_DAO.remove(dto.build());
    }

    @Override
    public void update(AppointmentDTO dto) throws InvalidValueException {
        APPOINTMENT_DAO.update(dto.build());
    }

    @Override
    public List<AppointmentDTO> getAll() {
        return APPOINTMENT_DAO.getAll().stream().map(AppointmentDTO::new).toList();
    }

    @Override
    public AppointmentDTO getById(int id) throws InvalidValueException {
        return new AppointmentDTO(APPOINTMENT_DAO.getById(id));
    }
}
