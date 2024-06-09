package com.hakimen.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;

public interface IDisplayable {

    default boolean[] enabledButtons() {
        return new boolean[]{
                true,   // Cadastrar
                true,   // Editar
                true,   // Remover
        };
    }

    default JMenuBar addMenuItems(GenericListView genericListView, JMenuBar bar){
        return bar;
    }

    void setupTableColumns(DefaultTableModel tableModel, JTable table);

    void get(DefaultTableModel tableModel, JTable table, boolean isAscending, String filterKey, String searchString);

    void edit(DefaultTableModel tableModel, JTable table, int row);

    void remove(DefaultTableModel tableModel, JTable table, int row);


    void register();

    Map<String, String> getFilter();
}
