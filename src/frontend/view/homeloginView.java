package frontend.view;

import javax.swing.*;
import java.awt.*;
import controller.LoginController;
import frontend.components.CustomButton;

public class homeloginView {
    JFrame frame;

    public homeloginView() {
        frame = new JFrame("Ứng dụng quản lý thuê trọ");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout để căn chỉnh

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL; // Thành phần sẽ kéo dãn theo chiều ngang
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa các thành phần

        // Tiêu đề
        JLabel welcomeLabel = new JLabel("Chào mừng bạn đến với ứng dụng quản lý thuê trọ");
        welcomeLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 16)); // Font chữ in đậm
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Tiêu đề chiếm 2 cột
        frame.add(welcomeLabel, gbc);

        // Nút đăng ký
        CustomButton registerButton = new CustomButton("Đăng ký");
        registerButton.addActionListener(e -> LoginController.showRegisterView(frame));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset lại gridwidth
        frame.add(registerButton, gbc);

        // Nút đăng nhập
        CustomButton loginButton = new CustomButton("Đăng nhập");
        loginButton.addActionListener(e -> LoginController.showLoginView(frame));
        gbc.gridx = 1; // Chuyển sang cột 1
        gbc.gridy = 1;
        frame.add(loginButton, gbc);

        // Hiển thị giao diện
        frame.setLocationRelativeTo(null); // Căn giữa màn hình
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(homeloginView::new);
    }
}
