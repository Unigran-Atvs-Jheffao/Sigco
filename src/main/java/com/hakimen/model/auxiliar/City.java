package com.hakimen.model.auxiliar;

import javax.persistence.*;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    public Integer getId() {
        return id;
    }

    public City setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public State getState() {
        return state;
    }

    public City setState(State state) {
        this.state = state;
        return this;
    }
}
