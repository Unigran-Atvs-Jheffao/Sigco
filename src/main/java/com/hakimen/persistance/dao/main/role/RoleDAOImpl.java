package com.hakimen.persistance.dao.main.role;

import com.hakimen.model.Appointment;
import com.hakimen.model.Role;
import com.hakimen.persistance.JPAInstance;
import com.hakimen.persistance.dao.DAO;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    @Override
    public Role getById(Integer id) throws NoResultException {
        TypedQuery<Role> query = JPAInstance.INSTANCE.getManager().createQuery("select role from Role role where role.id = :id", Role.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Role> getAll() throws NoResultException {
        TypedQuery<Role> query = JPAInstance.INSTANCE.getManager().createQuery("select role from Role role", Role.class);
        return query.getResultList();
    }
}
