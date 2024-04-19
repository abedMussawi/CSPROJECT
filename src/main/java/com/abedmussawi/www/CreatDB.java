package com.abedmussawi.www;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class CreatDB {
    public CreatDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Project","postgres","7729911");

        Statement stmt = conn.createStatement();

        stmt.executeUpdate("""
                CREATE TABLE Product(prodid CHAR(4),
                					 pname VARCHAR(20),
                					 price NUMERIC(4,1));
                                                      
                                      """);

        stmt.executeUpdate("""
                            CREATE TABLE Depot(depid CHAR(4),
                            addr VARCHAR(50),
                            volume INTEGER);
                                            """);

        stmt.executeUpdate("""
                            CREATE TABLE Stock(prodid CHAR(4),
                            				   depid CHAR(4),
                            				   quantity INTEGER);
                            
                                            """);
    }
}
