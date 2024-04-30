package com.abedmussawi.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InsertAndUpdates {
    private Connection conn;
    private Statement stmt;

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
        conn.setAutoCommit(false); // Start transaction control

        // Update the depot identifier in the Depot table
        stmt.executeUpdate("UPDATE Depot SET depid = 'dd1' WHERE depid = 'd1';");

        // Optionally, handle other related updates here if necessary

        conn.commit(); // Commit the transaction
        System.out.println("Transaction completed successfully.");

        // Clean up resources; ideally should be handled outside or in a separate finally block if this method is part of a larger transaction
        if (stmt != null) {
            stmt.close();

        }
        if (conn != null) {
            conn.close();

        }
    }
    public void showResults () throws SQLException {
        
    }
}
