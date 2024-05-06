package com.hakimen.model;

import javax.persistence.*;

@Entity
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer cro;

    @OneToOne
    @JoinColumn(name = "login_id")
    private Login login;

    public Integer getCro() {
        return cro;
    }

    public Dentist setCro(Integer cro) {
        this.cro = cro;
        return this;
    }

    public Login getLogin() {
        return login;
    }

    public Dentist setLogin(Login login) {
        this.login = login;
        return this;
    }
}
