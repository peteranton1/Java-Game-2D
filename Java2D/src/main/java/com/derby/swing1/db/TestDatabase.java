package com.derby.swing1.db;

import com.derby.swing1.model.Database;

public class TestDatabase {
    public static void main(String[] args) throws Exception {
        System.out.println("Running Database Test");
        Database db = new Database();
        db.connect();
        db.disconnect();
    }
}
