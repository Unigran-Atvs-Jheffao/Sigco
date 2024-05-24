package com.hakimen.controllers;

import com.hakimen.controllers.dto.MaterialDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.material.MaterialDAO;
import com.hakimen.persistance.dao.material.MaterialDAOImpl;

public class MaterialController implements Controller<MaterialDTO> {

    private static MaterialDAO MATERIAL_DAO = new MaterialDAOImpl();

    public static MaterialDAO getDAO(){
        return MATERIAL_DAO;
    }


    @Override
    public void update(MaterialDTO type) throws InvalidValueException {
        MATERIAL_DAO.update(type.build());
    }

    @Override
    public void remove(MaterialDTO type) throws InvalidValueException {
        MATERIAL_DAO.remove(type.build());
    }

    @Override
    public void insert(MaterialDTO type) throws InvalidValueException {
        MATERIAL_DAO.insert(type.build());
    }
}
