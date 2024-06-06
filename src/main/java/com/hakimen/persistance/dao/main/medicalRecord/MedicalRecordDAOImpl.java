package com.hakimen.persistance.dao.main.medicalRecord;

import com.hakimen.controllers.dto.PacientDTO;
import com.hakimen.model.Employee;
import com.hakimen.model.Material;
import com.hakimen.model.MedicalRecord;
import com.hakimen.persistance.JPAInstance;
import com.hakimen.persistance.dao.main.material.MaterialDAO;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class MedicalRecordDAOImpl implements MedicalRecordDAO {
    @Override
    public MedicalRecord getById(Integer id) throws NoResultException {
        TypedQuery<MedicalRecord> query = JPAInstance.INSTANCE.getManager().createQuery("select medical_record from MedicalRecord medical_record where medical_record.id = :id", MedicalRecord.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<MedicalRecord> getAll() throws NoResultException {
        TypedQuery<MedicalRecord> query = JPAInstance.INSTANCE.getManager().createQuery("select medical_record from MedicalRecord medical_record", MedicalRecord.class);
        return query.getResultList();
    }

    @Override
    public List<MedicalRecord> findAllFiltered(String pacientName, boolean ascendent, String key, String searchQuery) {
        String builtQuery = "select record from MedicalRecord record where record.forPacient.name = :pacient and lower(record.history.withDentist.login.username) like :search";

        builtQuery += " order by record." + key;
        builtQuery += ascendent ? " asc" : " desc";

        TypedQuery<MedicalRecord> query = JPAInstance.INSTANCE.getManager().createQuery(builtQuery, MedicalRecord.class);

        query.setParameter("pacient",  pacientName);
        query.setParameter("search", "%" + searchQuery + "%");
        return query.getResultList();
    }

    @Override
    public List<MedicalRecord> getByPacient(Integer pacient) {
        TypedQuery<MedicalRecord> query = JPAInstance.INSTANCE.getManager().createQuery("select medical_record from MedicalRecord medical_record where medical_record.forPacient.id = :pacient", MedicalRecord.class);
        query.setParameter("pacient", pacient);
        return query.getResultList();
    }

    @Override
    public MedicalRecord getByAppointment(Integer appointmentId) {
        TypedQuery<MedicalRecord> query = JPAInstance.INSTANCE.getManager().createQuery("select medical_record from MedicalRecord medical_record where medical_record.history.id = :appointment", MedicalRecord.class);
        query.setParameter("appointment", appointmentId);
        return query.getSingleResult();
    }
}