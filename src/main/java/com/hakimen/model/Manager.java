package com.hakimen.model;

import javax.persistence.*;

@Entity
public class Manager{

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "login_id")
    private Login login;

    public Integer getId() {
        return id;
    }

    public Manager setId(Integer id) {
        this.id = id;
        return this;
    }

    public Login getLogin() {
        return login;
    }

    public Manager setLogin(Login login) {
        this.login = login;
        return this;
    }
}
