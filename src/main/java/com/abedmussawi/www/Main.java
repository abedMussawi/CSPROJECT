package com.abedmussawi.www;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static void main(String[] args) throws SQLException {
        InsertAndUpdates db = new InsertAndUpdates();
        List<String> inserts = Arrays.asList(
//                "INSERT INTO Product VALUES ('p1', 'tape', 2.5);",
//                "INSERT INTO Product VALUES ('p2', 'tv', 250);",
//                "INSERT INTO Product VALUES ('p3', 'ver', 80);",
//                "INSERT INTO Depot VALUES ('d1', 'New York', 9000);",
//                "INSERT INTO Depot VALUES ('d2', 'Syracuse', 6000);",
//                "INSERT INTO Depot VALUES ('d4', 'New York', 2000);",
//
//                "INSERT INTO Stock VALUES ('p1', 'd1', 1000);",
//                "INSERT INTO Stock VALUES ('p1', 'd2', -100);",
//                "INSERT INTO Stock VALUES ('p1', 'd4', 1200);",
//                "INSERT INTO Stock VALUES ('p3', 'd1', 3000);",
//                "INSERT INTO Stock VALUES ('p3', 'd4', 2000);",
//                "INSERT INTO Stock VALUES ('p2', 'd4', 1500);",
//                "INSERT INTO Stock VALUES ('p2', 'd1', -400);",
//                "INSERT INTO Stock VALUES ('p2', 'd2', 2000) "
        );
        db.executeUpdates(inserts);
//        db.updateSchema();

        db.updateDepotName();
        db.closeResources();
    }
}