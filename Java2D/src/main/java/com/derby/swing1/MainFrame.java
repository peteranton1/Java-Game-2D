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

        toolbar.setStringListener(text ->
                textPanel.appendText(text));

        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
    }
}
