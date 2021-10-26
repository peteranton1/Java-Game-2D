package com.derby.swing1.model;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Person> people;

    public Database() {
        people = new ArrayList<>();
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public List<Person> getPeople() {
        return people;
    }
}
