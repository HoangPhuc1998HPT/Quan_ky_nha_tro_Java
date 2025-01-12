package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDatabase {

    public static class DatabaseConnection {
        private static final String Localhost_URL = "jdbc:sqlserver://localhost;databaseName=QLThueNhaTro_java;integratedSecurity=true;encrypt=false";
        private static final String Phuc_URL = "jdbc:sqlserver://DESKTOP-C7M760B\\SQLEXPRESS;databaseName=QLThueNhaTro_java;integratedSecurity=true;encrypt=false";
        private static final String Hieu_URL = "jdbc:sqlserver://HIEUTRAN\\SQLEXPRESS;databaseName=QLThueNhaTro_java;integratedSecurity=true;encrypt=false";
        private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        public static Connection getConnection() {
            String[] urls = {Localhost_URL, Phuc_URL, Hieu_URL};
            for (String currentURL : urls) {
                try {
                    Class.forName(DRIVER);
                    Connection connection = DriverManager.getConnection(currentURL);
//                    System.out.println("Kết nối với server " + currentURL + " thành công!");
                    return connection;
                } catch (ClassNotFoundException e) {
                    System.out.println("Không tìm thấy Driver!");
                    e.printStackTrace();
                    return null;
                } catch (SQLException e) {
//                    System.out.printf("Kết nối với server %s thất bại: %s\n", currentURL, e.getMessage());
                }
            }
            System.out.println("Không thể kết nối với bất kỳ server nào.");
            return null;
        }
    }
}