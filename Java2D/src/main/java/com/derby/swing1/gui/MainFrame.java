package com.derby.swing1.gui;

import com.derby.swing1.controller.DBController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.OK_OPTION;

public class MainFrame extends JFrame {

    private final TextPanel textPanel;
    private final Toolbar toolbar;
    private final FormPanel formPanel;
    private final JFileChooser fileChooser;
    private final DBController controller;
    private final TablePanel tablePanel;

    public MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        fileChooser = new JFileChooser();
        tablePanel = new TablePanel();

        controller = new DBController();
        tablePanel.setData(controller.getPeople());

        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        setJMenuBar(createMenuBar());

        toolbar.setStringListener(
                textPanel::appendText);

        formPanel.setFormListener(e -> {
            textPanel.appendText(e + "\n");
            controller.addPerson(e);
            tablePanel.refresh();
        });

        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        //add(textPanel, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 400));
        setSize(700, 500);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();


        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem(
                "Export Data ...");
        JMenuItem importDataItem = new JMenuItem(
                "Import Data ...");
        JMenuItem exitItem = new JMenuItem(
                "Exit");
        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");
        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);
        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        showFormItem.addActionListener(e -> {
            JCheckBoxMenuItem menuItem =
                    (JCheckBoxMenuItem) e.getSource();
            formPanel.setVisible(menuItem.isSelected());
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_X,
                        InputEvent.CTRL_DOWN_MASK));


        // IMPORT
        importDataItem.addActionListener(e -> {
            if (fileChooser.showOpenDialog(
                    MainFrame.this)
                    == JFileChooser.APPROVE_OPTION) {
                File file = null;
                try {
                    file = fileChooser.getSelectedFile();
                    controller.loadFromFile(file);
                    System.out.println("Loaded File: " + file);
                    tablePanel.refresh();
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Could not load data from file: \n" +
                                    file + "\n" + e1.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("File choosing cancelled");
            }
        });

        // EXPORT
        exportDataItem.addActionListener(e -> {
            if (fileChooser.showSaveDialog(
                    MainFrame.this)
                    == JFileChooser.APPROVE_OPTION) {
                File file = null;
                try {
                    file = fileChooser.getSelectedFile();
                    controller.saveToFile(file);
                    System.out.println("Saved File: " + file);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Could not save data to file: \n" +
                                    file + "\n" + e1.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("File choosing cancelled");
            }
        });

        // EXIT
        exitItem.addActionListener(e -> {
            String title = "Confirm Exit";
            int response = JOptionPane.showConfirmDialog(
                    MainFrame.this,
                    "Do you really want to exit?",
                    title, OK_CANCEL_OPTION
            );
            if (response == OK_OPTION) {
                System.exit(0);
            }
        });
        return menuBar;
    }
}
