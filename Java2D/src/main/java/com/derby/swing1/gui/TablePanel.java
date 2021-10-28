package com.derby.swing1.gui;

import com.derby.swing1.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.BorderLayout.CENTER;

public class TablePanel extends JPanel {

    private final JTable table;
    private final PersonTableModel tableModel;
    private final JPopupMenu popup;

    public TablePanel() {
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();

        JMenuItem removeItem = new JMenuItem("Delete row");
        popup.add(removeItem);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());

                table.getSelectionModel().setSelectionInterval(row,row);

                if(e.getButton() == MouseEvent.BUTTON3){
                    popup.show(table, e.getX(), e.getY());
                }
            }
        });

        removeItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            System.out.println("row: " + row);
        });

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
