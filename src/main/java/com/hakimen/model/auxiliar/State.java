package com.hakimen.model.auxiliar;

import javax.persistence.*;

@Entity
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public State setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public State setName(String name) {
        this.name = name;
        return this;
    }
}
