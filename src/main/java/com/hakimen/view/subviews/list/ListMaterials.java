package com.hakimen.view.subviews.list;

import com.hakimen.controllers.MaterialController;
import com.hakimen.controllers.dto.MaterialDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.view.GenericRegisterView;
import com.hakimen.view.IDisplayable;
import com.hakimen.view.subviews.register.MaterialRegisterPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public class ListMaterials implements IDisplayable {
    @Override
    public void setupTableColumns(DefaultTableModel tableModel, JTable table) {
        tableModel.addColumn("id");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Quantidade Minima");
        tableModel.addColumn("Responsavel");

        table.removeColumn(table.getColumnModel().getColumn(0));
    }

    @Override
    public void get(DefaultTableModel tableModel, JTable table, boolean isAscending, String filterKey, String searchString) {
        tableModel.setRowCount(0);

        List<MaterialDTO> dtos = MaterialController.INSTANCE.findAllFiltered(isAscending, getFilter().get(filterKey), searchString);
        for (MaterialDTO dto : dtos) {
            tableModel.addRow(new Object[]{
                    dto.getId(),
                    dto.getName(),
                    dto.getQuantity(),
                    dto.getMinQuantity(),
                    dto.getEmployee().getLogin().getUsername()
            });
        }
    }

    @Override
    public void edit(DefaultTableModel tableModel, JTable table, int row) {

        try {
            MaterialDTO dto = MaterialController.INSTANCE.getById((Integer) tableModel.getValueAt(row,0));

            new GenericRegisterView("Editar Material", new MaterialRegisterPanel(dto));
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null,"Erro: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void remove(DefaultTableModel tableModel, JTable table, int row) {

        try {
            MaterialDTO dto = MaterialController.INSTANCE.getById((Integer) tableModel.getValueAt(row,0));
            MaterialController.INSTANCE.remove(dto);
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null,"Erro: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void register() {
        new GenericRegisterView("Registrar Material", new MaterialRegisterPanel());
    }

    @Override
    public Map<String, String> getFilter() {
        return Map.ofEntries(
                Map.entry("Id","id"),
                Map.entry("Nome","name"),
                Map.entry("Quantidade","quantity"),
                Map.entry("Quantidade Minima","minQuantity"),
                Map.entry("Funcionario","employee")
        );
    }
}
