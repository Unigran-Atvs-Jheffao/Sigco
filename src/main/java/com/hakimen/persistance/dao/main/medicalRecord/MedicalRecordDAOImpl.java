package com.hakimen.persistance.dao.main.medicalRecord;

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
}
