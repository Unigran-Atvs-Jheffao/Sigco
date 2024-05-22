package com.hakimen.controllers.dto;

import com.hakimen.exceptions.InvalidValueException;

public interface DTO<T>{
    T build() throws InvalidValueException;
}
