package com.hakimen.controllers.auxiliar;

import com.hakimen.controllers.Controller;
import com.hakimen.controllers.dto.auxiliar.AddressDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.auxiliar.address.AddressDAO;
import com.hakimen.persistance.dao.auxiliar.address.AddressDAOImpl;

import java.util.List;

public class AddressController implements Controller<AddressDTO> {

    public static AddressController INSTANCE = new AddressController();

    private AddressController() {
    }

    private AddressDAO ADDRESS_DAO = new AddressDAOImpl();

    public AddressDAO getDAO(){
        return ADDRESS_DAO;
    }

    @Override
    public void insert(AddressDTO type) throws InvalidValueException {
        ADDRESS_DAO.insert(type.build());
    }

    @Override
    public void remove(AddressDTO type) throws InvalidValueException {
        ADDRESS_DAO.insert(type.build());
    }

    @Override
    public void update(AddressDTO type) throws InvalidValueException {
        ADDRESS_DAO.insert(type.build());
    }

    @Override
    public List<AddressDTO> getAll() {
        return ADDRESS_DAO.getAll().stream().map(AddressDTO::new).toList();
    }

    @Override
    public AddressDTO getById(int id) throws InvalidValueException {
        return new AddressDTO(ADDRESS_DAO.getById(id));
    }
}
