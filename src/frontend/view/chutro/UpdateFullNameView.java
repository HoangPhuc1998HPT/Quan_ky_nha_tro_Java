package frontend.view.chutro;

import backend.model.Chutro;

import javax.swing.*;
import java.awt.*;

public class UpdateFullNameView {
    private JFrame frame;
    public static String updateName;

    public UpdateFullNameView(int idChutro, String currentName) {
        frame = new JFrame("Cập Nhật Họ Tên");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Họ Tên:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(label, gbc);

        JTextField nameField = new JTextField(currentName, 20);
        gbc.gridx = 1;
        frame.add(nameField, gbc);

        JButton updateButton = new JButton("Cập Nhật");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        updateButton.addActionListener(e -> {
            String updatedName = nameField.getText();
            if (updatedName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Họ tên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean isUpdated = Chutro.updateHoTenChutro(idChutro, updatedName);
                if (isUpdated) {
                    updateName = updatedName;
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


    public static String getUpdateName() {
        return updateName;
    }
}
