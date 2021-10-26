package com.derby.swing1.model;

import static com.derby.swing1.model.GuiConstants.*;

public enum EmploymentCategory {
    employed,
    selfEmployed,
    unemployed,
    other;

    public static EmploymentCategory of(String value) {
        if(EMPLOYED.equals(value)){
            return employed;
        } else if(SELF_EMPLOYED.equals(value)){
            return selfEmployed;
        } else if(UNEMPLOYED.equals(value)){
            return unemployed;
        } else {
            return other;
        }
    }
}
