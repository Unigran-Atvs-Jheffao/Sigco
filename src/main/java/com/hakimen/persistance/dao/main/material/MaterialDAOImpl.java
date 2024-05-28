package com.hakimen.persistance.dao.main.material;

import com.hakimen.model.Material;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO{
    @Override
    public Material getById(Integer id) throws NoResultException {
        TypedQuery<Material> query = JPAInstance.INSTANCE.getManager().createQuery("select material from Material material where material.id = :id", Material.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Material> getAll() throws NoResultException {
        TypedQuery<Material> query = JPAInstance.INSTANCE.getManager().createQuery("select material from Material material", Material.class);
        return query.getResultList();
    }
}
