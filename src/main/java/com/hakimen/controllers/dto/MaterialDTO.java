package com.hakimen.controllers.dto;

import com.hakimen.controllers.EmployeeController;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Employee;
import com.hakimen.model.Material;

import javax.persistence.NoResultException;

public class MaterialDTO implements DTO<Material> {
    private Integer id;

    private String name;
    private Integer quantity;
    private Integer minQuantity;

    private EmployeeDTO employee;

    public Integer getId() {
        return id;
    }

    public MaterialDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MaterialDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public MaterialDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public MaterialDTO setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
        return this;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public MaterialDTO setEmployee(EmployeeDTO employee) {
        this.employee = employee;
        return this;
    }

    public MaterialDTO(Material material) {
        id = material.getId();
        name = material.getName();
        quantity = material.getQuantity();
        minQuantity = material.getMinQuantity();
        employee = new EmployeeDTO(material.getEmployee());
    }

    public MaterialDTO() {
    }

    @Override
    public Material build() throws InvalidValueException {
        Material material = new Material();

        material.setId(id != null && id > 0 ? id : null);

        if(name == null || name.isBlank()) throw new InvalidValueException("Nome Inválido");
        material.setName(name);

        if(minQuantity <= 0) throw new InvalidValueException("Quantidade Mínima tem que ser positivo");
        material.setMinQuantity(minQuantity);

        if(quantity <= minQuantity) throw new InvalidValueException("Quantidade Inválida");
        material.setQuantity(quantity);

        material.setEmployee(employee.build());

        return material;
    }
}
