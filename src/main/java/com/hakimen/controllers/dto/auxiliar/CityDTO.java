package com.hakimen.controllers.dto.auxiliar;

import com.hakimen.controllers.auxiliar.StateController;
import com.hakimen.controllers.dto.DTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.auxiliar.City;
import com.hakimen.model.auxiliar.State;

import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.Objects;


public class CityDTO implements DTO<City> {
    private Integer id;
    private String name;
    private StateDTO state;

    public CityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.state = new StateDTO(city.getState());
    }

    public CityDTO() {
    }

    public Integer getId() {
        return id;
    }

    public CityDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityDTO setName(String name) {
        this.name = name;
        return this;
    }

    public StateDTO getState() {
        return state;
    }

    public CityDTO setState(StateDTO state) {
        this.state = state;
        return this;
    }

    @Override
    public City build() throws InvalidValueException {
        City city = new City();

        city.setId(id != null && id > 0 ? id : null);

        if(name == null || name.isBlank()) throw new InvalidValueException("Nome Inválido");
        city.setName(name);

        city.setState(state.build());


        return city;
    }
}