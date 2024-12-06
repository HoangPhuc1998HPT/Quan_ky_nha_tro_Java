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

    public static void check_login(String entryUsername, JPasswordField entryPassword) {
        // Gọi phương thức kiểm tra
        int a = Login.check_login(entryUsername,entryUsername);
        if (a == 1) {
            new ChutroDashboardView();
        }
        else if (a == 2) {
            new nguoiThueTroDashboardView();
        }
        else if (a == 0) {
            new adminDashboard();
        }
    }

}