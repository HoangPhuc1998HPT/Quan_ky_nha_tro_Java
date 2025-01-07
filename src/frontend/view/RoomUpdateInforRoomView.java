package frontend.view;

import backend.model.Room;
import controller.RoomController;

import javax.swing.*;
import java.awt.*;

import static controller.RoomController.GoToBackRoomViewFromUpdate;

public class RoomUpdateInforRoomView {
    private JFrame frame;
    private String id_chutro;

    public RoomUpdateInforRoomView(String roomId, String id_chutro) {
        frame = new JFrame("Cập nhật thông tin phòng");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Lấy thông tin phòng từ database
        Room room = Room.getRoomDetails(roomId);

        if (room == null) {
            JOptionPane.showMessageDialog(frame, "Không thể tải thông tin phòng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            return;
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel titleLabel = new JLabel("Tên phòng: " + room.getName());
        titleLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        frame.add(titleLabel, gbc);

        JLabel tenantLabel = new JLabel("Người thuê: " + room.getTenantName());
        tenantLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridy = 1;
        frame.add(tenantLabel, gbc);

        gbc.gridwidth = 1;

        // Cấu trúc bảng
        String[] labels = {"Giá thuê phòng:", "Giá điện:", "Giá nước:", "Giá rác:"};
        double[] values = {room.getRoomPrice(), room.getElectricityPrice(), room.getWaterPrice(), RoomController.getGarbageFee(roomId)};
        String[] units = {"VNĐ/tháng", "VNĐ/kWh", "VNĐ/m³", "VNĐ/tháng"};

        for (int i = 0; i < labels.length; i++) {
            // Nhãn
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = i + 2;
            frame.add(label, gbc);

            // Giá trị hiện tại
            JLabel valueLabel = new JLabel(String.valueOf(values[i]));
            valueLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
            gbc.gridx = 1;
            frame.add(valueLabel, gbc);

            // Đơn vị
            JLabel unitLabel = new JLabel(units[i]);
            unitLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
            gbc.gridx = 2;
            frame.add(unitLabel, gbc);

            // Nút cập nhật
            JButton updateButton = new JButton("Cập nhật");
            updateButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
            int finalI = i;
            updateButton.addActionListener(e -> new RoomUpdatePriceView(
                    room.getIdRoom(),
                    "Cập nhật " + labels[finalI],
                    labels[finalI],
                    String.valueOf(values[finalI]),
                    units[finalI],
                    labels[finalI].toLowerCase().replace(" ", "_")
            ));
            gbc.gridx = 3;
            frame.add(updateButton, gbc);
        }

        // Nút quay lại
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = labels.length + 2;
        gbc.gridwidth = 4;
        backButton.addActionListener(e -> GoToBackRoomViewFromUpdate(frame, roomId, id_chutro));

        frame.add(backButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoomUpdateInforRoomView("1", "12"));
    }
}
