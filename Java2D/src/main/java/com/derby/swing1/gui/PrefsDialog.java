package com.derby.swing1.gui;

import javax.swing.*;
import java.awt.*;

public class PrefsDialog extends JDialog {

    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;

    public PrefsDialog(JFrame parent) {
        super(parent,
                "Preferences",
                false);

        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");

        spinnerModel = new SpinnerNumberModel(
                3306,
                0,
                9999,
                1
        );
        portSpinner = new JSpinner(spinnerModel);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;

        add(new JLabel("Port: "), gc);

        gc.gridx++;
        add(portSpinner, gc);

        /////// Next Row ///////

        gc.gridy++;

        gc.gridx = 0;
        add(okButton, gc);

        gc.gridx++;
        add(cancelButton, gc);

        okButton.addActionListener(e -> {
            Integer value = (Integer)portSpinner.getValue();
            System.out.println("Port: "+value);
            setVisible(false);
        });

        cancelButton.addActionListener(e ->
            setVisible(false)
        );

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }
}
