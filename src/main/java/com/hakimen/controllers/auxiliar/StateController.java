package com.hakimen.controllers.auxiliar;

import com.hakimen.controllers.Controller;
import com.hakimen.controllers.dto.auxiliar.StateDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.auxiliar.state.StateDAO;
import com.hakimen.persistance.dao.auxiliar.state.StateDAOImpl;

import javax.persistence.NoResultException;
import java.util.List;

public class StateController implements Controller<StateDTO> {

    public static StateController INSTANCE = new StateController();

    private StateController() {
    }

    private StateDAO STATE_DAO = new StateDAOImpl();

    public StateDAO getDAO(){
        return STATE_DAO;
    }

    @Override
    public void insert(StateDTO type) throws InvalidValueException {
        STATE_DAO.insert(type.build());
    }

    @Override
    public void remove(StateDTO type) throws InvalidValueException {
        STATE_DAO.remove(type.build());
    }

    @Override
    public void update(StateDTO type) throws InvalidValueException {
        STATE_DAO.update(type.build());
    }

    @Override
    public List<StateDTO> getAll() {
        return STATE_DAO.getAll().stream().map(StateDTO::new).toList();
    }

    @Override
    public StateDTO getById(int id) throws InvalidValueException {
        return new StateDTO(STATE_DAO.getById(id));
    }

    public StateDTO getByName(String name) throws InvalidValueException {
        try{
            return new StateDTO(STATE_DAO.getStateByName(name));
        } catch (NoResultException e){
            throw new InvalidValueException("Estado não encontrado", e);
        }
    }
}
