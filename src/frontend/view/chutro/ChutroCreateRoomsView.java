package frontend.view.chutro;

import javax.swing.*;
import java.awt.*;

import static controller.DashboardChutroController.go_back_dashboardchutro;
import static controller.DashboardChutroController.save_room_into_database;

public class ChutroCreateRoomsView {
    public ChutroCreateRoomsView(int id_chutro) {
        JFrame frame = new JFrame("Tạo phòng trọ mới");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout để hỗ trợ GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel label_create_rooms = new JLabel("Tạo phòng trọ mới");
        label_create_rooms.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa tiêu đề
        panel.add(label_create_rooms, gbc);

        // Nhập tên phòng
        JLabel label_ten_phong = new JLabel("Nhập tên phòng:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label_ten_phong, gbc);

        JTextField ten_phong = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(ten_phong, gbc);

        // Nhập địa chỉ
        JLabel label_dia_chi = new JLabel("Nhập địa chỉ:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(label_dia_chi, gbc);

        JTextField dia_chi = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(dia_chi, gbc);

        // Nhập giá phòng
        JLabel label_gia_phong = new JLabel("Nhập giá phòng:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(label_gia_phong, gbc);

        JTextField gia_phong = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(gia_phong, gbc);

        JLabel label_don_vi = new JLabel("VNĐ");
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(label_don_vi, gbc);

        // Nhập giá điện
        JLabel label_gia_dien = new JLabel("Nhập giá điện:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(label_gia_dien, gbc);

        JTextField gia_dien = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(gia_dien, gbc);

        label_don_vi = new JLabel("VNĐ");
        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(label_don_vi, gbc);

        // Nhập giá nước
        JLabel label_gia_nuoc = new JLabel("Nhập giá nước:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(label_gia_nuoc, gbc);

        JTextField gia_nuoc = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(gia_nuoc, gbc);

        label_don_vi = new JLabel("VNĐ");
        gbc.gridx = 2;
        gbc.gridy = 5;
        panel.add(label_don_vi, gbc);

        // Nhập giá rác
        JLabel label_gia_rac = new JLabel("Nhập giá rác:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(label_gia_rac, gbc);

        JTextField gia_rac = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(gia_rac, gbc);

        label_don_vi = new JLabel("VNĐ");
        gbc.gridx = 2;
        gbc.gridy = 6;
        panel.add(label_don_vi, gbc);

        // Nhập số điện hiện tại
        JLabel label_sodien = new JLabel("Số điện hiện tại:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(label_sodien, gbc);

        JTextField so_dien_hien_tai = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(so_dien_hien_tai, gbc);

        // Nhập số nước hiện tại
        JLabel label_sonuoctai = new JLabel("Số nước hiện tại:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(label_sonuoctai, gbc);

        JTextField so_nuoc_hien_tai = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(so_nuoc_hien_tai, gbc);

        // Nút lưu phòng
        JButton save_room = new JButton("Lưu phòng");
        gbc.gridx = 1;
        gbc.gridy = 9;
        save_room.addActionListener(e -> {
            try {
                // Lấy dữ liệu từ các trường nhập liệu
                String name = ten_phong.getText().trim();
                String address = dia_chi.getText().trim();
                double roomPrice = Double.parseDouble(gia_phong.getText().trim());
                double electricityPrice = Double.parseDouble(gia_dien.getText().trim());
                double waterPrice = Double.parseDouble(gia_nuoc.getText().trim());
                double garbagePrice = Double.parseDouble(gia_rac.getText().trim());
                int currentElectricity = Integer.parseInt(so_dien_hien_tai.getText().trim());
                int currentWater = Integer.parseInt(so_nuoc_hien_tai.getText().trim());

                // Gọi hàm lưu vào database
                save_room_into_database(name, address, roomPrice, electricityPrice, waterPrice, garbagePrice, currentElectricity, currentWater, id_chutro, frame);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(save_room, gbc);

        // Nút quay lại
        JButton button_back = new JButton("Quay lại");
        gbc.gridx = 1;
        gbc.gridy = 10;
        button_back.addActionListener(e -> frame.dispose());
        panel.add(button_back, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null); // Căn giữa màn hình
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ChutroCreateRoomsView(1);
    }
}
