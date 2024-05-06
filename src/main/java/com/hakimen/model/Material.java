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
    @JoinColumn(name = "manager_id")
    private Manager manager;

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

    public Manager getManager() {
        return manager;
    }

    public Material setManager(Manager manager) {
        this.manager = manager;
        return this;
    }
}
