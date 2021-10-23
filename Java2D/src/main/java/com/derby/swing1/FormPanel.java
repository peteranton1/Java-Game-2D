package com.derby.swing1;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private FormListener formListener;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        System.out.println(dim);
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name:");
        occupationLabel = new JLabel("Occupation:");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);

        okBtn = new JButton("OK");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();

                FormEvent ev = new FormEvent(
                        this,
                        name,
                        occupation);
                if(formListener != null) {
                    formListener.formEventOccurred(ev);
                }
            }
        });

        Border innerBorder = BorderFactory
                .createTitledBorder("Add Person");
        Border outerBorder = BorderFactory
                .createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(
                outerBorder, innerBorder
        ));

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        Insets inset5px = new Insets(0, 0, 0, 5);
        Insets inset0px = new Insets(0, 0, 0, 0);

        ////////////// First Row //////////////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.insets = inset5px;
        gc.anchor = GridBagConstraints.LINE_END;
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        ////////////// Second Row //////////////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = inset5px;
        gc.anchor = GridBagConstraints.LINE_END;
        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.LINE_START;
        add(occupationField, gc);

        ////////////// Third Row //////////////
        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn, gc);
    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }
}
