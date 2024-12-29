package frontend.view;

import controller.RegisterController;
import frontend.components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class RegisterView {
    private final JFrame frame;
    private final ButtonGroup roleGroup;

    public RegisterView() {
        // Tạo cửa sổ chính
        frame = new JFrame("Đăng ký tài khoản");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Giữ các thành phần ở giữa
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tạo tiêu đề cho JFrame
        JLabel titleLabel = new JLabel("Đăng ký tài khoản", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        panel.add(titleLabel, gbc);
        frame.add(panel, BorderLayout.CENTER);

        // Chọn vai trò đăng kí

        JLabel labelVaiTro = new JLabel("Vai trò ");
        labelVaiTro.setFont(new Font("Be Vietnampro", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(labelVaiTro, gbc);
        // Tạo radio button cho vai trò chủ trọ
        roleGroup = new ButtonGroup();
        JRadioButton ChutroRadioButton = new JRadioButton("Chủ trọ");
        ChutroRadioButton.setFont(new Font("Be Vietnampro", Font.PLAIN, 14));
        ChutroRadioButton.setActionCommand("chutro");
        ChutroRadioButton.setSelected(true);
        roleGroup.add(ChutroRadioButton);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(ChutroRadioButton, gbc);
        // Tạo radio cho vai trò người thuê trọ
        JRadioButton userRadioButton = new JRadioButton("Người thuê trọ");
        userRadioButton.setFont(new Font("Be Vietnampro", Font.PLAIN, 14));
        userRadioButton.setActionCommand("nguoithuetro");
        roleGroup.add(userRadioButton);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(userRadioButton, gbc);

        // Tạo tài khoản
        JLabel labelUsername = new JLabel(" Nhập tên tài khoản");
        labelUsername.setFont(new Font("Be Vietnampro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        panel.add(labelUsername, gbc);

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Be Vietnampro", Font.PLAIN, 14));
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(usernameField, gbc);

        // Tạo mật khẩu
        JLabel labelPassword = new JLabel(" Nhập mật khẩu");
        labelPassword.setFont(new Font("Be Vietnampro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(labelPassword, gbc);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Be Vietnampro", Font.PLAIN, 14));
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(passwordField, gbc);
        // Nhập lại mật khẩu
        JLabel labelConfirmPassword = new JLabel(" Nhập lặp mật khẩu");
        labelConfirmPassword.setFont(new Font("Be Vietnampro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        panel.add(labelConfirmPassword, gbc);
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Be Vietnampro", Font.PLAIN, 14));
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        panel.add(confirmPasswordField, gbc);

        // Tạo button đăng kí
        JButton registerButton = new JButton("Đăng ký");
        registerButton.setFont(new Font("Be Vietnampro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        registerButton.addActionListener(e -> {
            String role = roleGroup.getSelection().getActionCommand(); // Vai trò
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            boolean result = RegisterController.handleRegister(role, username, password, confirmPassword);
            if (result) {
                JOptionPane.showMessageDialog(frame, "Đăng ký thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new loginView();
            } else {
                JOptionPane.showMessageDialog(frame, "Đăng ký thất bại. Vui lòng kiểm tra thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(registerButton, gbc);

        // Thêm panel vào JFrame
        frame.add(panel, BorderLayout.CENTER);

        CustomButton backButton = new CustomButton("Quay lại");
        backButton.setFont(new Font("Be Vietnampro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        frame.add(panel, BorderLayout.CENTER);

        // Tạo tài khoản
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterView::new);}
}
