package com.derby.swing1.model;

import java.io.*;
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

    public void saveToFile(File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file))) {
            Person[] persons = people.toArray(new Person[0]);
            oos.writeObject(persons);
        }
    }

    public void loadFromFile(File file) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {
            Person[] persons = (Person[]) ois.readObject();
            people.clear();
            people.addAll(List.of(persons));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
