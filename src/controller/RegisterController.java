package controller;

import backend.Register;
import frontend.view.UpdateChutroInforView;

import javax.swing.*;

public class RegisterController {
    public static void handleRegister(String username, String password, String confirmPassword, JFrame frame) {
        boolean result = Register.registerUser("chutro", username, password, confirmPassword);

        if (result) {
            frame.dispose();
            new UpdateChutroInforView(username); // Chuyển hướng đến UpdateChutroInforView
        } else {
            JOptionPane.showMessageDialog(frame, "Đăng ký thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void updateChutroInfo(String username, String fullName, String cccd, String phone, JFrame frame) {
        boolean result = Register.updateChutroInfo(username, fullName, cccd, phone);

        if (result) {
            JOptionPane.showMessageDialog(frame, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
