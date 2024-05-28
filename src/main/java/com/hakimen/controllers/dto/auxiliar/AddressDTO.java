package com.hakimen.controllers.dto.auxiliar;

import com.hakimen.controllers.auxiliar.AddressController;
import com.hakimen.controllers.auxiliar.CityController;
import com.hakimen.controllers.dto.DTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.auxiliar.Address;
import com.hakimen.model.auxiliar.City;

import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.Objects;


public class AddressDTO implements DTO<Address> {
    private Integer id;
    private String street;
    private Integer cityId;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.cityId = address.getCity().getId();
    }

    public AddressDTO() {
    }

    @Override
    public Address build() throws InvalidValueException {
        Address address = new Address();

        address.setId(id != null && id > 0 ? id : null);

        if(street == null || street.isBlank()) throw new InvalidValueException("Rua Inválida");
        address.setStreet(street);

        try{
            City city = CityController.INSTANCE.getById(cityId).build();
            address.setCity(city);
        } catch (NoResultException e){
            throw new InvalidValueException("Cidade Inválida", e);
        }

        return address;
    }
}