package com.derby.swing1.model;

import java.io.*;
import java.sql.*;
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
        if (conn != null) return;

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
        if (conn == null) return;
        try {
            conn.close();
        } catch (SQLException e) {
            throw new Exception("Connection close failed: " +
                    e.getMessage(), e);
        }
        System.out.println("Database disconnected.");
        conn = null;
    }

    public void save() throws SQLException {

        String checkSql = "select count(*) as count " +
                "from people where id=?";
        PreparedStatement checkStmt = conn
                .prepareStatement(checkSql);

        String insertSql = "INSERT INTO people (" +
                "id, name, occupation, " +
                "age, employment_status, " +
                "tax_id, us_citizen, gender) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStmt = conn
                .prepareStatement(insertSql);

        String updateSql = "UPDATE people SET " +
                "name=?, occupation=?, " +
                "age=?, employment_status=?, " +
                "tax_id=?, us_citizen=?, gender=?" +
                " WHERE id = ?";
        PreparedStatement updateStmt = conn
                .prepareStatement(updateSql);

        int countActual = 0;
        for (Person person : people) {

            int id = person.getId();
            String name = person.getName();
            String occupation = person.getOccupation();
            String age = person.getAgeCategory().name();
            String empCat = person.getEmpCat().name();
            String taxId = person.getTaxId();
            boolean usCitizen = person.isUsCitizen();
            String gender = person.getGender().name();

            int parameterIndex = 1;
            checkStmt.setInt(parameterIndex, id);

            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            int columnIndex = 1;
            int count = checkResult.getInt(columnIndex);
            int col=0;
            if(count == 0){
                // INSERT
                System.out.println("Inserting person " +
                        "with id = " + person.getId() );
                insertStmt.setInt(++col, id);
                insertStmt.setString(++col, name);
                insertStmt.setString(++col, occupation);
                insertStmt.setString(++col, age);
                insertStmt.setString(++col, empCat);
                insertStmt.setString(++col, taxId);
                insertStmt.setBoolean(++col, usCitizen);
                insertStmt.setString(++col, gender);
                countActual += insertStmt.executeUpdate();
            } else {
                // UPDATE
                System.out.println("Updating person " +
                        "with id = " + person.getId() );
                updateStmt.setString(++col, name);
                updateStmt.setString(++col, occupation);
                updateStmt.setString(++col, age);
                updateStmt.setString(++col, empCat);
                updateStmt.setString(++col, taxId);
                updateStmt.setBoolean(++col, usCitizen);
                updateStmt.setString(++col, gender);
                updateStmt.setInt(++col, id);
                countActual += updateStmt.executeUpdate();
            }
        }

        System.out.println("Saved " + countActual);
        updateStmt.close();
        insertStmt.close();
        checkStmt.close();
    }

    public void load() throws SQLException {
        people.clear();

        Statement selectStatement =
            conn.createStatement();

        final String selectSql = "" +
            "select id, name, age, " +
            "employment_status," +
            "tax_id, us_citizen, " +
            "gender, occupation " +
            "from people " +
            "order by name";

        ResultSet results = selectStatement.executeQuery(selectSql);

        while(results.next()){
            int id = results.getInt("id");
            String name = results.getString("name");
            String ageCat = results.getString("age");
            AgeCategory ageCategory = AgeCategory.valueOf(ageCat);
            String empCategory = results.getString("employment_status");
            EmploymentCategory empCat = EmploymentCategory.valueOf(empCategory);
            String taxId = results.getString("tax_id");
            boolean usCitizen = results.getBoolean("us_citizen");
            String genderStr = results.getString("gender");
            Gender gender = Gender.valueOf(genderStr);
            String occupation = results.getString("occupation");

            Person person = Person.builder()
                .id(id)
                .name(name)
                .occupation(occupation)
                .ageCategory(ageCategory)
                .empCat(empCat)
                .taxId(taxId)
                .usCitizen(usCitizen)
                .gender(gender)
                .build();
            System.out.println("Person : " + person);
            people.add(person);

        }
        System.out.println("Loaded people : " + people.size());

        results.close();
        selectStatement.close();
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
