package com.hakimen.view;

import com.hakimen.utils.AppContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class GenericListView extends JFrame implements View {

    JScrollPane panel;
    JTable table;
    DefaultTableModel model;

    IDisplayable displayable;

    JButton register;
    JButton edit;
    JButton delete;

    JComboBox<String> orderingMode;
    JComboBox<String> orderedBy;
    JTextField search;
    JButton searchButton;

    public GenericListView(String title, IDisplayable displayable) {
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.displayable = displayable;

        setSize(800, 600);
        setLocationRelativeTo(null);

        addComponents();
        attachActions();

        setJMenuBar(displayable.addMenuItems(this, AppContext.getBar(this)));

        setVisible(true);
    }

    @Override
    public void addComponents() {
        model = new DefaultTableModel();

        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setFocusable(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        panel = new JScrollPane(table);
        add(panel);

        edit = new JButton("Editar");
        delete = new JButton("Remover");
        register = new JButton("Adicionar");

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttons.setBorder(new EmptyBorder(5, 5, 5, 5));

        register.setEnabled(displayable.enabledButtons()[0]);
        buttons.add(register);


        edit.setEnabled(displayable.enabledButtons()[1]);
        buttons.add(edit);


        delete.setEnabled(displayable.enabledButtons()[2]);
        buttons.add(delete);

        add(buttons, BorderLayout.SOUTH);

        orderingMode = new JComboBox<>();
        orderingMode.addItem("Ascending");
        orderingMode.addItem("Descending");
        orderingMode.setPreferredSize(orderingMode.getMinimumSize());
        orderingMode.setMaximumSize(orderingMode.getMinimumSize());

        orderedBy = new JComboBox<>(
                displayable.getFilter().keySet().toArray(String[]::new)
        );

        orderedBy.setPreferredSize(orderedBy.getMinimumSize());
        orderedBy.setMaximumSize(orderedBy.getMinimumSize());

        displayable.setupTableColumns(model, table);
        search = new JTextField(24);
        search.setMaximumSize(search.getPreferredSize());
        searchButton = new JButton("Buscar");

        JPanel filters = new JPanel();
        filters.setLayout(new BoxLayout(filters, BoxLayout.X_AXIS));
        filters.setBorder(new EmptyBorder(5, 5, 5, 5));

        filters.add(search);
        filters.add(searchButton);
        filters.add(Box.createGlue());
        filters.add(orderedBy);
        filters.add(orderingMode);

        add(filters, BorderLayout.NORTH);

        displayable.get(model, table, orderingMode.getSelectedItem().toString().equals("Ascending"), orderedBy.getSelectedItem().toString(), search.getText().toLowerCase());
    }

    @Override
    public void attachActions() {

        ActionListener onFilterChange = e -> {
            displayable.get(model, table, orderingMode.getSelectedItem().toString().equals("Ascending"), orderedBy.getSelectedItem().toString(), search.getText().toLowerCase());
        };

        edit.addActionListener(e -> {
            if (table.getSelectedRow() != -1) {
                displayable.edit(model, table, table.getSelectedRow());
                onFilterChange.actionPerformed(e);
            }
        });

        delete.addActionListener(e -> {
            if (table.getSelectedRow() != -1) {
                displayable.remove(model, table, table.getSelectedRow());
                onFilterChange.actionPerformed(e);
            }
        });

        register.addActionListener(e -> {
            displayable.register();
            onFilterChange.actionPerformed(e);
        });

        orderedBy.addActionListener(onFilterChange);
        orderingMode.addActionListener(onFilterChange);
        searchButton.addActionListener(onFilterChange);

        search.addActionListener(onFilterChange);
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }
}
