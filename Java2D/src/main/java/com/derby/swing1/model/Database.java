package com.derby.swing1.model;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private List<Person> people;

    public Database() {
        people = new LinkedList<>();
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public Person getPerson(int index) {
        return people.get(index);
    }

    public void removePerson(int index) {
        people.remove(index);
    }

    public List<Person> getPeople() {
        return Collections.unmodifiableList(people);
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
