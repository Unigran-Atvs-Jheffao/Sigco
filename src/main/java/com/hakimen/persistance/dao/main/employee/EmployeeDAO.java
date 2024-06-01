package com.hakimen.persistance.dao.main.employee;

import com.hakimen.model.Employee;
import com.hakimen.model.Scheduling;
import com.hakimen.persistance.dao.DAO;

import java.util.List;

public interface EmployeeDAO extends DAO<Integer, Employee> {
    Employee getByName(String name);
    List<Employee> findAllFiltered(boolean ascendent, String key, String searchQuery);

    Employee getByUserAndPassword(String user, String password);
}
