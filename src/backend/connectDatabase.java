package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDatabase {

    public static class DatabaseConnection {

        // Thông tin kết nối
        private static final String Phuc_URL = "jdbc:sqlserver://DESKTOP-C7M760B\\SQLEXPRESS;databaseName=QLThueNhaTro_java;integratedSecurity=true;encrypt=false";
        private static final String Hieu_URL = "jdbc:sqlserver://HIEUTRAN\\SQLEXPRESS;databaseName=QLThueNhaTro_java;integratedSecurity=true;encrypt=false";
        private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private static final int MAX_RETRY_ATTEMPTS = 3;  // Number of connection retries

        public static Connection getConnection() {
            Connection connection = null;
            String currentURL = Phuc_URL; // Start with the first URL
            int attemptCount = 0;

            while (attemptCount < MAX_RETRY_ATTEMPTS) {
                try {
                    Class.forName(DRIVER);
                    if (connection == null || connection.isClosed()) {
                        connection = DriverManager.getConnection(currentURL);
                        System.out.println("Kết nối với " + (currentURL.equals(Phuc_URL) ? "Phuc's" : "Hieu's") + " server thành công!");
                        return connection; // Return immediately on successful connection
                    }

                } catch (ClassNotFoundException e) {
                    System.out.println("Không tìm thấy Driver!");
                    e.printStackTrace();
                    return null; // Exit early on driver error
                } catch (SQLException e) {
                    System.out.printf("Kết nối với %s thất bại (lần %d): %s\n", (currentURL.equals(Phuc_URL) ? "Phuc's" : "Hieu's"), attemptCount + 1, e.getMessage());
                    attemptCount++;

                    // Switch to the other URL after the first attempt fails
                    if (attemptCount == 1) {
                        currentURL = (currentURL.equals(Phuc_URL) ? Hieu_URL : Phuc_URL);
                        System.out.println("Đang chuyển sang kết nối với " + (currentURL.equals(Phuc_URL) ? "Phuc's" : "Hieu's") + " server...");

                    }
                }
            }

            System.out.println("Không thể kết nối với bất kỳ server nào sau " + MAX_RETRY_ATTEMPTS + " lần thử.");
            return null;
        }
    }
}
