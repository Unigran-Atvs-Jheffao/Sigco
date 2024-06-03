package com.hakimen.view.subviews.list;

import com.hakimen.controllers.AppointmentController;
import com.hakimen.controllers.EmployeeController;
import com.hakimen.controllers.MedicalRecordController;
import com.hakimen.controllers.dto.AppointmentDTO;
import com.hakimen.controllers.dto.EmployeeDTO;
import com.hakimen.controllers.dto.MedicalRecordDTO;
import com.hakimen.controllers.dto.PacientDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.MedicalRecord;
import com.hakimen.view.GenericListView;
import com.hakimen.view.GenericRegisterView;
import com.hakimen.view.IDisplayable;
import com.hakimen.view.subviews.register.EmployeeRegisterPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public class ListMedicalRecordsForPacient implements IDisplayable {

    PacientDTO pacientDTO;

    public ListMedicalRecordsForPacient(PacientDTO pacientDTO) {
        this.pacientDTO = pacientDTO;
    }

    @Override
    public void setupTableColumns(DefaultTableModel tableModel, JTable table) {

        tableModel.addColumn("id");
        tableModel.addColumn("Dentista");
        tableModel.addColumn("Observações");
        tableModel.addColumn("Valor (R$)");

        table.removeColumn(table.getColumnModel().getColumn(0));
    }

    @Override
    public void get(DefaultTableModel tableModel, JTable table, boolean isAscending, String filterKey, String searchString) {
        tableModel.setRowCount(0);

        List<MedicalRecordDTO> dto = MedicalRecordController.INSTANCE.findAllFiltered(pacientDTO.getName(), isAscending, getFilter().get(filterKey), searchString);

        for (MedicalRecordDTO medicalRecordDTO : dto) {
            tableModel.addRow(
                    new Object[]{
                            medicalRecordDTO.getId(),
                            medicalRecordDTO.getHistory().getWithDentist().getLogin().getUsername(),
                            medicalRecordDTO.getHistory().getObservations(),
                            medicalRecordDTO.getHistory().getValue(),
                    }
            );
        }
    }

    @Override
    public void edit(DefaultTableModel tableModel, JTable table, int row) {
        //No need to implement
    }

    @Override
    public void remove(DefaultTableModel tableModel, JTable table, int row) {
        //No need to implement
    }

    @Override
    public void register() {
        // No need to implement
    }

    @Override
    public Map<String, String> getFilter() {
        return Map.ofEntries(
                Map.entry("Id", "id"),
                Map.entry("Dentista", "history.withDentist"),
                Map.entry("Observações", "history.observations"),
                Map.entry("Valor (R$)", "history.value")
        );
    }

    @Override
    public boolean[] enabledButtons() {
        return new boolean[]{
                false,
                false,
                false
        };
    }

    @Override
    public JMenuBar addMenuItems(GenericListView genericListView, JMenuBar bar) {
        genericListView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return new JMenuBar();
    }
}
