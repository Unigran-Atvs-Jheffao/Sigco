package com.hakimen;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.hakimen.view.GenericRegisterView;
import com.hakimen.view.components.AddMaterialPanel;
import com.hakimen.view.components.AddPacientPanel;
import com.hakimen.view.components.CreateAppointmentPanel;
import com.hakimen.view.components.LoginRegisterPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();

        new GenericRegisterView("omg", new AddMaterialPanel());
    }
}