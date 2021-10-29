package com.derby.swing1.model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private static final String DRIVER =
            "org.postgresql.Driver";
    private static final String CONN_URL =
            "jdbc:postgresql://localhost/" +
                    "mydb" +
                    "?user=myuser&" +
                    "password=secret&" +
                    "ssl=false";
    private Connection conn;

    private List<Person> people;

    public Database() {
        people = new LinkedList<>();
    }

    public void connect() throws Exception {
        if(conn != null) return;

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver not found: " +
                    DRIVER);
        }
        conn = DriverManager
                .getConnection(CONN_URL);
        System.out.println("Database Connected.");
    }

    public void disconnect() throws Exception {
        if(conn == null) return;
        try {
            conn.close();
        } catch (SQLException e) {
            throw new Exception("Connection close failed: " +
                    e.getMessage(), e);
        }
        System.out.println("Database disconnected.");
        conn = null;
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
