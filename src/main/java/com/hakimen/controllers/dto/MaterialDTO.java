package com.hakimen.controllers.dto;

import com.hakimen.controllers.EmployeeController;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Employee;
import com.hakimen.model.Material;

import javax.persistence.NoResultException;

public class MaterialDTO implements DTO<Material> {

    private String name;
    private Integer quantity;
    private Integer minQuantity;

    private Integer employeeId;

    @Override
    public Material build() throws InvalidValueException {
        Material material = new Material();

        if(name == null || name.isBlank()) throw new InvalidValueException("Nome Inválido");
        material.setName(name);

        if(minQuantity <= 0) throw new InvalidValueException("Quantidade Mínima tem que ser positivo");
        material.setMinQuantity(minQuantity);

        if(quantity <= minQuantity) throw new InvalidValueException("Quantidade Inválida");
        material.setQuantity(quantity);

        try{
            Employee employee = EmployeeController.getDAO().getById(employeeId);
            material.setEmployee(employee);
        } catch (NoResultException exception) {
            throw new InvalidValueException("Funcionario Inválido", exception);
        }

        return material;
    }
}
