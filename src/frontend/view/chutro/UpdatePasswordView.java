package frontend.view.chutro;

import backend.model.Chutro;

import javax.swing.*;
import java.awt.*;

public class UpdatePasswordView {
    private JFrame frame;

    public UpdatePasswordView(String idChutro) {
        frame = new JFrame("Đổi Mật Khẩu");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Mật Khẩu Mới:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(label, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        frame.add(passwordField, gbc);

        JButton updateButton = new JButton("Đổi Mật Khẩu");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        updateButton.addActionListener(e -> {
            String newPassword = new String(passwordField.getPassword());
            if (newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Mật khẩu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean isUpdated = Chutro.updatePasswordChutro(idChutro, newPassword);
                if (isUpdated) {
                    JOptionPane.showMessageDialog(frame, "Đổi mật khẩu thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Đổi mật khẩu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(updateButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
