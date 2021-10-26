package com.derby.swing1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static javax.swing.JOptionPane.*;

public class MainFrame extends JFrame {

    private final TextPanel textPanel;
    private final Toolbar toolbar;
    private final FormPanel formPanel;
    private final JFileChooser fileChooser;

    public MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        fileChooser = new JFileChooser();

        setJMenuBar(createMenuBar());

        toolbar.setStringListener(
                textPanel::appendText);

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
            if(fileChooser.showOpenDialog(
                    MainFrame.this)
                    == JFileChooser.APPROVE_OPTION){
                System.out.println("File chosen: " +
                        fileChooser.getSelectedFile());
            } else {
                System.out.println("File choosing cancelled");
            }
        });

        // EXPORT
        exportDataItem.addActionListener(e -> {
            if(fileChooser.showSaveDialog(
                    MainFrame.this)
                    == JFileChooser.APPROVE_OPTION){
                System.out.println("File chosen: " +
                        fileChooser.getSelectedFile());
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
