package com.derby.swing1.controller;

import com.derby.swing1.gui.FormEvent;
import com.derby.swing1.model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DBController {

    private Database db = new Database();

    public List<Person> getPeople() {
        return db.getPeople();
    }

    public void addPerson(FormEvent ev) {
        // Event fields
        String ev_name = ev.getName();
        String ev_occupation = ev.getOccupation();
        int ev_ageCategory = ev.getAgeCategory();
        String ev_empCat = ev.getEmpCat();
        String ev_taxId = ev.getTaxId();
        boolean ev_isUS = ev.isUsCitizen();
        String ev_gender = ev.getGenderCommand();

        // DB Fields
        AgeCategory db_ageCategory =
                AgeCategory.ofInt(ev_ageCategory);
        EmploymentCategory db_empCat =
                EmploymentCategory.of(ev_empCat);
        Gender db_gender = Gender.valueOf(ev_gender);

        // Create person
        Person person = new Person(
                ev_name,
                ev_occupation,
                db_ageCategory,
                db_empCat,
                ev_taxId,
                ev_isUS,
                db_gender
        );

        db.addPerson(person);
    }

    public void saveToFile(File file)
            throws IOException {
        db.saveToFile(file);
    }

    public void loadFromFile(File file)
            throws IOException {
        db.loadFromFile(file);
    }
}
