package com.hakimen.controllers.auxiliar;

import com.hakimen.controllers.Controller;
import com.hakimen.controllers.dto.auxiliar.AttachmentDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.persistance.dao.auxiliar.attachment.AttachmentDAO;
import com.hakimen.persistance.dao.auxiliar.attachment.AttachmentDAOImpl;

import java.util.List;

public class AttachmentController implements Controller<AttachmentDTO> {

    private static AttachmentDAO ATTACHMENT_DAO = new AttachmentDAOImpl();

    public static AttachmentDAO getDAO(){
        return ATTACHMENT_DAO;
    }

    @Override
    public void insert(AttachmentDTO type) throws InvalidValueException {
        ATTACHMENT_DAO.insert(type.build());
    }

    @Override
    public void remove(AttachmentDTO type) throws InvalidValueException {
        ATTACHMENT_DAO.remove(type.build());
    }

    @Override
    public void update(AttachmentDTO type) throws InvalidValueException {
        ATTACHMENT_DAO.update(type.build());
    }

    @Override
    public List<AttachmentDTO> getAll() {
        return ATTACHMENT_DAO.getAll().stream().map(AttachmentDTO::new).toList();
    }

    @Override
    public AttachmentDTO getById(int id) throws InvalidValueException {
        return new AttachmentDTO(ATTACHMENT_DAO.getById(id));
    }
}
