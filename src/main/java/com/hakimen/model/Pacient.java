package com.hakimen.model;

import com.hakimen.model.auxiliar.Address;
import com.hakimen.model.auxiliar.Contact;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pacient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
    private String cpf;
    private Date dateOfBirth;
    private String homeNumber;
    private String responsible;

    @OneToOne
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Integer getId() {
        return id;
    }

    public Pacient setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pacient setName(String name) {
        this.name = name;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Pacient setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Pacient setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public Pacient setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
        return this;
    }

    public String getResponsible() {
        return responsible;
    }

    public Pacient setResponsible(String responsible) {
        this.responsible = responsible;
        return this;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public Pacient setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
        return this;
    }

    public Contact getContact() {
        return contact;
    }

    public Pacient setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Pacient setAddress(Address address) {
        this.address = address;
        return this;
    }
}
