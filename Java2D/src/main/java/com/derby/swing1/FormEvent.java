package com.derby.swing1;

import lombok.Getter;

import java.util.EventObject;

@Getter
public class FormEvent extends EventObject {

    private String name;
    private String occupation;
    private AgeCategory ageCategory;

    public FormEvent(Object source,
                     String name,
                     String occupation,
                     AgeCategory ageCategory) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
    }
}
