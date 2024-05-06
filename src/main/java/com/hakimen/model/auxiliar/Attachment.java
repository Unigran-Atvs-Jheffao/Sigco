package com.hakimen.model.auxiliar;

import javax.persistence.*;
import java.io.File;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private File attachment;

    public Integer getId() {
        return id;
    }

    public Attachment setId(Integer id) {
        this.id = id;
        return this;
    }

    public File getAttachment() {
        return attachment;
    }

    public Attachment setAttachment(File attachment) {
        this.attachment = attachment;
        return this;
    }
}
