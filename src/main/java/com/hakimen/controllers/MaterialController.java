package com.hakimen.controllers;

import com.hakimen.controllers.dto.MaterialDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Material;
import com.hakimen.persistance.dao.main.material.MaterialDAO;
import com.hakimen.persistance.dao.main.material.MaterialDAOImpl;

import java.util.List;

public class MaterialController implements Controller<MaterialDTO> {
    
    public static MaterialController INSTANCE = new MaterialController();

    private MaterialController(){

    };

    private MaterialDAO MATERIAL_DAO = new MaterialDAOImpl();

    public MaterialDAO getDAO(){
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

    @Override
    public List<MaterialDTO> getAll() {
        return MATERIAL_DAO.getAll().stream().map(MaterialDTO::new).toList();
    }

    @Override
    public MaterialDTO getById(int id) throws InvalidValueException {
        return new MaterialDTO(MATERIAL_DAO.getById(id));
    }

    public MaterialDTO getByIdIfMatches(int id, int matching) throws InvalidValueException {
        Material material = MATERIAL_DAO.getById(id);
        if(material.getId() == matching){
            return new MaterialDTO(material);
        }

        return new MaterialDTO();
    }


    public List<MaterialDTO> findAllFiltered(boolean asc, String key, String query){
        return MATERIAL_DAO.findAllFiltered(asc,key,query).stream().map(MaterialDTO::new).toList();
    }
}
