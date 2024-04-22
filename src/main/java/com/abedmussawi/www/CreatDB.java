package com.abedmussawi.www;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatDB {
    private final Statement stmt;

    public CreatDB() throws SQLException {
        try {
            // Initialize connection and statement
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Project", "postgres", "7729911");
            stmt = conn.createStatement();
            createTables(); // Call the method to create tables
        } catch (SQLException e) {
            throw new SQLException("Error connecting to the database or executing statements", e);
        }
    }

    // this method below will not run unless the tables does not exist
    private void createTables() throws SQLException {

        stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Product(
                    prodid CHAR(4),
                    pname VARCHAR(20),
                    price NUMERIC(4,1)
                );
                """);


        stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Depot(
                    depid CHAR(4),
                    addr VARCHAR(50),
                    volume INTEGER
                );
                """);


        stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Stock(
                    prodid CHAR(4),
                    depid CHAR(4),
                    quantity INTEGER
                );
                """);
    }
}