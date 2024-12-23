package backend.model;

import backend.connectDatabase;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NguoiThueTro {
    public static String getTenNguoiThueInRoom(String id_phong) {
        System.out.println("Lấy thông tin người thuê trọ từ id_phòng");
        // Hiệu chỉnh lại khi tạo class
        String id_nguoithuetro = "P001";
        return id_nguoithuetro;
    }
    // check update người thuê trọ vào room
    public static void UpdateNguoiThueInRoom(String cccdValue, String id_room, JFrame frame) {
        System.out.println(" đã gọi UpdateNguoiThue");

        if (cccdValue.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Vui lòng nhập mã số CCCD", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Kết nối tới cơ sở dữ liệu
            connection = connectDatabase.DatabaseConnection.getConnection();

            // Truy vấn SQL để cập nhật dữ liệu
            String sql = "UPDATE PhongTro SET id_nguoithue = ? WHERE id_phong = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Gán giá trị cho tham số trong câu truy vấn
            preparedStatement.setString(1, cccdValue);
            preparedStatement.setString(2, id_room);

            // Thực thi truy vấn
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Cập nhật người thuê trọ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Không tìm thấy phòng trọ với ID đã cho!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Lỗi khi cập nhật dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            // Đóng kết nối và giải phóng tài nguyên
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
