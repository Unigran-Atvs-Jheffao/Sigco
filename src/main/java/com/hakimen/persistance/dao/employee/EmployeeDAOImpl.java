package com.hakimen.persistance.dao.employee;

import com.hakimen.model.Employee;
import com.hakimen.model.Login;
import com.hakimen.persistance.JPAInstance;
import com.hakimen.persistance.dao.login.LoginDAO;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;


public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public Employee getById(Integer id) throws NoResultException {
        TypedQuery<Employee> query = JPAInstance.INSTANCE.getManager().createQuery("select employee from Employee employee where employee.id = :id", Employee.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Employee> getAll() {
        TypedQuery<Employee> query = JPAInstance.INSTANCE.getManager().createQuery("select employee from Employee employee", Employee.class);
        return query.getResultList();
    }
}
