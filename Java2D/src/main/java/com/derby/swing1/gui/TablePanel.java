package com.derby.swing1.gui;

import com.derby.swing1.model.Person;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;

public class TablePanel extends JPanel {

    private JTable table;
    private PersonTableModel tableModel;

    public TablePanel() {
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);

        setLayout(new BorderLayout());

        add(new JScrollPane(table), CENTER);
    }

    public void setData(java.util.List<Person> db){
        tableModel.setData(db);
    }

    public void refresh(){
        tableModel.fireTableDataChanged();
    }
}
