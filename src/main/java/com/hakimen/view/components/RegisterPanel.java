package com.hakimen.view.components;

import com.hakimen.view.View;

import javax.swing.*;

public abstract class RegisterPanel<T> extends JPanel implements View {
    T type;


    public RegisterPanel() {
    }

    public RegisterPanel(T type){
        this.type = type;
    }
    public abstract boolean save();
    public abstract boolean edit();

    public T getType() {
        return type;
    }

    public RegisterPanel<T> setType(T type) {
        this.type = type;
        return this;
    }
}
