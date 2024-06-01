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
    private CityDTO city;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = new CityDTO(address.getCity());
    }

    public AddressDTO() {
    }

    public Integer getId() {
        return id;
    }

    public AddressDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public CityDTO getCity() {
        return city;
    }

    public AddressDTO setCity(CityDTO city) {
        this.city = city;
        return this;
    }

    @Override
    public Address build() throws InvalidValueException {
        Address address = new Address();

        address.setId(id != null && id > 0 ? id : null);

        if(street == null || street.isBlank()) throw new InvalidValueException("Rua Inválida");
        address.setStreet(street);

        address.setCity(city.build());

        return address;
    }
}