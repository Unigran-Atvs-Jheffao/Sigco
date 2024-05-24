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
    @Override
    public Pacient build() throws InvalidValueException {
        Pacient pacient = new Pacient();

        if(name == null || name.isBlank()) throw new InvalidValueException("Nome Inválido");
        pacient.setName(name);


        //TODO implementar o resto....
        return pacient;
    }
}
