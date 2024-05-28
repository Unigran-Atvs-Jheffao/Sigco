package com.hakimen.view.components;

import com.hakimen.utils.ViewUtils;
import com.hakimen.view.IRegistrable;
import com.hakimen.view.View;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;

public class CreateAppointmentPanel extends JPanel implements IRegistrable, View {

    JFormattedTextField cpf;
    JDateChooser appointmentDate;
    JFormattedTextField appointmentTime;
    JFormattedTextField value;
    JFormattedTextField dentist;
    JList<String> attachments;

    public CreateAppointmentPanel() {
        addComponents();
        attachActions();
    }

    @Override
    public void save() {
        // TODO implementar funcionalidade de save usando o Appointment Controller
    }

    @Override
    public void addComponents() {
        cpf = ViewUtils.makeFormatted("###.###.###-##");
        appointmentDate = new JDateChooser();
        appointmentTime = ViewUtils.makeFormatted("##:##");
        value = new JFormattedTextField(new NumberFormatter());
        dentist =  new JFormattedTextField(new NumberFormatter());

        DefaultListModel<String> model = new DefaultListModel<>();
        model.add(0,"file 1");
        model.add(1,"file 2");
        model.add(2,"file 3");
        model.add(3,"file 4");
        model.add(4,"file 5");
        model.add(5,"file 6");

        attachments = new JList<>(model);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel all = new JPanel();
        all.setLayout(new GridLayout(3, 2));

        all.add(ViewUtils.makeFieldAndLabel("CPF do Paciente", cpf));
        all.add(ViewUtils.makeFieldAndLabel("CRO do Dentista", dentist));
        all.add(ViewUtils.makeFieldAndLabel("Data da Consulta", appointmentDate));
        all.add(ViewUtils.makeFieldAndLabel("Horario", appointmentTime));
        all.add(ViewUtils.makeFieldAndLabel("Valor", value));
        all.add(Box.createGlue());

        JScrollPane scrollpane = new JScrollPane(attachments);
        scrollpane.setBorder(new EmptyBorder(10,10,10,10));

        add(all);
        add(scrollpane,BorderLayout.SOUTH);
    }

    @Override
    public void attachActions() {

    }
}
