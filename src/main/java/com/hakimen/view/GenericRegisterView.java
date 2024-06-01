package com.hakimen.view;

import com.hakimen.view.components.RegisterPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GenericRegisterView extends JDialog implements View{

    RegisterPanel<?> panel;
    JPanel buttonPanel;
    JButton saveButton;
    JButton cancelButton;

    public GenericRegisterView(String title, RegisterPanel<?> panel) {
        this.panel = panel;

        setTitle(title);
        setModal(true);
        setSize(400,600);

        addComponents();
        attachActions();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addComponents(){
        buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        saveButton = new JButton("Salvar");
        cancelButton = new JButton("Cancelar");

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));

        add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }

    public void attachActions(){
        saveButton.addActionListener(e -> {
            if(panel.getType() == null){
                if(!panel.save()){
                    return;
                }
                JOptionPane.showMessageDialog(null, "Registro bem sucedido!");
            }else{
                if(!panel.edit()){
                    return;
                }
                JOptionPane.showMessageDialog(null, "Atualizaçao bem sucedida!");
            }
            dispose();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });
    }
}
