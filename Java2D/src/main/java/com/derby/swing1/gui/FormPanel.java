package com.derby.swing1.gui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static com.derby.swing1.model.GuiConstants.*;

public class FormPanel extends JPanel {

    private final JLabel nameLabel;
    private final JLabel occupationLabel;
    private final JTextField nameField;
    private final JTextField occupationField;
    private final JButton okBtn;
    private final JButton clearBtn;
    private FormListener formListener;
    private final JList<AgeCategory> ageList;
    private final JComboBox<String> empCombo;
    private final JCheckBox citizenCheck;
    private final JTextField taxField;
    private final JLabel taxLabel;
    private final JRadioButton maleRadio;
    private final JRadioButton femaleRadio;
    private final ButtonGroup genderGroup;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 350;
        setPreferredSize(dim);
        setMinimumSize(dim);

        nameLabel = new JLabel("Name:");
        occupationLabel = new JLabel("Occupation:");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        ageList = new JList<>();
        empCombo = new JComboBox<>();
        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID:");
        okBtn = new JButton("OK");
        clearBtn = new JButton("Clear");

        // Setup OK,Clear Buttons
        okBtn.setMnemonic(KeyEvent.VK_O);
        clearBtn.setMnemonic(KeyEvent.VK_C);

        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        // Set up gender
        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");
        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");
        genderGroup = new ButtonGroup();

        // set up gender group
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        maleRadio.setSelected(true);

        // Setup Tax ID
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        citizenCheck.addActionListener(e -> {
            boolean isTicked = citizenCheck.isSelected();
            taxLabel.setEnabled(isTicked);
            taxField.setEnabled(isTicked);
            if(!isTicked){
                taxField.setText("");
            }
        });

        // Setup list box
        setupListBox();

        // setup combo box
        DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<>();
        empModel.addElement(EMPLOYED);
        empModel.addElement(SELF_EMPLOYED);
        empModel.addElement(UNEMPLOYED);
        empCombo.setModel(empModel);


        ageList.setPreferredSize(new Dimension(110, 66));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        empCombo.setSelectedIndex(0);
        empCombo.setEditable(true);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCat = ageList.getSelectedValue();
                String empCat = (String) empCombo.getSelectedItem();
                String taxId = taxField.getText();
                boolean usCitizen = citizenCheck.isSelected();

                ButtonModel genderSelection = genderGroup
                        .getSelection();
                String genderCommand = (genderSelection != null?
                        genderSelection.getActionCommand(): "none");

                FormEvent ev = new FormEvent(
                        this,
                        name,
                        occupation,
                        ageCat.getId(),
                        empCat,
                        taxId,
                        usCitizen,
                        genderCommand
                );
                System.out.println(ev);
                if(formListener != null) {
                    formListener.formEventOccurred(ev);
                }
            }
        });

        clearBtn.addActionListener(e -> {
            nameField.setText("");
            occupationField.setText("");
            ageList.setSelectedIndex(1);
            empCombo.setSelectedItem(EMPLOYED);
            taxField.setText("");
            citizenCheck.setSelected(false);
            maleRadio.setSelected(true);
        });

        Border innerBorder = BorderFactory
                .createTitledBorder("Add Person");
        Border outerBorder = BorderFactory
                .createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(
                outerBorder, innerBorder
        ));

        layoutComponents();
    }

    private void setupListBox() {
        DefaultListModel<AgeCategory> ageModel =
                new DefaultListModel<>();
        AgeCategory element0 = AgeCategory.builder()
                .id(0)
                .text(UNDER_18)
                .build();
        AgeCategory.add(element0);
        ageModel.addElement(element0);

        AgeCategory element1 = AgeCategory.builder()
                .id(1)
                .text(TO_65)
                .build();
        AgeCategory.add(element1);
        ageModel.addElement(element1);

        AgeCategory element2 = AgeCategory.builder()
                .id(2)
                .text(OR_OVER)
                .build();
        AgeCategory.add(element2);
        ageModel.addElement(element2);

        ageList.setModel(ageModel);
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        Insets inset5px = new Insets(0, 0, 0, 5);
        Insets inset0px = new Insets(0, 0, 0, 0);

        ////////////// First Row //////////////
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.insets = inset5px;
        gc.anchor = GridBagConstraints.LINE_END;
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        ////////////// Next Row //////////////
        gc.gridy ++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = inset5px;
        gc.anchor = GridBagConstraints.LINE_END;
        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.LINE_START;
        add(occupationField, gc);

        ////////////// Next Row //////////////
        gc.gridy ++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = inset5px;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Age:"), gc);

        gc.gridx = 1;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ageList, gc);

        ////////////// Next Row //////////////
        gc.gridy ++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = inset5px;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Employment:"), gc);

        gc.gridx = 1;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(empCombo, gc);

        ////////////// Next Row //////////////
        gc.gridy ++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = inset5px;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("US Citizens:"), gc);

        gc.gridx = 1;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(citizenCheck, gc);

        ////////////// Next Row //////////////
        gc.gridy ++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = inset5px;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(taxLabel, gc);

        gc.gridx = 1;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(taxField, gc);

        ////////////// Next Row //////////////
        gc.gridy ++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = inset5px;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Gender:"), gc);

        gc.gridx = 1;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(maleRadio, gc);

        ////////////// Next Row //////////////
        gc.gridy ++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 1;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(femaleRadio, gc);

        ////////////// Last Row //////////////
        gc.gridy ++;

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 0;
        gc.insets = inset5px;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(okBtn, gc);

        gc.gridx = 1;
        gc.insets = inset0px;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(clearBtn, gc);
    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }
}

@Builder
@AllArgsConstructor
@Getter
class AgeCategory {
    private int id;
    private String text;

    private static final Map<Integer, AgeCategory> categories =
            new HashMap<>();
    private static final AgeCategory defaultValue = AgeCategory
            .builder()
            .id(0)
            .text("Category 0")
            .build();

    public static AgeCategory of(int ageCategory) {
        return categories.getOrDefault(ageCategory, defaultValue);
    }

    public static void add(AgeCategory element) {
        categories.put(element.getId(), element);
    }

    @Override
    public String toString() {
        return text ;
    }
    public String toStringAll() {
        return id + ": " + text ;
    }
}
