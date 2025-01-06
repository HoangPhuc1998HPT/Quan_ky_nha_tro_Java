package controller;

import backend.Register;
import frontend.view.RegisterView;
import frontend.view.UpdateChutroInforView;
import frontend.view.nguoiThueTroDashboardView;

import javax.swing.*;

public class RegisterController {
    public static void handleRegister(String username, String password, String confirmPassword, String role, JFrame frame) {
        boolean result = Register.registerUser(role, username, password, confirmPassword);
//        Debug lineqQq
//        boolean userCheckResult = Register.userNameCheck(username);
//        System.out.println("Result user check: " + userCheckResult)
        System.out.println(result);

        switch(role) {
            case "chutro":
                if (result) {
                    JOptionPane.showMessageDialog(frame, "Bạn đã đăng ký thành công với vai trò chủ trọ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new UpdateChutroInforView(username); // Chuyển hướng đến UpdateChutroInforView
                } else {
                    JOptionPane.showMessageDialog(frame, "Tên tài khoản đã tồn tại hoặc xác nhận mật khẩu không chính xác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(frame, "Đăng ký thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "nguoithuetro":
                if (result) {
                    JOptionPane.showMessageDialog(frame, "Bạn đã đăng ký thành công với vai trò người thuê trọ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new nguoiThueTroDashboardView();
                } else {
                    JOptionPane.showMessageDialog(frame, "Tên tài khoản đã tồn tại hoặc xác nhận mật khẩu không chính xác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(frame, "Đăng ký thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }

    }

    public static void updateChutroInfo(String fullName, String cccd, String phone, String username, JFrame frame) {
//        System.out.println(username);
        String userID = Register.getUserIDFromUsers(username);
//        System.out.println("UserID: " + userID);
        boolean result = Register.updateChutroInfo(userID, fullName, cccd, phone);

        if (result) {
            JOptionPane.showMessageDialog(frame, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

