package com.hakimen;


import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.hakimen.controllers.RoleController;
import com.hakimen.controllers.auxiliar.PaymentTypeController;
import com.hakimen.controllers.dto.RoleDTO;
import com.hakimen.controllers.dto.auxiliar.PaymentTypeDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.view.LoginView;

import javax.persistence.NoResultException;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();

        populateRoles();
        populatePaymentTypes();

        new LoginView();
    }

    public static void populateRoles(){
        List<String> roles = List.of("Administrador", "Dentista", "Gerente", "Recepcionista");
        for (int i = 1; i < 5; i++) {
            try {
                RoleController.INSTANCE.getById(i);
            } catch (NoResultException | InvalidValueException e) {
                try {
                    RoleController.INSTANCE.insert(
                            new RoleDTO()
                                    .setName(roles.get(i-1))
                                    .setDescription(roles.get(i-1))
                    );
                } catch (InvalidValueException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }

    }

    public static void populatePaymentTypes(){
        List<String> paymentTypes = List.of("Pix", "Crédito", "Debito", "Dinheiro");
        for (int i = 1; i < 5; i++) {
            try {
                PaymentTypeController.INSTANCE.getById(i);
            } catch (NoResultException | InvalidValueException e) {
                try {
                    PaymentTypeController.INSTANCE.insert(
                            new PaymentTypeDTO()
                                    .setName(paymentTypes.get(i-1))
                    );
                } catch (InvalidValueException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}