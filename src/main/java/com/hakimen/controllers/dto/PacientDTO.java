package com.hakimen.controllers.dto;

import com.hakimen.controllers.auxiliar.AddressController;
import com.hakimen.controllers.auxiliar.ContactController;
import com.hakimen.controllers.MedicalRecordController;
import com.hakimen.controllers.dto.auxiliar.AddressDTO;
import com.hakimen.controllers.dto.auxiliar.ContactDTO;
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

    private ContactDTO contact;
    private AddressDTO address;

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


    public ContactDTO getContact() {
        return contact;
    }

    public PacientDTO setContact(ContactDTO contact) {
        this.contact = contact;
        return this;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public PacientDTO setAddress(AddressDTO address) {
        this.address = address;
        return this;
    }

    public PacientDTO(Pacient pacient) {
        id = pacient.getId();
        name = pacient.getName();
        cpf = pacient.getCpf();
        dateOfBirth = pacient.getDateOfBirth();
        homeNumber = pacient.getHomeNumber();
        responsible = pacient.getResponsible();

        contact = new ContactDTO(pacient.getContact());
        address = new AddressDTO(pacient.getAddress());
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

        if(homeNumber == null) throw new InvalidValueException("Numero da Casa Inválido");
        pacient.setHomeNumber(homeNumber);


        pacient.setContact(contact.build());
        pacient.setAddress(address.build());

        return pacient;
    }
}
