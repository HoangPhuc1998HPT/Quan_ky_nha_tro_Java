package frontend.view;

import controller.RoomController;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class RoomUpdatePriceView {
    private JFrame frame;

    public RoomUpdatePriceView(String roomId, String title, String label, String currentValue, String unit, String type) {
        frame = new JFrame(title);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Định dạng giá trị tiền
        String formattedValue = formatCurrency(Double.parseDouble(currentValue));

        // Tiêu đề
        JLabel titleLabel = new JLabel(label);
        titleLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(titleLabel, gbc);

        // Giá trị hiện tại
        JLabel currentLabel = new JLabel("Giá trị hiện tại: " + formattedValue + " " + unit);
        currentLabel.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridy = 1;
        frame.add(currentLabel, gbc);

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

        // Nút cập nhật
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
                try {
                    double updatedValue = Double.parseDouble(newValue);
                    RoomController.updateRoomPrice(roomId, updatedValue, type);
                    JOptionPane.showMessageDialog(frame, "Cập nhật thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(updateButton, gbc);

        // Nút quay lại
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridy = 4;
        backButton.addActionListener(e -> frame.dispose());
        frame.add(backButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Hàm định dạng giá trị tiền
    private String formatCurrency(double value) {
        NumberFormat currencyFormatter = NumberFormat.getNumberInstance(Locale.US);
        return currencyFormatter.format(value);
    }
}