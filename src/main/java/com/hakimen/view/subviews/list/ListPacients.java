package com.hakimen.view.subviews.list;

import com.hakimen.controllers.MaterialController;
import com.hakimen.controllers.PacientController;
import com.hakimen.controllers.dto.MaterialDTO;
import com.hakimen.controllers.dto.PacientDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.view.GenericListView;
import com.hakimen.view.GenericRegisterView;
import com.hakimen.view.IDisplayable;
import com.hakimen.view.subviews.register.PacientRegisterPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public class ListPacients implements IDisplayable {
    @Override
    public void setupTableColumns(DefaultTableModel tableModel, JTable table) {
        tableModel.addColumn("id");
        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Data de Nascimento");
        tableModel.addColumn("Responsável");
        tableModel.addColumn("Endereço");

        table.removeColumn(table.getColumnModel().getColumn(0));
    }

    @Override
    public void get(DefaultTableModel tableModel, JTable table, boolean isAscending, String filterKey, String searchString) {
        tableModel.setRowCount(0);

        List<PacientDTO> pacients = PacientController.INSTANCE.findAllFiltered(isAscending,getFilter().get(filterKey), searchString);

        for (PacientDTO pacient : pacients) {
            tableModel.addRow(new Object[]{
                    pacient.getId(),
                    pacient.getName(),
                    pacient.getCpf(),
                    pacient.getDateOfBirth(),
                    pacient.getResponsible(),
                    pacient.getAddress().getStreet() + " " + pacient.getHomeNumber() + ", " + pacient.getAddress().getCity().getName() + " - " + pacient.getAddress().getCity().getState().getName()
            });
        }
    }

    @Override
    public void edit(DefaultTableModel tableModel, JTable table, int row) {
        Integer id = (Integer) tableModel.getValueAt(row,0);
        try {
            PacientDTO pacientDTO = PacientController.INSTANCE.getById(id);
            new GenericRegisterView("Editar Paciente", new PacientRegisterPanel(pacientDTO));
        } catch (InvalidValueException e){
            JOptionPane.showMessageDialog(null,"Erro: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void remove(DefaultTableModel tableModel, JTable table, int row) {
        try {
            PacientDTO dto = PacientController.INSTANCE.getById((Integer) tableModel.getValueAt(row,0));
            PacientController.INSTANCE.remove(dto);
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null,"Erro: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void register() {
        new GenericRegisterView("Cadastrar Paciente", new PacientRegisterPanel());
    }

    @Override
    public Map<String, String> getFilter() {
        return Map.ofEntries(
                Map.entry("Id", "id")
        );
    }

    @Override
    public JMenuBar addMenuItems(GenericListView genericListView, JMenuBar bar) {
        JMenu paciente = new JMenu("Paciente");

        JMenuItem verProntuario = new JMenuItem("Ver Prontuário");

        paciente.add(verProntuario);

        verProntuario.addActionListener((e) -> {
            int row = genericListView.getTable().getSelectedRow();
            if(row != -1){
                String name = (String) genericListView.getTable().getValueAt(row, 0);
                new GenericListView("Prontuário de "+name, new ListMedicalRecordsForPacient(new PacientDTO().setName(name)));
            }else {
                JOptionPane.showMessageDialog(null, "Escolha uma linha na tabela");
            }
        });

        bar.add(paciente);

        return bar;
    }
}
