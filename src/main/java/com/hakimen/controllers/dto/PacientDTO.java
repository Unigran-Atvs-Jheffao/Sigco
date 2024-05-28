package com.hakimen.controllers.dto;

import com.hakimen.controllers.auxiliar.AddressController;
import com.hakimen.controllers.auxiliar.ContactController;
import com.hakimen.controllers.MedicalRecordController;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.MedicalRecord;
import com.hakimen.model.Pacient;
import com.hakimen.model.auxiliar.Address;
import com.hakimen.model.auxiliar.Contact;

import javax.persistence.NoResultException;
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

    public PacientDTO(Pacient pacient) {
        id = pacient.getId();
        name = pacient.getName();
        cpf = pacient.getCpf();
        dateOfBirth = pacient.getDateOfBirth();
        homeNumber = pacient.getHomeNumber();
        responsible = pacient.getResponsible();

        medicalRecordId = pacient.getMedicalRecord().getId();
        contactId = pacient.getContact().getId();
        addressId = pacient.getAddress().getId();
    }

    public PacientDTO() {
    }

    @Override
    public Pacient build() throws InvalidValueException {
        Pacient pacient = new Pacient();

        pacient.setId(id != null && id > 0 ? id : null);


        if(name == null || name.isBlank()) throw new InvalidValueException("Nome Inválido");
        pacient.setName(name);

        if(cpf == null || cpf.isBlank()) throw new InvalidValueException("CPF Inválido");
        pacient.setCpf(cpf);

        if(dateOfBirth == null) throw new InvalidValueException("Data de Nascimento Inválida");
        pacient.setDateOfBirth(dateOfBirth);

        if(responsible != null && !responsible.isBlank()) {
            pacient.setResponsible(responsible);
        }

        if(homeNumber != null) throw new InvalidValueException("Numero da Casa Inválido");
        pacient.setHomeNumber(homeNumber);


        try{
            MedicalRecord record = MedicalRecordController.INSTANCE.getById(medicalRecordId).build();
            pacient.setMedicalRecord(record);
        } catch (NoResultException e) {
            throw new InvalidValueException("Prontuário Inválido", e);
        }

        try{
            Contact contact = ContactController.INSTANCE.getById(contactId).build();
            pacient.setContact(contact);
        } catch (NoResultException e) {
            throw new InvalidValueException("Contato Inválido", e);
        }

        try{
            Address address = AddressController.INSTANCE.getById(addressId).build();
            pacient.setAddress(address);
        } catch (NoResultException e) {
            throw new InvalidValueException("Endereço Inválido", e);
        }

        return pacient;
    }
}
