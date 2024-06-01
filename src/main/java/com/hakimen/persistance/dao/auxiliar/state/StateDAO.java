package com.hakimen.persistance.dao.auxiliar.state;

import com.hakimen.model.auxiliar.State;
import com.hakimen.persistance.dao.DAO;

public interface StateDAO extends DAO<Integer, State> {
    State getStateByName(String name);
}
