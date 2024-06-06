package com.hakimen.view.subviews.list;

import com.hakimen.controllers.PaymentMethodController;
import com.hakimen.controllers.SchedulingController;
import com.hakimen.controllers.dto.PaymentMethodDTO;
import com.hakimen.controllers.dto.SchedulingDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.PaymentMethod;
import com.hakimen.view.GenericRegisterView;
import com.hakimen.view.IDisplayable;
import com.hakimen.view.subviews.register.ScheduleAppointmentPanel;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

import static com.hakimen.utils.ViewUtils.DATE_FORMATTER;

public class ListScheduling implements IDisplayable {
    @Override
    public void setupTableColumns(DefaultTableModel tableModel, JTable table) {
        tableModel.addColumn("id");
        tableModel.addColumn("Data");
        tableModel.addColumn("Horario");
        tableModel.addColumn("Paciente");
        tableModel.addColumn("Dentista");
        tableModel.addColumn("Recepcionista");
        tableModel.addColumn("Valor (R$)");
        tableModel.addColumn("Pago com");

        table.removeColumn(table.getColumnModel().getColumn(0));
    }

    @Override
    public void get(DefaultTableModel tableModel, JTable table, boolean isAscending, String filterKey, String searchString) {
        tableModel.setRowCount(0);

        List<SchedulingDTO> dtos = SchedulingController.INSTANCE.findAllFiltered(isAscending,getFilter().get(filterKey), searchString);

        for (SchedulingDTO dto : dtos) {
            PaymentMethodDTO payment = null;
            try {
                payment = PaymentMethodController.INSTANCE.findByScheduleId(dto.getId());
            } catch (NoResultException | InvalidValueException e) {

            }

            tableModel.addRow(new Object[]{
                    dto.getId(),
                    DATE_FORMATTER.getFormat().format(dto.getDate()),
                    dto.getAppointmentTime(),
                    dto.getPacient().getName(),
                    dto.getAppointment().getWithDentist().getLogin().getUsername(),
                    dto.getReceptionist().getLogin().getUsername(),
                    dto.getAppointment().getValue(),
                    payment != null ? payment.getType().getName() : "Não Pago",
            });
        }
    }

    @Override
    public void edit(DefaultTableModel tableModel, JTable table, int row) {
        try {
            SchedulingDTO schedulingDTO = SchedulingController.INSTANCE.getById((Integer) tableModel.getValueAt(row,0));
            new GenericRegisterView("Editar Agendamento", new ScheduleAppointmentPanel(schedulingDTO));
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null,"Erro: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void remove(DefaultTableModel tableModel, JTable table, int row) {
        try {
            SchedulingDTO dto = SchedulingController.INSTANCE.getById((Integer) tableModel.getValueAt(row,0));

            try {
                PaymentMethodDTO paymentMethodDTO = PaymentMethodController.INSTANCE.findByScheduleId(dto.getId());

                PaymentMethodController.INSTANCE.remove(paymentMethodDTO);
            } catch (NoResultException e) {
                
            }

            SchedulingController.INSTANCE.remove(dto);
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null,"Erro: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void register() {
        new GenericRegisterView("Registrar Agendamento", new ScheduleAppointmentPanel());
    }

    @Override
    public Map<String, String> getFilter() {
        return Map.ofEntries(
                Map.entry("Id", "id"),
                Map.entry("Data", "date"),
                Map.entry("Horário", "appointmentTime"),
                Map.entry("Paciente", "pacient.name"),
                Map.entry("Dentista", "dentist.login.username"),
                Map.entry("Recepcionista", "receptionist.login.username"),
                Map.entry("Valor (R$)", "appointment.value")
        );
    }
}
