package com.hakimen.view.subviews.register;

import com.hakimen.controllers.*;
import com.hakimen.controllers.dto.*;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Scheduling;
import com.hakimen.utils.ViewUtils;
import com.hakimen.view.GenericRegisterView;
import com.hakimen.view.View;
import com.hakimen.view.components.RegisterPanel;
import com.hakimen.view.subviews.register.auxiliar.PaymentRegisterPanel;
import com.toedter.calendar.JDateChooser;

import javax.persistence.NoResultException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleAppointmentPanel extends RegisterPanel<SchedulingDTO> implements View {

    JFormattedTextField cpf;
    JDateChooser appointmentDate;
    JFormattedTextField appointmentTime;
    JFormattedTextField value;
    JTextField dentist;
    JTextField receptionist;
    JList<String> attachments;
    JButton pay;

    public ScheduleAppointmentPanel() {
        addComponents();
        attachActions();
    }

    public ScheduleAppointmentPanel(SchedulingDTO type) {
        super(type);
        addComponents();
        attachActions();
    }

    @Override
    public boolean save() {
        String pacientCPF = cpf.getText();
        String receptionistName = receptionist.getText();
        Date date = appointmentDate.getDate();
        String time = (String) appointmentTime.getValue();
        Float cost = Float.parseFloat(value.getValue().toString());
        String dentistName = dentist.getText();

        try {

            AppointmentDTO appointmentDTO = new AppointmentDTO();
            appointmentDTO.setValue(cost);
            appointmentDTO.setAttachments(new ArrayList<>());
            appointmentDTO.setWithDentist(EmployeeController.INSTANCE.getByNameIfRoleIdMatches(dentistName, id -> id == 2 || id == 1));

            SchedulingDTO schedulingDTO = new SchedulingDTO();
            schedulingDTO.setDentist(appointmentDTO.getWithDentist());
            schedulingDTO.setAppointment(appointmentDTO);
            schedulingDTO.setDate(date);
            schedulingDTO.setAppointmentTime(time);
            schedulingDTO.setReceptionist(EmployeeController.INSTANCE.getByNameIfRoleIdMatches(receptionistName, id -> id == 4 || id == 1));

            PacientDTO pacientDTO = PacientController.INSTANCE.getByCPF(pacientCPF);
            schedulingDTO.setPacient(pacientDTO);

            MedicalRecordController.INSTANCE.insert(new MedicalRecordDTO()
                    .setForPacient(pacientDTO)
                    .setHistory(schedulingDTO)
            );
            return true;
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro : %s".formatted(e.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    @Override
    public boolean edit() {
        String pacientCPF = cpf.getText();
        String receptionistName = receptionist.getText();
        Date date = appointmentDate.getDate();
        String time = (String) appointmentTime.getValue();
        Float cost = Float.parseFloat(value.getValue().toString());
        String dentistName = dentist.getText();

        try {
            AppointmentDTO appointmentDTO = getType().getAppointment();
            appointmentDTO.setValue(cost);
            appointmentDTO.setAttachments(new ArrayList<>());
            appointmentDTO.setWithDentist(EmployeeController.INSTANCE.getByNameIfRoleIdMatches(dentistName, id -> id == 2 || id == 1));

            SchedulingDTO schedulingDTO = new SchedulingDTO();
            schedulingDTO.setDentist(appointmentDTO.getWithDentist());
            schedulingDTO.setId(getType().getId());
            schedulingDTO.setAppointment(appointmentDTO);
            schedulingDTO.setDate(date);
            schedulingDTO.setAppointmentTime(time);
            schedulingDTO.setPacient(PacientController.INSTANCE.getByCPF(pacientCPF));
            schedulingDTO.setReceptionist(EmployeeController.INSTANCE.getByNameIfRoleIdMatches(receptionistName, id -> id == 4 || id == 1));


            SchedulingController.INSTANCE.update(schedulingDTO);

            MedicalRecordController.INSTANCE.update(
                    MedicalRecordController.INSTANCE.getByAppointment(appointmentDTO.getId())
                                .setForPacient(schedulingDTO.getPacient()
                            )
            );
            return true;
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro : %s".formatted(e.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }


    @Override
    public void addComponents() {

        cpf = ViewUtils.makeFormatted("###.###.###-##");
        appointmentDate = new JDateChooser();
        appointmentTime = ViewUtils.makeFormatted("##:##");
        receptionist = new JTextField(32);
        value = new JFormattedTextField(
                new NumberFormatter(
                        new DecimalFormat("#,#00.00#;(#,#00.00#)")
                )
        );

        dentist = new JTextField(32);

        DefaultListModel<String> model = new DefaultListModel<>();
        attachments = new JList<>(model);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel all = new JPanel();
        all.setLayout(new GridLayout(7, 1));

        all.add(ViewUtils.makeFieldAndLabel("CPF do Paciente", cpf));
        all.add(ViewUtils.makeFieldAndLabel("Nome do Dentista", dentist));
        all.add(ViewUtils.makeFieldAndLabel("Nome do Recepcionista", receptionist));
        all.add(ViewUtils.makeFieldAndLabel("Data da Consulta", appointmentDate));
        all.add(ViewUtils.makeFieldAndLabel("Horario", appointmentTime));
        all.add(ViewUtils.makeFieldAndLabel("Valor", value));

        JPanel payPanel = new JPanel();
        payPanel.setLayout(new BorderLayout());
        payPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        pay = new JButton("Pagar");

        try {
            if (getType() != null) {
                PaymentMethodController.INSTANCE.findByScheduleId(getType().getId());
            }
            pay.setEnabled(false);
        } catch (NoResultException | InvalidValueException e) {
            pay.setEnabled(true);
        }

        payPanel.add(pay);
        all.add(payPanel);

        add(all);

        if (getType() != null) {
            cpf.setValue(getType().getPacient().getCpf());
            dentist.setText(getType().getAppointment().getWithDentist().getLogin().getUsername());
            receptionist.setText(getType().getReceptionist().getLogin().getUsername());
            appointmentDate.setDate(getType().getDate());
            appointmentTime.setValue(getType().getAppointmentTime());
            value.setValue(getType().getAppointment().getValue());
        }
    }

    @Override
    public void attachActions() {
        if (getType() != null) {
            pay.addActionListener(e -> {
                new GenericRegisterView("Pagamento", new PaymentRegisterPanel(getType().getId()));
            });
        }
    }
}
