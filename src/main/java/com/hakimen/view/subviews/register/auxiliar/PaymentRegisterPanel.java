package com.hakimen.view.subviews.register.auxiliar;

import com.hakimen.controllers.PaymentMethodController;
import com.hakimen.controllers.SchedulingController;
import com.hakimen.controllers.auxiliar.PaymentTypeController;
import com.hakimen.controllers.dto.PaymentMethodDTO;
import com.hakimen.controllers.dto.auxiliar.PaymentTypeDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.utils.ViewUtils;
import com.hakimen.view.components.RegisterPanel;

import javax.swing.*;
import java.awt.*;

public class PaymentRegisterPanel extends RegisterPanel<PaymentMethodDTO> {

    int id;

    JComboBox<String> paymentType;


    public PaymentRegisterPanel(Integer id) {
        this.id = id;
        addComponents();
        attachActions();
    }

    @Override
    public boolean save() {

        PaymentMethodDTO methodDTO = new PaymentMethodDTO();
        try {
            methodDTO.setType(PaymentTypeController.INSTANCE.getById(paymentType.getSelectedIndex() + 1));
            methodDTO.setScheduling(SchedulingController.INSTANCE.getById(id));

            PaymentMethodController.INSTANCE.update(methodDTO);
            return true;
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro : %s".formatted(e.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean edit() {

        //Not implemented
        return false;
    }

    @Override
    public void addComponents() {
        paymentType = new JComboBox<>(
                PaymentTypeController.INSTANCE.getAll().stream().map(PaymentTypeDTO::getName).toArray(String[]::new)
        );

        setLayout(new GridLayout(1,1));

        add(ViewUtils.makeFieldAndLabel("Tipo de Pagamento",paymentType));
    }

    @Override
    public void attachActions() {

    }
}
