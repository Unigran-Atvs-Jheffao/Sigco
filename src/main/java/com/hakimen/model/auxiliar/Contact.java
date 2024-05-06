package com.hakimen.model.auxiliar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String mainPhoneNumber;
    private String secondPhoneNumber;

    public Integer getId() {
        return id;
    }

    public Contact setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMainPhoneNumber() {
        return mainPhoneNumber;
    }

    public Contact setMainPhoneNumber(String mainPhoneNumber) {
        this.mainPhoneNumber = mainPhoneNumber;
        return this;
    }

    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public Contact setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
        return this;
    }
}
