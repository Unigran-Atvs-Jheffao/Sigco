package com.hakimen.persistance.dao.main.pacient;

import com.hakimen.model.Material;
import com.hakimen.model.Pacient;
import com.hakimen.persistance.JPAInstance;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class PacientDAOImpl implements PacientDAO {
    @Override
    public Pacient getById(Integer id) throws NoResultException {
        TypedQuery<Pacient> query = JPAInstance.INSTANCE.getManager().createQuery("select pacient from Pacient pacient where pacient.id = :id", Pacient.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Pacient> getAll() throws NoResultException {
        TypedQuery<Pacient> query = JPAInstance.INSTANCE.getManager().createQuery("select pacient from Pacient pacient", Pacient.class);
        return query.getResultList();
    }

    @Override
    public Pacient getByCPF(String cpf) {
        TypedQuery<Pacient> query = JPAInstance.INSTANCE.getManager().createQuery("select pacient from Pacient pacient where pacient.cpf = :cpf", Pacient.class);
        query.setParameter("cpf", cpf);
        return query.getSingleResult();
    }

    @Override
    public List<Pacient> findAllFiltered(boolean ascendent, String key, String searchQuery) {
        String builtQuery = "select pacient from Pacient pacient where pacient.name like :search";

        builtQuery += " order by pacient." + key;
        builtQuery += ascendent ? " asc" : " desc";


        TypedQuery<Pacient> query = JPAInstance.INSTANCE.getManager().createQuery(builtQuery, Pacient.class);

        query.setParameter("search", "%" + searchQuery + "%");
        return query.getResultList();
    }
}
