package com.hakimen.view.subviews.register.auxiliar;

import com.hakimen.controllers.auxiliar.CityController;
import com.hakimen.controllers.auxiliar.StateController;
import com.hakimen.controllers.dto.auxiliar.CityDTO;
import com.hakimen.controllers.dto.auxiliar.StateDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.utils.ViewUtils;
import com.hakimen.view.components.RegisterPanel;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.awt.*;

public class CityRegisterPanel extends RegisterPanel<CityDTO> {

    JTextField cityNameField;
    JComboBox<String> stateComboBox;

    public CityRegisterPanel() {
        addComponents();
        attachActions();
    }

    public CityRegisterPanel(CityDTO type) {
        super(type);
        addComponents();
        attachActions();
    }

    @Override
    public boolean save() {
        String cityName = cityNameField.getText();
        String stateName = stateComboBox.getSelectedItem().toString();

        try {

            try {
                CityController.INSTANCE.getByName(cityName, stateName);

                JOptionPane.showMessageDialog(null, "Cidade já existe", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (InvalidValueException e) {
                if (e.getCause() instanceof NoResultException) {
                    CityController.INSTANCE.insert(new CityDTO()
                            .setName(cityName)
                            .setState(StateController.INSTANCE.getByName(stateName))
                    );
                    return true;
                }
            }
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro: %s".formatted(e.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    @Override
    public boolean edit() {

        String cityName = cityNameField.getText();
        String stateName = stateComboBox.getSelectedItem().toString();

        try {

            try {
                CityController.INSTANCE.getByName(cityName, stateName);

                JOptionPane.showMessageDialog(null, "Cidade já existe", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (InvalidValueException e) {
                if (e.getCause() instanceof NoResultException) {
                    CityController.INSTANCE.update(new CityDTO()
                            .setId(getType().getId())
                            .setName(cityName)
                            .setState(StateController.INSTANCE.getByName(stateName))
                    );
                    return true;
                }
            }
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro: %s".formatted(e.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public void addComponents() {
        cityNameField = new JTextField(32);
        stateComboBox = new JComboBox<>(
                StateController.INSTANCE.getAll().stream().map(StateDTO::getName).toArray(String[]::new)
        );

        setLayout(new GridLayout(2, 1));

        add(ViewUtils.makeFieldAndLabel("Nome", cityNameField));
        add(ViewUtils.makeFieldAndLabel("Estado", stateComboBox));

        if(getType() != null){
            cityNameField.setText(getType().getName());
            stateComboBox.setSelectedItem(getType().getState());
        }
    }

    @Override
    public void attachActions() {

    }
}
