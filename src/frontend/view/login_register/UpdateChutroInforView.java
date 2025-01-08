package frontend.view.login_register;

import controller.RegisterController;

import javax.swing.*;
import java.awt.*;

public class UpdateChutroInforView {
    public UpdateChutroInforView(String username, String role) {
        JFrame frame = new JFrame("Cập nhật thông tin chủ trọ");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Cập nhật thông tin", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        panel.add(titleLabel, gbc);

        JLabel labelFullName = new JLabel("Họ tên:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(labelFullName, gbc);

        JTextField fullNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(fullNameField, gbc);

        JLabel labelCCCD = new JLabel("Số CCCD:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(labelCCCD, gbc);

        JTextField cccdField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(cccdField, gbc);

        JLabel labelPhone = new JLabel("Số điện thoại:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(labelPhone, gbc);

        JTextField phoneField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(phoneField, gbc);

        JButton updateButton = new JButton("Cập nhật");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        updateButton.addActionListener(e -> {
            String fullName = fullNameField.getText().trim();
            String cccd = cccdField.getText().trim();
            String phone = phoneField.getText().trim();
//            System.out.println(username);
            RegisterController.updateInfo(fullName, cccd, phone, username, role, frame);
        });
        panel.add(updateButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
