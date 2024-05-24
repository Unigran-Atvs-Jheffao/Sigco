package com.hakimen.persistance.dao.Attachment;


import com.hakimen.model.auxiliar.Attachment;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AttachmentDAOImpl implements AttachmentDAO{
    @Override
    public Attachment getById(Integer id) throws NoResultException {
        TypedQuery<Attachment> query = JPAInstance.INSTANCE.getManager().createQuery("select a from Attachment a where a.id = :id", Attachment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Attachment> getAll() {
        TypedQuery<Attachment> query = JPAInstance.INSTANCE.getManager().createQuery("select a from Attachment a", Attachment.class);
        return query.getResultList();
    }
}
