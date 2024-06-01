package com.hakimen;


import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.hakimen.controllers.RoleController;
import com.hakimen.controllers.auxiliar.PaymentTypeController;
import com.hakimen.controllers.dto.RoleDTO;
import com.hakimen.controllers.dto.auxiliar.PaymentTypeDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.view.GenericListView;
import com.hakimen.view.LoginView;


public class Main {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();

        new LoginView();
    }

    public static void populateRoles(){
        try {
            RoleController.INSTANCE.insert(
                    new RoleDTO()
                            .setName("Administrador")
                            .setDescription("Administrador")
            );

            RoleController.INSTANCE.insert(
                    new RoleDTO()
                            .setName("Dentista")
                            .setDescription("Dentista")
            );

            RoleController.INSTANCE.insert(
                    new RoleDTO()
                            .setName("Gerente")
                            .setDescription("Gerente")
            );

            RoleController.INSTANCE.insert(
                    new RoleDTO()
                            .setName("Recepcionista")
                            .setDescription("Recepcionista")
            );
        } catch (InvalidValueException e) {
            throw new RuntimeException(e);
        }
    }

    public static void populatePaymentTypes(){
        try {
            PaymentTypeController.INSTANCE.insert(
                    new PaymentTypeDTO()
                            .setName("Pix")
            );
            PaymentTypeController.INSTANCE.insert(
                    new PaymentTypeDTO()
                            .setName("Credito")
            );
            PaymentTypeController.INSTANCE.insert(
                    new PaymentTypeDTO()
                            .setName("Debito")
            );
            PaymentTypeController.INSTANCE.insert(
                    new PaymentTypeDTO()
                            .setName("Dinheiro")
            );
        } catch (InvalidValueException e) {
            throw new RuntimeException(e);
        }
    }
}