package com.derby.swing1.gui;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {

    private JButton cancelButton;
    private JProgressBar progressBar;


    public ProgressDialog(Window parent) {
        super(parent, "Messages Downloading...",
            Dialog.ModalityType.APPLICATION_MODAL);

        cancelButton = new JButton("Cancel");
        progressBar = new JProgressBar();

        setLayout(new FlowLayout());
        add(progressBar);
        add(cancelButton);

        Dimension size = cancelButton.getPreferredSize();
        size.width = 400;
        progressBar.setPreferredSize(size);
        pack();
        setLocationRelativeTo(parent);
    }

    public void setMaximum(int value){
        progressBar.setMaximum(value);
    }

    public void setValue(int value){
        progressBar.setValue(value);
    }

    @Override
    public void setVisible(final boolean visible) {
        progressBar.setValue(0);
        
        SwingUtilities.invokeLater(() -> {
            ProgressDialog.super.setVisible(visible);
        });
    }
}
