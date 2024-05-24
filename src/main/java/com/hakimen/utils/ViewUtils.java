package com.hakimen.utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class ViewUtils {
    public static JPanel makeFieldAndLabel(String fieldName, JComponent component) {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(0, 10, 0, 10));
        panel.setLayout(new GridLayout(2, 1));

        JLabel label = new JLabel(fieldName);
        label.setBorder(new EmptyBorder(10, 0, 10, 0));

        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(component);

        return panel;
    }

    public static JFormattedTextField makeFormatted(String format){
        try {
            return new JFormattedTextField(
                    new MaskFormatter(format)
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
