package com.hakimen.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GenericRegisterView extends JDialog implements View{

    JPanel panel;
    JPanel buttonPanel;
    JButton saveButton;
    JButton cancelButton;

    public <T extends JPanel & IRegistrable> GenericRegisterView(String title, T panel) {
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

    //TODO: make a constructor that accepts a DTO to make it possible to edit a type

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
            if(panel instanceof IRegistrable registrable){
                registrable.save();
            }
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });
    }
}
