package frontend.view;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public abstract class RoomBaseUpdateView {
    protected JFrame frame;

    public RoomBaseUpdateView(String title, String label, String currentValue, String unit, Consumer<String> onUpdate) {
        frame = new JFrame(title);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel titleLabel = new JLabel(label, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(titleLabel, gbc);

        // Hiển thị giá trị hiện tại
        JLabel currentValueLabel = new JLabel("Giá trị hiện tại: " + currentValue + " " + unit);
        currentValueLabel.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridy = 1;
        frame.add(currentValueLabel, gbc);

        // Ô nhập liệu
        JTextField inputField = new JTextField(15);
        inputField.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        frame.add(inputField, gbc);

        JLabel unitLabel = new JLabel(unit);
        unitLabel.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridx = 1;
        frame.add(unitLabel, gbc);

        // Nút Cập Nhật
        JButton updateButton = new JButton("Cập nhật");
        updateButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        updateButton.addActionListener(e -> {
            String newValue = inputField.getText();
            if (newValue.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Vui lòng nhập giá trị mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                onUpdate.accept(newValue);
                frame.dispose();
            }
        });
        frame.add(updateButton, gbc);

        // Nút Quay Lại
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridy = 4;
        backButton.addActionListener(e -> frame.dispose());
        frame.add(backButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
