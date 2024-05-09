package com.hakimen.model;

import javax.persistence.*;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    private String name;
    private Integer quantity;
    private Integer minQuantity;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public Material setEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Material setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Material setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Material setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public Material setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
        return this;
    }

}
