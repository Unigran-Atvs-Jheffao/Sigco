package com.hakimen.controllers.auxiliar;

import com.hakimen.controllers.Controller;
import com.hakimen.controllers.dto.auxiliar.ContactDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.auxiliar.contact.ContactDAO;
import com.hakimen.persistance.dao.auxiliar.contact.ContactDAOImpl;

import java.util.List;

public class ContactController implements Controller<ContactDTO>{

    private ContactDAO CONTACT_DAO = new ContactDAOImpl();

    public ContactDAO getDAO(){
        return CONTACT_DAO;
    }

    public static ContactController INSTANCE = new ContactController();

    private ContactController() {
    }


    @Override
    public void insert(ContactDTO type) throws InvalidValueException {
        CONTACT_DAO.insert(type.build());
    }

    @Override
    public void remove(ContactDTO type) throws InvalidValueException {
        CONTACT_DAO.remove(type.build());
    }

    @Override
    public void update(ContactDTO type) throws InvalidValueException {
        CONTACT_DAO.update(type.build());
    }

    @Override
    public List<ContactDTO> getAll() {
        return CONTACT_DAO.getAll().stream().map(ContactDTO::new).toList();
    }

    @Override
    public ContactDTO getById(int id) throws InvalidValueException {
        return new ContactDTO(CONTACT_DAO.getById(id));
    }
}
