package backend;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Login {
    public static int check_login(JFrame f, String username, String pasword){
        // check login
        System.out.println(" login chưa được thiết lập");
        return 1;
    }

    public static class DatabaseConnection {

        // Thông tin kết nối
        private static final String URL = "jdbc:sqlserver://DESKTOP-C7M760B\\SQLEXPRESS;databaseName=YourDatabaseName;integratedSecurity=true";
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

        public static void main(String[] args) {
            Connection connection = getConnection();
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Đóng kết nối thành công!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
