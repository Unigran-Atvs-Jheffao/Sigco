package com.hakimen.controllers.dto;

import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Role;

public class RoleDTO implements DTO<Role> {
    private Integer id;

    private String name;
    private String description;

    public Integer getId() {
        return id;
    }

    public RoleDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }


    public RoleDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RoleDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public RoleDTO(Role role){
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
    }

    public RoleDTO() {
    }

    @Override
    public Role build() throws InvalidValueException {
        Role role = new Role();

        role.setId(id != null && id > 0 ? id : null);

        if(name == null || name.isBlank()) throw new InvalidValueException("Nome Inválido");
        role.setName(name);

        if(description == null || description.isBlank()) throw new InvalidValueException("Descrição Inválida");
        role.setDescription(description);

        return role;
    }
}
