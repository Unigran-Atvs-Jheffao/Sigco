package com.hakimen.model;

import com.hakimen.model.auxiliar.Attachment;

import javax.persistence.*;
import java.util.List;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String observations;

    @OneToMany
    @JoinColumn(name = "attachment_id")
    private List<Attachment> attachments;

    private Float value;

    public Integer getId() {
        return id;
    }

    public Appointment setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getObservations() {
        return observations;
    }

    public Appointment setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public Appointment setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public Float getValue() {
        return value;
    }

    public Appointment setValue(Float value) {
        this.value = value;
        return this;
    }
}