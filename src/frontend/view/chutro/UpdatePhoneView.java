package frontend.view.chutro;

import backend.model.Chutro;

import javax.swing.*;
import java.awt.*;

public class UpdatePhoneView {
    private JFrame frame;

    public UpdatePhoneView(String idChutro, String currentPhone) {
        frame = new JFrame("Cập Nhật Số Điện Thoại");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Số Điện Thoại:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(label, gbc);

        JTextField phoneField = new JTextField(currentPhone, 20);
        gbc.gridx = 1;
        frame.add(phoneField, gbc);

        JButton updateButton = new JButton("Cập Nhật");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        updateButton.addActionListener(e -> {
            String updatedPhone = phoneField.getText();
            if (updatedPhone.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Số điện thoại không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean isUpdated = Chutro.updateSDTChutro(idChutro, updatedPhone);
                if (isUpdated) {
                    JOptionPane.showMessageDialog(frame, "Cập nhật thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(updateButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
