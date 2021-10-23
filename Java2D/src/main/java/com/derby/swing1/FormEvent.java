package com.derby.swing1;

import lombok.Getter;

import java.util.EventObject;

@Getter
public class FormEvent extends EventObject {

    private String name;
    private String occupation;
    private AgeCategory ageCategory;
    private String empCat;
    private String taxId;
    private boolean usCitizen;


    public FormEvent(Object source,
                     String name,
                     String occupation,
                     AgeCategory ageCategory,
                     String empCat,
                     String taxId,
                     boolean usCitizen) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.empCat = empCat;
        this.taxId = taxId;
        this.usCitizen = usCitizen;
    }
}
