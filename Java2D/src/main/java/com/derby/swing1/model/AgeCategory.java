package com.derby.swing1.model;

import com.sudoku.SolverException;

import static com.derby.swing1.model.GuiConstants.*;

public enum AgeCategory {
    child,
    adult,
    senior;

    public static AgeCategory ofInt(int value) {
        if(0 == value) {
            return child;
        } else if(1 == value) {
            return adult;
        } else if(2 == value) {
            return senior;
        } else {
            throw new SolverException(
                    "Unknown Age: " + value);
        }
    }

    public static AgeCategory ofString(String value) {
        if(UNDER_18.equals(value)) {
            return child;
        } else if(TO_65.equals(value)) {
            return child;
        } else if(OR_OVER.equals(value)) {
            return child;
        } else {
            throw new SolverException(
                    "Unknown Age: " + value);
        }
    }
}
