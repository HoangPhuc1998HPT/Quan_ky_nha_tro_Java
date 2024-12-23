package backend;

import java.sql.Connection;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        // Thử kết nối tới cơ sở dữ liệu
        try (Connection connection = connectDatabase.DatabaseConnection.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Kết nối tới cơ sở dữ liệu thành công!");
            } else {
                System.out.println("Kết nối tới cơ sở dữ liệu thất bại!");
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi trong quá trình kết nối:");
            e.printStackTrace();
        }
    }
}
