package com.hakimen.view.components;

import com.hakimen.utils.ViewUtils;
import com.hakimen.view.IRegistrable;
import com.hakimen.view.View;

import javax.swing.*;
import java.awt.*;

public class AddMaterialPanel extends JPanel implements IRegistrable, View {

    private JTextField materialName;
    private JSpinner materialQuantity;
    private JSpinner minMaterialQuantity;
    private JTextField managerName;

    public AddMaterialPanel() {
        addComponents();
        attachActions();
    }

    @Override
    public void save() {
        //TODO implementar funcionalidade de save usando o Material Controller
    }

    @Override
    public void addComponents() {
        setLayout(new GridLayout(4, 1));

        materialName = new JTextField(32);
        materialQuantity = new JSpinner();
        minMaterialQuantity = new JSpinner();
        managerName = new JTextField(32);

        add(ViewUtils.makeFieldAndLabel("Nome", materialName));
        add(ViewUtils.makeFieldAndLabel("Quantidade", materialQuantity));
        add(ViewUtils.makeFieldAndLabel("Quantidade Minima", minMaterialQuantity));
        add(ViewUtils.makeFieldAndLabel("Nome do Gerente", managerName));
    }

    @Override
    public void attachActions() {

    }
}
