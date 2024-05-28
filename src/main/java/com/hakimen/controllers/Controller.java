package com.hakimen.controllers;

import com.hakimen.controllers.dto.DTO;
import com.hakimen.exceptions.InvalidValueException;

import java.util.List;

public interface Controller<T extends DTO<?>> {
    void insert(T type) throws InvalidValueException;
    void remove(T type) throws InvalidValueException;
    void update(T type) throws InvalidValueException;

    List<T> getAll();
    T getById(int id) throws InvalidValueException;
}
