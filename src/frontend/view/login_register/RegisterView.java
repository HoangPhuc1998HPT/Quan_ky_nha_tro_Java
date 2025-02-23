package frontend.view.login_register;

import controller.RegisterController;
import frontend.components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class RegisterView {
    private final JFrame frame;
    private ButtonGroup roleGroup;

    public RegisterView() {
        frame = new JFrame("Đăng ký tài khoản");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel titleLabel = new JLabel("Đăng ký tài khoản", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        panel.add(titleLabel, gbc);

        // Nhập tên tài khoản
        JLabel labelUsername = new JLabel("Tên tài khoản:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(labelUsername, gbc);

        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(usernameField, gbc);

        // Nhập mật khẩu
        JLabel labelPassword = new JLabel("Mật khẩu:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(labelPassword, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(passwordField, gbc);

        // Xác nhận mật khẩu
        JLabel labelConfirmPassword = new JLabel("Xác nhận mật khẩu:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(labelConfirmPassword, gbc);

        JPasswordField confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(confirmPasswordField, gbc);

        // Thêm lựa chọn vai trò
        JLabel roleLabel = new JLabel("Chọn vai trò:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(roleLabel, gbc);

        JRadioButton landlordButton = new JRadioButton("Chủ nhà trọ");
        JRadioButton tenantButton = new JRadioButton("Người thuê trọ");

        roleGroup = new ButtonGroup();
        roleGroup.add(landlordButton);
        roleGroup.add(tenantButton);

        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rolePanel.add(landlordButton);
        rolePanel.add(tenantButton);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        panel.add(rolePanel, gbc);

        // Nút Đăng ký
        JButton registerButton = new JButton("Đăng ký");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String selectedRole = landlordButton.isSelected() ? "chutro" : tenantButton.isSelected() ? "nguoithuetro" : "";

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Vui lòng không để trống tên đăng nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Vui lòng không để trống mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Vui lòng xác nhận mật khẩu đã nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedRole.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Vui lòng chọn vai trò!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            RegisterController.handleRegister( username, password, confirmPassword,selectedRole, frame);

        });
        panel.add(registerButton, gbc);

        // Nút Quay lại
        CustomButton backButton = new CustomButton("Quay lại");
        backButton.setFont(new Font("Be Vietnampro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        backButton.addActionListener(e->RegisterController.goToHomelogin(frame));
        panel.add(backButton, gbc);

        // Thêm panel vào frame
        frame.add(panel, BorderLayout.CENTER);

        // Hiển thị giao diện
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterView::new);
    }
}
