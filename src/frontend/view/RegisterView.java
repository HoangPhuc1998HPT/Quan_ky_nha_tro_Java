package frontend.view;

import controller.RegisterController;
import frontend.components.CustomButton;

import javax.swing.*;
import java.awt.*;

    public class RegisterView {
        private final JFrame frame;
        private  ButtonGroup roleGroup;

        public RegisterView() {

            frame = new JFrame("Đăng ký tài khoản");
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel titleLabel = new JLabel("Đăng ký tài khoản", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 5;
            panel.add(titleLabel, gbc);

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

            JButton registerButton = new JButton("Đăng ký");
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            registerButton.addActionListener(e -> {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                RegisterController.handleRegister(username, password, confirmPassword, frame);
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
