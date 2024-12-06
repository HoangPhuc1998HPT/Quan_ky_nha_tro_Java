// Trong thư mục controller
package controller;

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
        System.out.println("Chuyển đến giao diện đăng nhập");
    }

}