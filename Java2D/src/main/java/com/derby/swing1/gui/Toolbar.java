package com.derby.swing1.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Toolbar extends JToolBar implements ActionListener {

    private final JButton saveButton;
    private final JButton refreshButton;
    private ToolbarListener toolbarListener;

    public Toolbar() {

        //setBorder(BorderFactory.createEtchedBorder());

        saveButton = new JButton();
        refreshButton = new JButton();

        saveButton.addActionListener(this);
        saveButton.setIcon(createIcon(
            "/images/Save16.gif"));
        saveButton.setToolTipText("Save");
        refreshButton.addActionListener(this);
        refreshButton.setIcon(createIcon(
            "/images/Refresh16.gif"));
        refreshButton.setToolTipText("Refresh");
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveButton);
        add(refreshButton);
    }

    private ImageIcon createIcon(String path) {
        URL url = getClass().getResource(path);
        if(url == null){
            System.err.println("Unable to locate: "+path);
            return null;
        }
        return new ImageIcon(url);
    }

    public void setToolbarListener(ToolbarListener toolbarListener) {
        this.toolbarListener = toolbarListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == saveButton) {
            if(toolbarListener != null) {
                toolbarListener.saveEventOccurred();
            }
        } else if (clicked == refreshButton) {
            if(toolbarListener != null) {
                toolbarListener.refreshEventOccurred();
            }
        } else {
            System.out.println("Unknown button clicked");
        }
    }
}
