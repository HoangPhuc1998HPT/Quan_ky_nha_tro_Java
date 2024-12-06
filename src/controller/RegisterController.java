package controller;

import backend.Register;
import javax.swing.*;

public class RegisterController {
    public static void checkRegisterController(JFrame frame) {
        System.out.println("Chuyển đến xử lý giao diện đăng ký");
    }

    public static boolean handleRegister(String role, String username, String password, String confirmPassword) {
        // Gọi backend để xử lý logic đăng ký
        boolean isRegistered = Register.registerUser(role, username, password, confirmPassword);

        if (isRegistered) {
            System.out.println("Đăng ký thành công!");
        } else {
            System.out.println("Đăng ký thất bại. Vui lòng kiểm tra thông tin.");
        }

        return isRegistered;
    }
}
