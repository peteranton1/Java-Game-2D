package com.derby.swing1.gui;

import lombok.Getter;
import lombok.ToString;

import java.util.EventObject;

@ToString
@Getter
public class FormEvent extends EventObject {

    private String name;
    private String occupation;
    private int ageCategory;
    private String empCat;
    private String taxId;
    private boolean usCitizen;
    private String genderCommand;


    public FormEvent(Object source,
                     String name,
                     String occupation,
                     int ageCategory,
                     String empCat,
                     String taxId,
                     boolean usCitizen,
                     String genderCommand) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.empCat = empCat;
        this.taxId = taxId;
        this.usCitizen = usCitizen;
        this.genderCommand = genderCommand;
    }
}
