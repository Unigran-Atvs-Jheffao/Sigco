package com.hakimen.persistance.dao.main.pacient;

import com.hakimen.model.Material;
import com.hakimen.model.Pacient;
import com.hakimen.persistance.dao.DAO;

import java.util.List;

public interface PacientDAO extends DAO<Integer, Pacient> {
    Pacient getByCPF(String cpf);
    List<Pacient> findAllFiltered(boolean ascendent, String key, String searchQuery);
}
