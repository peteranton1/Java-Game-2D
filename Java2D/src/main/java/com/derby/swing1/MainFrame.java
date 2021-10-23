package com.derby.swing1;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private Toolbar toolbar;

    public MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        toolbar.setStringListener(text ->
                textPanel.appendText(text));

        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
    }
}
