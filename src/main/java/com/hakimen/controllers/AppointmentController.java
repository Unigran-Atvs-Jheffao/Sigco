package com.hakimen.controllers;

import com.hakimen.controllers.dto.AppointmentDTO;
import com.hakimen.controllers.dto.EmployeeDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.appointment.AppointmentDAO;
import com.hakimen.persistance.dao.appointment.AppointmentDAOImpl;
import com.hakimen.persistance.dao.employee.EmployeeDAO;
import com.hakimen.persistance.dao.employee.EmployeeDAOImpl;

public class AppointmentController implements Controller<AppointmentDTO> {
    private static AppointmentDAO APPOINTMENT_DAO = new AppointmentDAOImpl();

    public static AppointmentDAO getDAO(){
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
}
