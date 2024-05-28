package com.hakimen;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.hakimen.view.GenericListView;
import com.hakimen.view.GenericRegisterView;
import com.hakimen.view.IDisplayable;
import com.hakimen.view.components.AddMaterialPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();

        new GenericListView("Materials List", new IDisplayable() {
            @Override
            public void setupTableColumns(DefaultTableModel tableModel) {
                tableModel.addColumn("Id");
                tableModel.addColumn("Id1");
                tableModel.addColumn("Id2");
                tableModel.addColumn("Id3");
                tableModel.addColumn("Id4");
                tableModel.addColumn("Id5");
            }

            @Override
            public void register() {
                new GenericRegisterView("Cadastrar Material", new AddMaterialPanel());
            }

            @Override
            public void get(DefaultTableModel tableModel, JTable table, boolean ascending, String filterKey, String searchString) {

                tableModel.setRowCount(0);

                System.out.println(ascending);

                tableModel.addRow(new Object[]{"1", "2", "3", "4", "5", "6"});
                tableModel.addRow(new Object[]{"1", "2", "3", "4", "5", "6"});
                tableModel.addRow(new Object[]{"1", "2", "3", "4", "5", "6"});
                tableModel.addRow(new Object[]{"1", "2", "3", "4", "5", "6"});
                tableModel.addRow(new Object[]{"1", "2", "3", "4", "5", "6"});
                tableModel.addRow(new Object[]{"1", "2", "3", "4", "5", "6"});
                tableModel.addRow(new Object[]{"1", "2", "3", "4", "5", "6"});
            }

            @Override
            public void edit(DefaultTableModel tableModel, JTable table, int row) {
                System.out.println(row);
            }

            @Override
            public void remove(DefaultTableModel tableModel, JTable table, int row) {

            }

            @Override
            public Map<String, String> getFilter() {
                return Map.ofEntries(
                        Map.entry("Id", ""),
                        Map.entry("Id1", ""),
                        Map.entry("Id2", ""),
                        Map.entry("Id3", ""),
                        Map.entry("Id4", ""),
                        Map.entry("Id5", "")
                );
            }
        });
    }
}