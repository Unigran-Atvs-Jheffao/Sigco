package com.hakimen.view.subviews.register;

import com.hakimen.controllers.MedicalRecordController;
import com.hakimen.controllers.PacientController;
import com.hakimen.controllers.auxiliar.AddressController;
import com.hakimen.controllers.auxiliar.CityController;
import com.hakimen.controllers.auxiliar.ContactController;
import com.hakimen.controllers.auxiliar.StateController;
import com.hakimen.controllers.dto.MedicalRecordDTO;
import com.hakimen.controllers.dto.PacientDTO;
import com.hakimen.controllers.dto.auxiliar.AddressDTO;
import com.hakimen.controllers.dto.auxiliar.CityDTO;
import com.hakimen.controllers.dto.auxiliar.ContactDTO;
import com.hakimen.controllers.dto.auxiliar.StateDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.MedicalRecord;
import com.hakimen.utils.ViewUtils;
import com.hakimen.view.GenericRegisterView;
import com.hakimen.view.View;
import com.hakimen.view.components.RegisterPanel;
import com.hakimen.view.subviews.register.auxiliar.CityRegisterPanel;
import com.toedter.calendar.JDateChooser;
import netscape.javascript.JSObject;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class PacientRegisterPanel extends RegisterPanel<PacientDTO> implements View {

    JTextField name;
    JFormattedTextField phoneNumber;
    JFormattedTextField cpf;
    JDateChooser dateOfBirth;
    JTextField homeNumber;
    JTextField responsible;


    JTextField street;
    JComboBox<String> city;
    JComboBox<String> state;

    JButton addState;
    JButton addCity;

    public PacientRegisterPanel(PacientDTO type) {
        super(type);
        addComponents();
        attachActions();
    }

    public PacientRegisterPanel() {
        addComponents();
        attachActions();
    }

    @Override
    public boolean save() {
        try {
            String pacientName = name.getText();
            String pacientCPF = cpf.getValue().toString();
            Date date = dateOfBirth.getDate();
            String home = homeNumber.getText();
            String respons = responsible.getText();
            String phone = (String) phoneNumber.getValue();

            PacientDTO pacientDTO = new PacientDTO();
            pacientDTO.setName(pacientName);

            MedicalRecordDTO medicalRecord = new MedicalRecordDTO()
                    .setHistory(new ArrayList<>());

            MedicalRecordController.INSTANCE.insert(medicalRecord);

            pacientDTO.setMedicalRecord(medicalRecord);

            pacientDTO.setCpf(pacientCPF);
            pacientDTO.setDateOfBirth(date);
            pacientDTO.setHomeNumber(home);
            pacientDTO.setResponsible(respons);

            pacientDTO.setContact(new ContactDTO()
                    .setValue(phone)
                    .setType(1));

            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(street.getText());

            CityDTO cityDTO = CityController.INSTANCE.getByName((String) city.getSelectedItem(), (String) state.getSelectedItem());

            addressDTO.setCity(cityDTO);
            pacientDTO.setAddress(addressDTO);

            PacientController.INSTANCE.insert(pacientDTO);
            return true;
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro %s:".formatted(e.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }


        return false;
    }

    @Override
    public boolean edit() {
        try {
            String pacientName = name.getText();
            String pacientCPF = cpf.getValue().toString();
            Date date = dateOfBirth.getDate();
            String home = homeNumber.getText();
            String respons = responsible.getText();

            String phone = (String) phoneNumber.getValue();

            PacientDTO pacientDTO = new PacientDTO();
            pacientDTO.setId(getType().getId());
            pacientDTO.setName(pacientName);
            pacientDTO.setMedicalRecord(
                    getType().getMedicalRecord()
            );

            ContactDTO contactDTO = getType().getContact()
                    .setValue(phone);

            pacientDTO.setContact(contactDTO);
            pacientDTO.setCpf(pacientCPF);
            pacientDTO.setDateOfBirth(date);
            pacientDTO.setHomeNumber(home);
            pacientDTO.setResponsible(respons);

            AddressDTO addressDTO = getType().getAddress();
            addressDTO.setStreet(street.getText());

            CityDTO cityDTO = CityController.INSTANCE.getByName((String) city.getSelectedItem(), (String) state.getSelectedItem());

            addressDTO.setCity(cityDTO);
            pacientDTO.setAddress(addressDTO);

            PacientController.INSTANCE.update(pacientDTO);
            return true;
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro %s:".formatted(e.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    @Override
    public void addComponents() {
        name = new JTextField(48);
        cpf = ViewUtils.makeFormatted("###.###.###-##");
        phoneNumber = ViewUtils.makeFormatted("(##)#####-####");

        dateOfBirth = new JDateChooser();
        homeNumber = new JTextField(48);
        responsible = new JTextField(48);

        street = new JTextField(48);

        state = new JComboBox<>(
                StateController.INSTANCE.getAll().stream().map(StateDTO::getName).toArray(String[]::new)
        );

        city = new JComboBox<>(
                CityController.INSTANCE.getAll().stream().filter(cityDTO -> cityDTO.getState().getName().equals(state.getItemAt(state.getSelectedIndex()))).map(CityDTO::getName).toArray(String[]::new)
        );


        addState = new JButton("Adicionar");
        addCity = new JButton("Adicionar");


        setLayout(new GridLayout(9, 1));

        add(ViewUtils.makeFieldAndLabel("Nome", name));
        add(ViewUtils.makeFieldAndLabel("CPF", cpf));
        add(ViewUtils.makeFieldAndLabel("Data de Nascimento", dateOfBirth));
        add(ViewUtils.makeFieldAndLabel("Numero da Casa", homeNumber));
        add(ViewUtils.makeFieldAndLabel("Responsável", responsible));
        add(ViewUtils.makeFieldAndLabel("Telefone", phoneNumber));

        add(ViewUtils.makeFieldAndLabel("Rua", street));
        add(ViewUtils.makeFieldWithAddButtonAndLabel("Estado", state, addState));
        add(ViewUtils.makeFieldWithAddButtonAndLabel("Cidade", city, addCity));

        if (getType() != null) {
            name.setText(getType().getName());
            cpf.setValue(getType().getCpf());
            dateOfBirth.setDate(getType().getDateOfBirth());
            homeNumber.setText(getType().getHomeNumber());
            responsible.setText(getType().getResponsible());
            phoneNumber.setValue(getType().getContact().getValue());

            street.setText(getType().getAddress().getStreet());

            try {
                CityDTO cityDTO = CityController.INSTANCE.getById(getType().getAddress().getCity().getId());

                state.setSelectedItem(cityDTO.getState().getName());
                city.setSelectedItem(cityDTO.getName());

            } catch (InvalidValueException e) {
                JOptionPane.showMessageDialog(null, "Erro %s:".formatted(e.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    @Override
    public void attachActions() {

        ActionListener updateCities = (e) -> {
            city.setModel(new DefaultComboBoxModel<>(
                    CityController.INSTANCE.getAll().stream().filter(cityDTO -> cityDTO.getState().getName().equals(state.getItemAt(state.getSelectedIndex()))).map(CityDTO::getName).toArray(String[]::new)
            ));
        };

        ActionListener updateStates = (e) -> {
            state.setModel(new DefaultComboBoxModel<>(
                    StateController.INSTANCE.getAll().stream().map(StateDTO::getName).toArray(String[]::new)
            ));
        };


        state.addActionListener(updateCities);


        addCity.addActionListener((e) -> {
            new GenericRegisterView("Registrar Cidade", new CityRegisterPanel());

            updateCities.actionPerformed(e);
        });



        addState.addActionListener((e) -> {
            boolean registered = false;

            while (!registered) {
                String stateName = JOptionPane.showInputDialog(null, "Nome do Estado", "Registrar Estado", JOptionPane.INFORMATION_MESSAGE);

                if (stateName == null) {
                    break;
                }

                try {
                    StateController.INSTANCE.getByName(stateName);

                    JOptionPane.showMessageDialog(null, "Um estado com esse nome já existe", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (InvalidValueException ive) {
                    if (ive.getCause() instanceof NoResultException) {
                        try {
                            StateController.INSTANCE.insert(new StateDTO()
                                    .setName(stateName));
                        } catch (InvalidValueException insertionError) {
                            JOptionPane.showMessageDialog(null, "Erro: %s".formatted(insertionError.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        registered = true;
                    }
                }
            }

            updateStates.actionPerformed(e);
        });

    }
}
