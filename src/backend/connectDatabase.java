package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDatabase {
    public static class DatabaseConnection {

        // Thông tin kết nối
        private static final String URL = "jdbc:sqlserver://DESKTOP-C7M760B\\SQLEXPRESS;databaseName=QLThueNhaTro_java;integratedSecurity=true;encrypt=false";
        private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        public static Connection getConnection() {
            Connection connection = null;
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL);
                System.out.println("Kết nối thành công!");
            } catch (ClassNotFoundException e) {
                System.out.println("Không tìm thấy Driver!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Kết nối thất bại!");
                e.printStackTrace();
            }
            return connection;
        }
    }
}
