package com.hakimen.persistance.dao.appointment;

import com.hakimen.model.Appointment;
import com.hakimen.model.Dentist;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {
    @Override
    public Appointment getById(Integer id) throws NoResultException {
        TypedQuery<Appointment> query = JPAInstance.INSTANCE.getManager().createQuery("select a from Appointment a where a.id = :id", Appointment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Appointment> getAll() {
        TypedQuery<Appointment> query = JPAInstance.INSTANCE.getManager().createQuery("select a from Appointment a", Appointment.class);
        return query.getResultList();
    }
}
