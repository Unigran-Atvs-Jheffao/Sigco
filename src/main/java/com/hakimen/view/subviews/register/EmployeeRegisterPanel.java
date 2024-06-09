package com.hakimen.view.subviews.register;

import com.hakimen.controllers.EmployeeController;
import com.hakimen.controllers.RoleController;
import com.hakimen.controllers.dto.EmployeeDTO;
import com.hakimen.controllers.dto.LoginDTO;
import com.hakimen.controllers.dto.RoleDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.utils.HashingUtils;
import com.hakimen.utils.ViewUtils;
import com.hakimen.view.components.RegisterPanel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;


public class EmployeeRegisterPanel extends RegisterPanel<EmployeeDTO> {

    private static final Log log = LogFactory.getLog(EmployeeRegisterPanel.class);
    public JTextField nameField;
    public JPasswordField passwordField;
    public JComboBox<String> roleComboBox;
    public JTextField registration;


    public EmployeeRegisterPanel(EmployeeDTO loginDTO){
        super(loginDTO);
        addComponents();
        attachActions();
    }
    public EmployeeRegisterPanel() {
        super();
        addComponents();
        attachActions();
    }


    @Override
    public boolean save() {
        String name = nameField.getText();
        String password = passwordField.getText();
        Integer role = roleComboBox.getSelectedIndex();

        RoleDTO dto;
        try {
            dto = RoleController.INSTANCE.getById(role+1);
        } catch (InvalidValueException e) {
            throw new RuntimeException(e);
        }

        LoginDTO login = new LoginDTO();
        login.setUsername(name);
        login.setPassword(HashingUtils.sha256(password));
        login.setRole(dto);

        EmployeeDTO employee = new EmployeeDTO();
        employee.setLogin(login);
        employee.setRegistration(registration.getText());

        try {
            EmployeeController.INSTANCE.insert(employee);
            return true;
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro: %s".formatted(e.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean edit() {
        String name = nameField.getText();
        String password = passwordField.getText();
        String registrar = registration.getText();
        Integer role = roleComboBox.getSelectedIndex();

        RoleDTO dto;
        try {
            dto = RoleController.INSTANCE.getById(role+1);
        } catch (InvalidValueException e) {
            throw new RuntimeException(e);
        }

        LoginDTO login = new LoginDTO();
        login.setId(getType().getLogin().getId());
        login.setUsername(name);
        login.setPassword(!password.isBlank() ? HashingUtils.sha256(password) : getType().getLogin().getPassword());
        login.setRole(dto);


        EmployeeDTO employee = new EmployeeDTO();
        employee.setId(getType().getId());
        employee.setLogin(login);
        employee.setRegistration(registrar);

        try {
            EmployeeController.INSTANCE.update(employee);
        } catch (InvalidValueException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void addComponents() {
        setLayout(new GridLayout(4, 1));

        nameField = new JTextField(32);
        passwordField = new JPasswordField(32);
        registration = new JTextField(32);
        roleComboBox = new JComboBox<>(
                RoleController.INSTANCE.getAll().stream().map(RoleDTO::getName).toArray(String[]::new)
        );

        add(ViewUtils.makeFieldAndLabel("Nome", nameField));
        add(ViewUtils.makeFieldAndLabel("Senha", passwordField));
        add(ViewUtils.makeFieldAndLabel("Cargo", roleComboBox));
        add(ViewUtils.makeFieldAndLabel("Codigo de Registro", registration));

        if(getType() != null){
            nameField.setText(getType().getLogin().getUsername());
            roleComboBox.setSelectedIndex(getType().getLogin().getRole().getId()-1);
            registration.setText(getType().getRegistration());
        }
    }

    @Override
    public void attachActions() {

    }
}
