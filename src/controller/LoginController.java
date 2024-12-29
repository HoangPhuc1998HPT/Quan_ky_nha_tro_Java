// Trong thư mục controller
package controller;


import backend.Login;
import backend.connectDatabase;
import backend.model.Admin;
import backend.model.Chutro;
import backend.model.NguoiThueTro;
import frontend.view.*;

import javax.swing.*;

import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginController {
    public static void showRegisterView(JFrame frame) {
        // Mã để hiển thị giao diện đăng ký
        frame.setVisible(false);
        new RegisterView();
        System.out.println("Chuyển đến giao diện đăng ký");
    }

    public static void showLoginView(JFrame frame) {
        // Mã để hiển thị giao diện đăng nhập
        frame.setVisible(false);

        new loginView();
        System.out.println("Chuyển đến giao diện đăng nhập");
    }

    public static void logout(JFrame frame) {
        frame.setVisible(false);
        new loginView();
    }


    public static void exist_Window() {
        System.exit(0);
    }
    public static Chutro getChutroByUserId(String userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Chutro WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Chutro(
                        rs.getString("IDChutro"),
                        rs.getString("UserID"),
                        rs.getString("Hoten"),
                        rs.getString("Phone"),
                        rs.getString("CCCD")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void checkLogin(String username, char[] password, JFrame frame) {
        Login.LoginResult result = Login.checkLogin(username, new String(password));

        if (result != null) {
            String userId = result.getUserId();
            String role = result.getRole();

            switch (role) {
                case "chutro":
                    Chutro chutro = Chutro.getChutroByUserId(userId);
                    if (chutro != null) {
                        new ChutroDashboardView(username);
                    }
                    break;
                case "nguoithuetro":
                    NguoiThueTro tenant = NguoiThueTro.getNguoiThueTroByUserId(userId);
                    if (tenant != null) {
                        //new NguoiThueTroDashboardView(tenant);
                        System.out.println(" nhờ mở new Dashboard NGười thuê trọ");
                        //TODO: Tạo dashboard Người thuê trọ rồi kích hoạt lại ==> giờ tắt xóa lỗi

                    }
                    break;
                case "admin":
                    Admin admin = Admin.getAdminByUserId(userId);
                    if (admin != null) {
                       // new AdminDashboardView(admin); // chưa c
                        System.out.println(" nhờ mở new Dashboard ADmin");
                        //TODO: Tạo dashboard Admin rồi kích hoạt lại ==> giờ tắt xóa lỗi
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Vai trò không xác định!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Đăng nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

