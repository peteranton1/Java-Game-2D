package com.derby.swing1.gui;

import com.derby.swing1.controller.DBController;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.OK_OPTION;

public class MainFrame extends JFrame {

    private final TextPanel textPanel;
    private final Toolbar toolbar;
    private final FormPanel formPanel;
    private final JFileChooser fileChooser;
    private final DBController controller;
    private final TablePanel tablePanel;
    private final PrefsDialog prefsDialog;
    private final Preferences prefs;
    private final JSplitPane splitPane;
    private JTabbedPane tabPane;

    public MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        fileChooser = new JFileChooser();
        tablePanel = new TablePanel();
        prefsDialog = new PrefsDialog(this);
        tabPane = new JTabbedPane();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            formPanel, tabPane);
        splitPane.setOneTouchExpandable(true);

        tabPane.addTab("Person Database", tablePanel);
        tabPane.addTab("Messages", textPanel);

        prefs = Preferences.userRoot().node("db");

        controller = new DBController();
        tablePanel.setData(controller.getPeople());

        tablePanel.setPersonTableListener(
            row -> {
                controller.removePerson(row);
                System.out.println(
                    "deleted:" + row);
            }
        );

        prefsDialog.setPrefsListener((user, password, portNo) -> {
            prefs.put("user", user);
            prefs.put("password", password);
            prefs.putInt("port", portNo);
            System.out.println("user: " + user +
                ", pass: " +
                password + ". Port: " + portNo);
        });

        String user = prefs.get("user", "");
        String password = prefs.get("password", "");
        int portNo = prefs.getInt("port", 3306);
        prefsDialog.setDefaults(user, password, portNo);

        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        setJMenuBar(createMenuBar());

        toolbar.setToolbarListener(new ToolbarListener() {
            @Override
            public void saveEventOccurred() {
                try {
                    controller.connect();
                    controller.save();
                    controller.disconnect();
                } catch (Exception e) {
                    getShowMessageDialog(e,
                        "Cannot connect/save database:\n");
                }
            }

            @Override
            public void refreshEventOccurred() {
                try {
                    controller.connect();
                    controller.load();
                    controller.disconnect();
                    tablePanel.refresh();
                } catch (Exception e) {
                    getShowMessageDialog(e,
                        "Cannot connect/load database:\n");
                }
            }

            private void getShowMessageDialog(Exception e, String s) {
                JOptionPane.showMessageDialog(
                    MainFrame.this,
                    s +
                        e.getMessage(),
                    "Database connect problem",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        formPanel.setFormListener(e -> {
            textPanel.appendText(e + "\n");
            controller.addPerson(e);
            tablePanel.refresh();
        });

        addWindowListener(new WindowAdapter() {
            @SneakyThrows
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Window Closing");
                controller.disconnect();
                dispose();
                System.gc();
            }
        });

        add(toolbar, BorderLayout.NORTH);
        //add(textPanel, BorderLayout.CENTER);
        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
        JMenuItem prefsItem = new JMenuItem("Preferences...");

        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);
        windowMenu.add(prefsItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        prefsItem.addActionListener(e ->
            prefsDialog.setVisible(true)
        );

        showFormItem.addActionListener(e -> {
            JCheckBoxMenuItem menuItem =
                (JCheckBoxMenuItem) e.getSource();
            if (menuItem.isSelected()) {
                splitPane.setDividerLocation((int) formPanel
                    .getMinimumSize().getWidth());
            }
            formPanel.setVisible(menuItem.isSelected());
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(
            KeyStroke.getKeyStroke(
                KeyEvent.VK_X,
                InputEvent.CTRL_DOWN_MASK));
        importDataItem.setAccelerator(
            KeyStroke.getKeyStroke(
                KeyEvent.VK_I,
                InputEvent.CTRL_DOWN_MASK));
        prefsItem.setAccelerator(
            KeyStroke.getKeyStroke(
                KeyEvent.VK_P,
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
        exitItem.addActionListener(e ->
            confirmExit()
        );
        return menuBar;
    }

    private void confirmExit() {
        String title = "Confirm Exit";
        int response = JOptionPane.showConfirmDialog(
            MainFrame.this,
            "Do you really want to exit?",
            title, OK_CANCEL_OPTION
        );
        if (response == OK_OPTION) {
            WindowListener[] listeners =
                getWindowListeners();
            for (WindowListener listener : listeners) {
                listener.windowClosing(
                    new WindowEvent(
                        MainFrame.this,
                        0)
                );
            }
        }
    }
}
