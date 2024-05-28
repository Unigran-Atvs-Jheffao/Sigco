package com.hakimen.controllers.auxiliar;

import com.hakimen.controllers.Controller;
import com.hakimen.controllers.dto.auxiliar.CityDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.auxiliar.city.CityDAO;
import com.hakimen.persistance.dao.auxiliar.city.CityDAOImpl;

import java.util.List;

public class CityController implements Controller<CityDTO> {

    public static CityController INSTANCE = new CityController();

    private CityController() {
    }

    private CityDAO CITY_DAO = new CityDAOImpl();

    public CityDAO getDAO(){
        return CITY_DAO;
    }

    @Override
    public void insert(CityDTO type) throws InvalidValueException {
        CITY_DAO.insert(type.build());
    }

    @Override
    public void remove(CityDTO type) throws InvalidValueException {
        CITY_DAO.remove(type.build());
    }

    @Override
    public void update(CityDTO type) throws InvalidValueException {
        CITY_DAO.update(type.build());
    }

    @Override
    public List<CityDTO> getAll() {
        return CITY_DAO.getAll().stream().map(CityDTO::new).toList();
    }

    @Override
    public CityDTO getById(int id) throws InvalidValueException {
        return new CityDTO(CITY_DAO.getById(id));
    }
}
