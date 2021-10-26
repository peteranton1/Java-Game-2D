package com.derby.swing1.controller;

import com.derby.swing1.gui.FormEvent;
import com.derby.swing1.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBControllerTest {

    private DBController underTest;

    @BeforeEach
    void setUp() {
        underTest = new DBController();
    }

    @Test
    void shouldAddPersonValid1Person() {
        FormEvent ev = createFormEvent();
        underTest.addPerson(ev);
        List<Person> personList = underTest.getPeople();
        assertEquals(1, personList.size());
        Person actual = personList.get(0);
        assertEquals(ev.getName(), actual.getName());
    }

    private FormEvent createFormEvent() {
        String name = "name";
        String occupation = "occupation";
        int ageCat = 0;
        String empCat = "employed";
        String taxId = "taxId";
        boolean usCitizen = true;
        String gender = "male";

        return new FormEvent(
                this,
                name,
                occupation,
                ageCat,
                empCat,
                taxId,
                usCitizen,
                gender
        );
    }
}