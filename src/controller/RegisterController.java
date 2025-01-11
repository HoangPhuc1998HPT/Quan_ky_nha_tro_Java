package controller;

import backend.Register;
import frontend.view.login_register.loginView;
import frontend.view.login_register.UpdateChutroInforView;

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
                    new UpdateChutroInforView(username, role); // Chuyển hướng đến UpdateChutroInforView
                } else {
                    JOptionPane.showMessageDialog(frame, "Tên tài khoản đã tồn tại hoặc xác nhận mật khẩu không chính xác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(frame, "Đăng ký thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "nguoithuetro":
                if (result) {
                    JOptionPane.showMessageDialog(frame, "Bạn đã đăng ký thành công với vai trò người thuê trọ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new UpdateChutroInforView(username, role);
                } else {
                    JOptionPane.showMessageDialog(frame, "Tên tài khoản đã tồn tại hoặc xác nhận mật khẩu không chính xác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(frame, "Đăng ký thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }

    }

    public static void updateInfo(String fullName, String cccd, String phone, String username, String role, JFrame frame) {
//        System.out.println(username);
        int userID = Register.getUserIDFromUsers(username);
//        System.out.println("UserID: " + userID);
        switch (role) {
            case "chutro":
                boolean resultChutro = Register.updateChutroInfo(userID, fullName, cccd, phone);
                if(resultChutro) {
                    JOptionPane.showMessageDialog(frame, "Cập nhật thành công thông tin chủ trọ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new loginView();
                } else {
                    JOptionPane.showMessageDialog(frame, "CCCD hay SĐT đã bị trùng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(frame, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            case "nguoithuetro":
                boolean resultNguoiTT = Register.updateNguoiTTInfo(userID, fullName, cccd, phone);
                if(resultNguoiTT) {
                    JOptionPane.showMessageDialog(frame, "Cập nhật thành công thông tin người thuê trọ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new loginView();
                } else {
                    JOptionPane.showMessageDialog(frame, "CCCD hay SĐT đã bị trùng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(frame, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
        }
    }
}

