// Trong thư mục controller
package controller;


import backend.Login;
import backend.connectDatabase;
import backend.model.Admin;
import backend.model.Chutro;
import backend.model.NguoiThueTro;
import backend.model.Room;
import frontend.view.admin.AdminDashboardView;
import frontend.view.chutro.ChutroDashboardView;
import frontend.view.login_register.RegisterView;
import frontend.view.login_register.loginView;
import frontend.view.nguoithuetro.NguoiThueTroDashboard_0View;
import frontend.view.nguoithuetro.NguoiThueTroDashboard_0View;
import frontend.view.nguoithuetro.NguoiThueTroDashboard_1View;

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
                            rs.getInt("IDChutro"),
                            rs.getInt("UserID"),
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
        //TODO Hiếu nhớ đưa checkLogin vào Login.java nha

        public static void checkLogin(String username, String password, JFrame frame) {
            Login.LoginResult result = Login.checkLogin(username, password);
            System.out.println(result);
            if (result != null) {
                System.out.println("Đăng nhập thành công. UserID: " + result.getUserId() + ", Role: " + result.getRole());
                int userId = result.getUserId();
                String role = result.getRole();

                switch (role) {
                    case "chutro":
                        Chutro chutro = Chutro.getChutroByUserId(userId);
                        if (chutro != null) {
                            new ChutroDashboardView(username);
                            frame.setVisible(false);
                            System.out.println("Mở dashboard Chủ trọ thành công.");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Không tìm thấy thông tin chủ trọ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case "nguoithuetro":
                        // Lấy thông tin người thuê trọ
                        NguoiThueTro tenant = NguoiThueTro.getNguoiThueTroByUserId(userId);
                        if (tenant != null) {
                            // Kiểm tra xem người thuê đã có phòng hay chưa
                            int roomId = Room.getTenantRoomId(tenant.getIdNguoiThue()); // Gọi hàm kiểm tra phòng
                            if (roomId == 0) {
                                // Chưa được thêm vào bất cứ phòng nào
                                new NguoiThueTroDashboard_0View(userId);
                                System.out.println("Chuyển đến Dashboard Người Thuê Trọ (chưa có phòng)");
                            } else {
                                // Đã có phòng
                                new NguoiThueTroDashboard_1View(userId);
                                System.out.println("Chuyển đến Dashboard Người Thuê Trọ (đã có phòng)");
                            }
                            frame.setVisible(false);
                        } else {
                            System.out.println("Không tìm thấy thông tin Người Thuê Trọ với User ID: " + userId);
                            JOptionPane.showMessageDialog(frame, "Không tìm thấy thông tin Người Thuê Trọ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case "admin":
                        Admin admin = Admin.getAdminByUserId(userId);
                        if (admin != null) {
                            new AdminDashboardView(); // chưa c
                            frame.setVisible(false);
                            System.out.println("mở new Dashboard Admin");
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "Vai trò không xác định!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                boolean usernameCheck = Login.usernameCheck(username);
                boolean passwordCheck = Login.passwordCheck(password);
                boolean isActive = Login.isActive(username, password);
                if (usernameCheck) {
                    JOptionPane.showMessageDialog(frame, "User này chưa tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else if (passwordCheck) {
                    JOptionPane.showMessageDialog(frame, "Mật khẩu không đúng! Vui lòng kiểm tra lại mật khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else if (isActive) {
                    JOptionPane.showMessageDialog(frame, "Tài khoản chưa được kích hoạt! Vui lòng liên hệ Admin : 0325575333 để được kích hoạt.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    System.out.println("Đăng nhập thất bại.");
                    JOptionPane.showMessageDialog(frame, "Đăng nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
}

