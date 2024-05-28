package com.hakimen.view.components;

import com.hakimen.utils.ViewUtils;
import com.hakimen.view.IRegistrable;
import com.hakimen.view.View;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class AddPacientPanel extends JPanel implements IRegistrable, View {

    JTextField name;
    JFormattedTextField cpf;
    JDateChooser dateOfBirth;
    JTextField homeNumber;
    JTextField responsible;


    public AddPacientPanel() {
        addComponents();
        attachActions();
    }

    @Override
    public void save() {
        //TODO implementar funcionalidade de save usando o Pacient Controller
    }

    @Override
    public void addComponents() {
        name = new JTextField(32);
        cpf = ViewUtils.makeFormatted("###.###.###-##");

        dateOfBirth = new JDateChooser();
        homeNumber = new JTextField(32);
        responsible = new JTextField(32);

        setLayout(new GridLayout(5, 1));

        add(ViewUtils.makeFieldAndLabel("Nome", name));
        add(ViewUtils.makeFieldAndLabel("CPF", cpf));
        add(ViewUtils.makeFieldAndLabel("Data de Nascimento", dateOfBirth));
        add(ViewUtils.makeFieldAndLabel("Numero da Casa", homeNumber));
        add(ViewUtils.makeFieldAndLabel("Responsável", responsible));
    }

    @Override
    public void attachActions() {

    }
}
