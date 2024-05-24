package com.hakimen.view.components;

import com.hakimen.utils.ViewUtils;
import com.hakimen.view.IRegistrable;
import com.hakimen.view.View;

import javax.swing.*;
import java.awt.*;


public class LoginRegisterPanel extends JPanel implements IRegistrable, View {

    public JTextField nameField;
    public JTextField passwordField;
    public JComboBox<String> roleComboBox;

    public LoginRegisterPanel() {
        addComponents();
        attachActions();
    }


    @Override
    public void save() {
        //TODO implementar funcionalidade de save usando o Login Controller
    }

    @Override
    public void addComponents() {
        setLayout(new GridLayout(3, 1));

        nameField = new JTextField(32);
        passwordField = new JTextField(32);
        roleComboBox = new JComboBox<>(
                new String[]{
                        "Administrador",
                        "Recepcionista",
                        "Dentista",
                        "Gerente"
                }
                //TODO trocar isso aqui pra usar o controlador de Roles
        );

        add(ViewUtils.makeFieldAndLabel("Nome", nameField));
        add(ViewUtils.makeFieldAndLabel("Senha", passwordField));
        add(ViewUtils.makeFieldAndLabel("Cargo", roleComboBox));
    }

    @Override
    public void attachActions() {

    }
}
