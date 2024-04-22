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

    public void executeUpdates(List<String> sqlStatements) throws SQLException {
        for (String sql : sqlStatements) {
            stmt.executeUpdate(sql);
        }
        conn.commit(); // Commit the transaction if all insertions are successful
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
        stmt.executeUpdate("ALTER TABLE Stock ADD CONSTRAINT fk_Stock_depid_Depot FOREIGN KEY (depid) REFERENCES Depot(depid);");
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
}
