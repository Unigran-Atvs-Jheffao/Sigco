package com.hakimen.utils;

import com.hakimen.view.GenericListView;
import com.hakimen.view.subviews.list.ListEmployees;
import com.hakimen.view.subviews.list.ListMaterials;
import com.hakimen.view.subviews.list.ListScheduling;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ViewUtils {
    public static final DateFormatter DATE_FORMATTER = new DateFormatter(new SimpleDateFormat("dd/MM/yyyy"));
    public static JPanel makeFieldAndLabel(String fieldName, JComponent component) {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(0, 10, 0, 10));
        panel.setLayout(new GridLayout(2, 1));

        JLabel label = new JLabel(fieldName);
        label.setBorder(new EmptyBorder(10, 0, 10, 0));

        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(component);

        return panel;
    }

    public static JPanel makeFieldWithAddButtonAndLabel(String fieldName, JComponent component, JButton add) {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(0, 10, 0, 10));
        panel.setLayout(new GridLayout(2, 1));

        JLabel label = new JLabel(fieldName);
        label.setBorder(new EmptyBorder(10, 0, 10, 0));

        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.X_AXIS));

        newPanel.add(component);
        newPanel.add(add);

        panel.add(newPanel);

        return panel;
    }

    public static JFormattedTextField makeFormatted(String format){
        try {
            return new JFormattedTextField(
                    new MaskFormatter(format)
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createWindow(){
        switch (AppContext.getLoggedAs().getLogin().getRole().getId()){
            case 1 -> {
                new GenericListView("Funcionários", new ListEmployees());
            }    //Admin
            case 2,4 -> {
                new GenericListView("Consultas", new ListScheduling());
            }    //Dentista, Recepcionista
            case 3 -> {
                new GenericListView("Materiais", new ListMaterials());
            }    //Gerente
        }
    }
}
