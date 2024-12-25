// Trong thư mục controller
package controller;


import backend.Login;
import frontend.view.ChutroDashboardView;
import frontend.view.adminDashboard;
import frontend.view.loginView;
import frontend.view.nguoiThueTroDashboardView;

import javax.swing.*;

import javax.swing.JFrame;




public class LoginController {
    public static void showRegisterView(JFrame frame) {
        // Mã để hiển thị giao diện đăng ký
        frame.setVisible(false);

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


        public static void checkLogin(String username, char[] password, JFrame frame) {
            int loginResult = Login.check_login(username, new String(password));
            frame.dispose(); // Đóng giao diện đăng nhập

            switch (loginResult) {
                case 1:
                    // gọi hàm check với database trước khi xuất

                    new ChutroDashboardView(username);
                    break;
                case 2:
                    // gọi hàm check với database trước khi xuất
                    new nguoiThueTroDashboardView();
                    break;
                case 0:
                    // gọi hàm check với database trước khi xuất
                    new adminDashboard();
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Đăng nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

