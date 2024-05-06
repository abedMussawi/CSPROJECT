package com.abedmussawi.www;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InsertAndUpdates {
    private Connection conn;
    private static Statement stmt;

    public InsertAndUpdates() throws SQLException {
        initializeDatabase();
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        stmt = conn.createStatement();
    }

    public void executeUpdates(@org.jetbrains.annotations.NotNull List<String> sqlStatements) throws SQLException {
        if (!sqlStatements.isEmpty()) {
            for (String sql : sqlStatements) {
                stmt.executeUpdate(sql);
            }
            conn.commit(); // Commit the transaction if all insertions are successful
        }
    }

    private void initializeDatabase() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Project", "postgres", "7729911");
        new CreatDB(); // This initializes the database structure
    }

    public void updateSchema() throws SQLException {
        stmt.executeUpdate("ALTER TABLE Product ADD CONSTRAINT pk_Product PRIMARY KEY(prodid);");
        stmt.executeUpdate("ALTER TABLE Depot ADD CONSTRAINT pk_Depot PRIMARY KEY(depid);");
        stmt.executeUpdate("ALTER TABLE Stock ADD CONSTRAINT pk_Stock PRIMARY KEY(prodid, depid);");
        stmt.executeUpdate("ALTER TABLE Stock ADD CONSTRAINT fk_Stock_prodid_Product FOREIGN KEY (prodid) REFERENCES Product(prodid);");
        stmt.executeUpdate("ALTER TABLE Stock ADD CONSTRAINT fk_Stock_depid_Depot FOREIGN KEY (depid) REFERENCES Depot(depid) ON UPDATE CASCADE;");
        conn.commit();
    }

    public void closeResources() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public void updateDepotName() throws SQLException {
        conn.setAutoCommit(false); // For Atomicity

        // Update the depid in the Depot table and it will cascade to Stock
        stmt.executeUpdate("UPDATE Depot SET depid = 'dd1' WHERE depid = 'd1';");

        conn.commit(); // Commit the transaction
        System.out.println("Transaction completed successfully.");

    }
    public static void showResults () throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM Depot");
        System.out.println("Depot");
        while (rs.next()) {
            System.out.println(rs.getString("Depid"));
        }
        ResultSet rs1 = stmt.executeQuery("SELECT * FROM Stock");
        System.out.println("Stock");
        while (rs1.next()) {
            System.out.println(rs1.getString("Depid"));
        }

    }
}
