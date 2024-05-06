package com.hakimen.model;

import javax.persistence.*;

@Entity
public class Receptionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "login_id")
    private Login login;

    public Integer getId() {
        return id;
    }

    public Receptionist setId(Integer id) {
        this.id = id;
        return this;
    }

    public Login getLogin() {
        return login;
    }

    public Receptionist setLogin(Login login) {
        this.login = login;
        return this;
    }
}