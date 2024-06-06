package com.hakimen.persistance.dao.main.employee;

import com.hakimen.model.Employee;
import com.hakimen.model.Login;
import com.hakimen.model.Scheduling;
import com.hakimen.persistance.JPAInstance;
import com.hakimen.persistance.dao.main.login.LoginDAO;

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

    @Override
    public Employee getByName(String name) {
        TypedQuery<Employee> query = JPAInstance.INSTANCE.getManager().createQuery("select employee from Employee employee where employee.login.username = :name", Employee.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Employee> findAllFiltered(boolean ascendent, String key, String searchQuery) {
        String builtQuery = "select employee from Employee employee where lower(employee.login.username) like :search";

        builtQuery += " order by employee." + key;
        builtQuery += ascendent ? " asc" : " desc";

        TypedQuery<Employee> query = JPAInstance.INSTANCE.getManager().createQuery(builtQuery, Employee.class);

        query.setParameter("search", "%" + searchQuery + "%");
        return query.getResultList();
    }

    @Override
    public Employee getByUserAndPassword(String user, String password) {
        TypedQuery<Employee> query = JPAInstance.INSTANCE.getManager().createQuery("select employee from Employee employee where employee.login.username = :name and employee.login.password = :pass", Employee.class);
        query.setParameter("name", user);
        query.setParameter("pass", password);

        return query.getSingleResult();
    }
}
