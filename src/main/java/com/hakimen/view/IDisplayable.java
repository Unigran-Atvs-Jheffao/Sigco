package com.hakimen.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;

public interface IDisplayable {
    void setupTableColumns(DefaultTableModel tableModel);

    void get(DefaultTableModel tableModel, JTable table, boolean isAscending, String filterKey, String searchString);

    void edit(DefaultTableModel tableModel, JTable table, int row);

    void remove(DefaultTableModel tableModel, JTable table, int row);

    void register();

    Map<String, String> getFilter();
}
