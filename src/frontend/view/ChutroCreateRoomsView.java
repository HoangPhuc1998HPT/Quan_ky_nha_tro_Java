package frontend.view;

import javax.swing.*;
import java.awt.*;


import static controller.DashboardChutroController.go_back_dashboardchutro;
import static controller.DashboardChutroController.save_room_into_database;

public class ChutroCreateRoomsView {
    public ChutroCreateRoomsView(String id_chutro) {
        JFrame frame = new JFrame("Tạo phòng trọ mới ");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame
        //id phòng sẽ được tạo tự động sau khi nhấn tạo phòng
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout để hỗ trợ GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.CENTER;

        //Tạo các trường nhập thông tin
        JLabel label_create_rooms = new JLabel("Tạo phòng trọ mới");
        label_create_rooms.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa tiêu đề
        panel.add(label_create_rooms,gbc);
        // Thêm các trường cần thiết
        // Nhập tên phòng
        JLabel label_ten_phong = new JLabel("Nhập tên phòng");
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.anchor = GridBagConstraints.WEST; // Căn lề trái
        panel.add(label_ten_phong,gbc);

        JTextField ten_phong = new JTextField();
        ten_phong.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(ten_phong,gbc);
        // Nhập địa chỉ
        JLabel label_dia_chi = new JLabel("Nhập địa chỉ");
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.anchor = GridBagConstraints.WEST; // Căn lề trái
        panel.add(label_dia_chi,gbc);

        JTextField dia_chi = new JTextField(40);
        dia_chi.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(dia_chi,gbc);

        // Nhập giá phòng
        JLabel label_gia_phong = new JLabel("Nhập giá phòng");
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.anchor = GridBagConstraints.WEST; // Căn lề trái
        panel.add(label_gia_phong,gbc);

        JTextField gia_phong = new JTextField(40);
        gia_phong.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa ô nhập liệu
        panel.add(gia_phong,gbc);

        JLabel label_don_vi = new JLabel("VNĐ");
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(label_don_vi,gbc);

        // Nhập giá điện
        JLabel label_gia_dien = new JLabel("Nhập giá điện");
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.anchor = GridBagConstraints.WEST; // Căn lề trái
        panel.add(label_gia_dien,gbc);

        JTextField gia_dien = new JTextField(40);
        gia_dien.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa ô nhập liệu
        panel.add(gia_dien,gbc);

        label_don_vi = new JLabel("VNĐ");
        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(label_don_vi,gbc);

        // Nhập giá nước
        JLabel label_gia_nuoc = new JLabel("Nhập giá nước");
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.anchor = GridBagConstraints.WEST; // Căn lề trái
        panel.add(label_gia_nuoc,gbc);

        JTextField gia_nuoc = new JTextField(40);
        gia_nuoc.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa ô nhập liệu
        panel.add(gia_nuoc,gbc);

        label_don_vi = new JLabel("VNĐ");
        gbc.gridx = 2;
        gbc.gridy = 5;
        panel.add(label_don_vi,gbc);

        // Nhập giá rác
        JLabel label_gia_rac = new JLabel("Nhập giá rác");
        gbc.gridx=0;
        gbc.gridy=6;
        gbc.anchor = GridBagConstraints.WEST; // Căn lề trái
        panel.add(label_gia_rac,gbc);

        JTextField gia_rac = new JTextField(40);
        gia_rac.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa ô nhập liệu
        panel.add(gia_rac,gbc);

        label_don_vi = new JLabel("VNĐ");
        gbc.gridx = 2;
        gbc.gridy = 6;
        panel.add(label_don_vi,gbc);

        // Nhập chi phí khác
        //JLabel label_chi_phi_khac = new JLabel("Chi phí khác");
        //gbc.gridx=0;
        //gbc.gridy=7;
        //gbc.anchor = GridBagConstraints.WEST; // Căn lề trái
        //panel.add(label_chi_phi_khac,gbc);

        //JTextField chi_phi_khac = new JTextField(40);
        //chi_phi_khac.setPreferredSize(new Dimension(200, 25));
        //gbc.gridx = 1;
       // gbc.gridy = 7;
        //gbc.anchor = GridBagConstraints.CENTER; // Căn giữa ô nhập liệu
        //panel.add(chi_phi_khac,gbc);

       // label_don_vi = new JLabel("VNĐ");
       // gbc.gridx = 2;
       // gbc.gridy = 7;
       // panel.add(label_don_vi,gbc);





        // Lưu phòng
        JButton save_room = new JButton("Lưu phòng");
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa ô nhập liệu
        save_room.addActionListener(e->save_room_into_database(id_chutro));
        panel.add(save_room,gbc);

        // Nút quay lại
        JButton button_back = new JButton("Quay lại");
        gbc.gridx = 1;
        gbc.gridy = 9;
        button_back.addActionListener(e -> go_back_dashboardchutro(frame,id_chutro ));
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa ô nhập liệu
        panel.add(button_back, gbc);


        frame.add(panel, BorderLayout.CENTER);
        // Hiển thị giao diện
        frame.setLocationRelativeTo(null); // Canh giữa màn hình
        frame.setVisible(true);

    }
        public static void main(String[] args) {
            String id_chutro_infor = "CT001";
            SwingUtilities.invokeLater(() -> new ChutroCreateRoomsView(id_chutro_infor));
        }
    }