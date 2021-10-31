package com.derby.swing1.db;

import com.derby.swing1.model.*;

public class TestDatabase {
    public static void main(String[] args) throws Exception {
        System.out.println("Running Database Test");
        Database db = new Database();
        db.connect();

        db.addPerson(new Person(
                "Joe", "Builder",
                AgeCategory.adult,
                EmploymentCategory.employed,
                "777", true,
                Gender.male
        ));
        db.addPerson(new Person(
                "Sue", "Artist",
                AgeCategory.senior,
                EmploymentCategory.selfEmployed,
                null, false,
                Gender.female
        ));
        db.save();

        db.load();

        db.disconnect();
    }
}
