package backend.utilities;

import backend.connectDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseHelper {

    public static Connection getConnection() throws SQLException {
        return connectDatabase.DatabaseConnection.getConnection();
    }

}
