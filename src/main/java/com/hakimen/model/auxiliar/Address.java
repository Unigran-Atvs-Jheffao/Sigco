package com.hakimen.model.auxiliar;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    private String street;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Integer getId() {
        return id;
    }

    public Address setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public City getCity() {
        return city;
    }

    public Address setCity(City city) {
        this.city = city;
        return this;
    }
}
