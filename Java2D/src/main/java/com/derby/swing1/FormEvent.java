package com.derby.swing1;

import lombok.Getter;

import java.util.EventObject;

@Getter
public class FormEvent extends EventObject {

    private String name;
    private String occupation;

    public FormEvent(Object source,
                     String name,
                     String occupation) {
        super(source);
        this.name = name;
        this.occupation = occupation;
    }
}
