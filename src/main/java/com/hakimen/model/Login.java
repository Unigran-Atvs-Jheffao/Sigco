package com.hakimen.model;

import javax.persistence.*;

@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public Login setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Login setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Login setPassword(String password) {
        this.password = password;
        return this;
    }
}
