package com.hakimen.view;

import com.hakimen.controllers.EmployeeController;
import com.hakimen.controllers.dto.EmployeeDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.utils.AppContext;
import com.hakimen.utils.HashingUtils;
import com.hakimen.utils.ViewUtils;
import com.hakimen.view.subviews.register.EmployeeRegisterPanel;

import javax.persistence.NoResultException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements View {

    JTextField username;
    JPasswordField password;

    JButton login;
    JButton register;
    JButton cancel;

    public LoginView() {
        setTitle("Login");

        addComponents();
        attachActions();

        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public void addComponents() {
        username = new JTextField(32);
        password = new JPasswordField(32);
        login = new JButton("Login");
        register = new JButton("Registrar");
        cancel = new JButton("Cancelar");

        JPanel fieldsPanel = new JPanel();

        fieldsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        fieldsPanel.setLayout(new GridLayout(2, 1));

        fieldsPanel.add(ViewUtils.makeFieldAndLabel("Usuário", username));
        fieldsPanel.add(ViewUtils.makeFieldAndLabel("Senha", password));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        buttonsPanel.add(login);
        buttonsPanel.add(register);
        buttonsPanel.add(cancel);

        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    @Override
    public void attachActions() {
        register.addActionListener(e -> {
            new GenericRegisterView("Registrar Funcionário", new EmployeeRegisterPanel());
        });

        ActionListener loginListener = e -> {
            String name = username.getText();
            String pass = password.getText();


            try {
                EmployeeDTO login = EmployeeController.INSTANCE.getByUserAndPassword(name, HashingUtils.sha256(pass));
                if (login != null) {
                    AppContext.setLoggedAs(login);
                    this.dispose();
                    ViewUtils.createWindow();
                }
            } catch (InvalidValueException ex) {
                throw new RuntimeException(ex);
            } catch (NoResultException ex) {
                JOptionPane.showMessageDialog(null, "Informações incorretas ou inexistentes");
            }
        };

        login.addActionListener(loginListener);
        password.addActionListener(loginListener);
        username.addActionListener(loginListener);

        cancel.addActionListener(e -> {
            this.dispose();
        });
    }
}
