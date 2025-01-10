package frontend.view.rooms;


import backend.model.Room;
import javax.swing.*;
import java.awt.*;

public class RoomInforView {
    private JFrame frame;

    public RoomInforView(Room room) {
        frame = new JFrame("Thông tin phòng: " + (room != null ? room.getName() : "Không xác định"));
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông Tin Phòng Trọ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(titleLabel, gbc);

        // Hiển thị thông tin
        gbc.gridwidth = 1;
        addLabelAndValue("Tên chủ trọ:", room != null ? room.getTenantName() : "Không xác định", frame, gbc, 1);
        addLabelAndValue("Số điện thoại:", room != null ? String.valueOf(room.getElectricityPrice()) : "Không xác định", frame, gbc, 2);
        addLabelAndValue("Tên phòng:", room != null ? room.getName() : "Không xác định", frame, gbc, 3);
        addLabelAndValue("Địa chỉ:", "Địa chỉ phòng trọ", frame, gbc, 4);
        addLabelAndValue("Giá phòng:", room != null ? String.valueOf(room.getRoomPrice()) + " VNĐ" : "Không xác định", frame, gbc, 5);
        addLabelAndValue("Giá điện:", room != null ? String.valueOf(room.getElectricityPrice()) + " VNĐ/kWh" : "Không xác định", frame, gbc, 6);
        addLabelAndValue("Giá nước:", room != null ? String.valueOf(room.getWaterPrice()) + " VNĐ/m³" : "Không xác định", frame, gbc, 7);
        addLabelAndValue("Giá rác:", room != null ? String.valueOf(room.getWaterPrice()) : "Không xác định", frame, gbc, 8);
        addLabelAndValue("Tình trạng:", "Trống", frame, gbc, 9);

        // Nút quay lại
        JButton backButton = new JButton("Quay lại");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        backButton.addActionListener(e -> frame.dispose());
        frame.add(backButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addLabelAndValue(String label, String value, JFrame frame, GridBagConstraints gbc, int row) {
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = row;
        frame.add(labelComponent, gbc);

        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        frame.add(valueComponent, gbc);
    }
}

