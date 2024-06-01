package com.hakimen.controllers.dto.auxiliar;

import com.hakimen.controllers.dto.DTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.auxiliar.Attachment;

import java.io.File;

public class AttachmentDTO implements DTO<Attachment> {

    private Integer id;
    private File attachment;

    public Integer getId() {
        return id;
    }

    public AttachmentDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public File getAttachment() {
        return attachment;
    }

    public AttachmentDTO setAttachment(File attachment) {
        this.attachment = attachment;
        return this;
    }



    public AttachmentDTO(Attachment attachment) {
        this.id = attachment.getId();
        this.attachment = attachment.getAttachment();
    }

    @Override
    public Attachment build() throws InvalidValueException {
        Attachment attachment = new Attachment();

        attachment.setId(id != null && id > 0 ? id : null);

        if(this.attachment == null) throw new InvalidValueException("Anexo inválido");
        attachment.setAttachment(this.attachment);

        return attachment;
    }
}
