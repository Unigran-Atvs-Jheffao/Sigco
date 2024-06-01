package com.hakimen.utils;

import com.hakimen.controllers.dto.EmployeeDTO;
import com.hakimen.model.Employee;
import com.hakimen.view.GenericListView;
import com.hakimen.view.subviews.list.ListEmployees;
import com.hakimen.view.subviews.list.ListMaterials;
import com.hakimen.view.subviews.list.ListPacients;
import com.hakimen.view.subviews.list.ListScheduling;

import javax.swing.*;

public class AppContext {
    static EmployeeDTO loggedAs;
    static JMenuBar bar;

    public static JMenuBar getBar(JFrame frame) {
        bar = new JMenuBar();
        JMenu list = new JMenu("List");

        Integer roleId = loggedAs.getLogin().getRole().getId();

        if(roleId == 1 || roleId == 2 || roleId == 4) {
            JMenuItem schedulings = new JMenuItem("Consultas");
            schedulings.addActionListener(e -> {
                new GenericListView("Consultas", new ListScheduling());
                frame.dispose();
            });
            list.add(schedulings);

            JMenuItem pacients = new JMenuItem("Pacientes");
            pacients.addActionListener(e -> {
                new GenericListView("Pacientes", new ListPacients());
                frame.dispose();
            });
            list.add(pacients);
        }

        if(roleId == 1 || roleId == 3) {
            JMenuItem employees = new JMenuItem("Funcionarios");
            employees.addActionListener(e -> {
                new GenericListView("Funcionarios", new ListEmployees());
                frame.dispose();
            });
            list.add(employees);

            JMenuItem materials = new JMenuItem("Materiais");
            materials.addActionListener(e -> {
                new GenericListView("Materiais", new ListMaterials());
                frame.dispose();
            });
            list.add(materials);
        }

        bar.add(list);

        return bar;
    }


    public static EmployeeDTO getLoggedAs() {
        return loggedAs;
    }

    public static void setLoggedAs(EmployeeDTO loggedAs) {
        AppContext.loggedAs = loggedAs;
    }
}
