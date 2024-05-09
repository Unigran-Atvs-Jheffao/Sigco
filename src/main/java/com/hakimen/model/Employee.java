package com.hakimen.model;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String registration;
    @OneToOne
    @JoinColumn(name = "login_id")
    private Login login;

    public Integer getId() {
        return id;
    }

    public Employee setId(Integer id) {
        this.id = id;
        return this;
    }

    public Login getLogin() {
        return login;
    }

    public Employee setLogin(Login login) {
        this.login = login;
        return this;
    }
}
