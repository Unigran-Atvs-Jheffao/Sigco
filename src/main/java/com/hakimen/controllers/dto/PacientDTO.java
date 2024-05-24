package com.hakimen.controllers.dto;

import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Pacient;
import com.hakimen.model.auxiliar.Address;
import com.hakimen.model.auxiliar.Contact;

import java.util.Date;

public class PacientDTO implements DTO<Pacient> {
    private Integer id;

    private String name;
    private String cpf;
    private Date dateOfBirth;
    private String homeNumber;
    private String responsible;

    private Integer medicalRecordId;
    private Integer contactId;
    private Integer addressId;

    public Integer getId() {
        return id;
    }

    public PacientDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PacientDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public PacientDTO setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public PacientDTO setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getResponsible() {
        return responsible;
    }

    public PacientDTO setResponsible(String responsible) {
        this.responsible = responsible;
        return this;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public PacientDTO setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
        return this;
    }

    public Integer getMedicalRecordId() {
        return medicalRecordId;
    }

    public PacientDTO setMedicalRecordId(Integer medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
        return this;
    }

    public Integer getContactId() {
        return contactId;
    }

    public PacientDTO setContactId(Integer contactId) {
        this.contactId = contactId;
        return this;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public PacientDTO setAddressId(Integer addressId) {
        this.addressId = addressId;
        return this;
    }

    @Override
    public Pacient build() throws InvalidValueException {
        Pacient pacient = new Pacient();

        if(name == null || name.isBlank()) throw new InvalidValueException("Nome Inválido");
        pacient.setName(name);


        //TODO implementar o resto....
        return pacient;
    }
}
