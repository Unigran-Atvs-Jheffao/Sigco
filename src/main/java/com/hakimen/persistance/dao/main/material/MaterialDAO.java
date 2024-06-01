package com.hakimen.persistance.dao.main.material;

import com.hakimen.model.Material;
import com.hakimen.model.Scheduling;
import com.hakimen.persistance.dao.DAO;

import java.util.List;

public interface MaterialDAO extends DAO<Integer, Material> {
    List<Material> findAllFiltered(boolean ascendent, String key, String searchQuery);
}
