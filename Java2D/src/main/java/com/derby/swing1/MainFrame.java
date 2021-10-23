package com.derby.swing1;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;

    public MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();

        setJMenuBar(createMenuBar());

        toolbar.setStringListener(text ->
                textPanel.appendText(text));

        formPanel.setFormListener(e -> {
            String name = e.getName();
            String occupation = e.getOccupation();
            AgeCategory ageCategory = e.getAgeCategory();
            String empCat = e.getEmpCat();
            String taxId = e.getTaxId();
            boolean usCitizen = e.isUsCitizen();
            String gender = e.getGenderCommand();

            textPanel.appendText(name + ": " +
                    occupation +
                    ", age: " + ageCategory.toStringAll() +
                    ", empCat: " + empCat + "\n" +
                    ", usCitizen: " + usCitizen +
                    ", taxId: " + taxId + "\n" +
                    ", gender: " + gender +
                    "\n");
        });

        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        JMenuItem showFormItem = new JMenuItem("Person Form");
        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        return menuBar;
    }
}
