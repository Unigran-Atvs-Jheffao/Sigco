package com.hakimen.controllers.dto.auxiliar;

import com.hakimen.controllers.dto.DTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.auxiliar.City;
import com.hakimen.model.auxiliar.State;


public class StateDTO implements DTO<State> {
    private Integer id;
    private String name;

    public StateDTO(State state){
        this.id = state.getId();
        this.name = state.getName();
    }

    public StateDTO() {
    }

    public Integer getId() {
        return id;
    }

    public StateDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StateDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public State build() throws InvalidValueException {
        State state = new State();

        state.setId(id != null && id > 0 ? id : null);

        if(name == null || name.isBlank()) throw new InvalidValueException("Nome Inválido");
        state.setName(name);

        return state;
    }
}