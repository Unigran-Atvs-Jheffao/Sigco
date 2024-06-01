package com.hakimen.persistance.dao.main.scheduling;

import com.hakimen.model.Material;
import com.hakimen.model.Scheduling;
import com.hakimen.persistance.dao.DAO;

import java.util.List;

public interface SchedulingDAO extends DAO<Integer, Scheduling> {
    List<Scheduling> findAllFiltered(boolean ascendent, String key, String searchQuery);
}
