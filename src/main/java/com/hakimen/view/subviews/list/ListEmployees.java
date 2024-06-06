package com.hakimen.view.subviews.list;

import com.hakimen.controllers.EmployeeController;
import com.hakimen.controllers.dto.EmployeeDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.view.GenericRegisterView;
import com.hakimen.view.IDisplayable;
import com.hakimen.view.subviews.register.EmployeeRegisterPanel;
import org.eclipse.persistence.internal.jaxb.many.MapEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public class ListEmployees implements IDisplayable {
    @Override
    public void setupTableColumns(DefaultTableModel tableModel, JTable table) {
        tableModel.addColumn("id");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Cargo");
        tableModel.addColumn("Registro");

        table.removeColumn(table.getColumnModel().getColumn(0));
    }

    @Override
    public void get(DefaultTableModel tableModel, JTable table, boolean isAscending, String filterKey, String searchString) {
        tableModel.setRowCount(0);

        List<EmployeeDTO> dtos = EmployeeController.INSTANCE.findAllFiltered(isAscending, getFilter().get(filterKey), searchString);

        for (EmployeeDTO dto : dtos) {
            tableModel.addRow(new Object[]{
                    dto.getId(),
                    dto.getLogin().getUsername(),
                    dto.getLogin().getRole().getName(),
                    dto.getRegistration()
            });
        }
    }

    @Override
    public void edit(DefaultTableModel tableModel, JTable table, int row) {

        Integer id = (Integer) tableModel.getValueAt(row, 0);
        try {
            EmployeeDTO dto = EmployeeController.INSTANCE.getById(id);
            new GenericRegisterView("Editar Funcionario", new EmployeeRegisterPanel(dto));
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro: %s".formatted(e.getMessage()));
        }


    }

    @Override
    public void remove(DefaultTableModel tableModel, JTable table, int row) {
        Integer id = (Integer) tableModel.getValueAt(row, 0);
        try {
            EmployeeController.INSTANCE.remove(EmployeeController.INSTANCE.getById(id));
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void register() {
        new GenericRegisterView("Cadastrar Funcionario", new EmployeeRegisterPanel());
    }

    @Override
    public Map<String, String> getFilter() {
        return Map.ofEntries(
                Map.entry("Id", "id"),
                Map.entry("Nome", "login.username"),
                Map.entry("Cargo", "login.role.name"),
                Map.entry("Registro", "registration")
        );
    }
}
