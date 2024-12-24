package frontend.view;

import backend.model.NguoiThueTro;
import controller.RoomController;

import javax.swing.*;
import java.awt.*;

import static controller.DashboardChutroController.go_back_dashboardchutro;
import static controller.RoomController.deletePhong;

// class này xử lý giao diện của 1 phòng


public class RoomView {
    // Room view phục vụ cho rooms view của chủ trọ
    private JFrame frame;
    private String id_room;
    private String id_chutro;

    public RoomView(String id_room, String id_chutro, JPanel roomFrame){
        this.id_room = id_room;
        this.id_chutro = id_chutro;
        // lấy thông tin người thuê --> xem xét xóa dòng bên dưới
        String tenantName = NguoiThueTro.getTenNguoiThueInRoom(id_room);
        //
        // Lấy thông tin phòng
        Object[] room_infor = RoomController.getThongTinPhong(id_room, id_chutro);
        // Hàm trên trả về 1 mảng thông tin của phong ==> trích xuất thông tin này để xử lý các nội dung của phòng bên dưới

        frame = new JFrame("Phòng trọ số : ....");
        frame.setSize(400,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Tạo canvas (scroll Panel)
        //JScrollPane scrollPane = new JScrollPane();
        //frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel tenantLabel = new JLabel("Tên Người Thuê: " + (tenantName != null ? tenantName : "Không có"));
        tenantLabel.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(tenantLabel, gbc);

        // Nút cập nhật người thuê
        JButton btnUpdateTenant = new JButton("Cập nhật người thuê");
        btnUpdateTenant.addActionListener(e -> RoomController.goToUpdateNguoiThue(frame,id_room));
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(btnUpdateTenant,gbc);

        // Nút cập nhật thông tin phòng
        JButton btnUpdateRoom = new JButton("Cập nhật thông tin phòng");
        btnUpdateRoom.addActionListener(e -> {
            RoomController.updateInforRoom(id_room, id_chutro);
            //JOptionPane.showMessageDialog(frame, "Thông tin phòng đã được cập nhật!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(btnUpdateRoom,gbc);

        // Nút cập nhật hóa đơn
        JButton btnUpdateInvoice = new JButton("Cập nhật hóa đơn");
        btnUpdateInvoice.addActionListener(e -> RoomController.goToUpdateHoaDon(frame,id_room));
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(btnUpdateInvoice,gbc);

        // Nút xuất hóa đơn
        JButton btnExportInvoice = new JButton("Xuất hóa đơn");
        btnExportInvoice.addActionListener(e -> RoomController.goToXuatHoaDon(frame,id_room));
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(btnExportInvoice,gbc);

        // Nút xóa phòng
        JButton btnDeleteRoom = new JButton("Xóa phòng");
        btnDeleteRoom.addActionListener(e -> deletePhong(frame,id_room));
        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(btnDeleteRoom,gbc);

        //**********************************
        // Nút quay lại
        JButton btnBack = new JButton("Quay lại");
        btnBack.addActionListener(e -> go_back_dashboardchutro(new JFrame() ,id_chutro));
        // back sẽ quay lại danh sách phòng ==> Tạm thời ể như vầy trước
        btnBack.getColorModel();
        gbc.gridx = 0;
        gbc.gridy = 7;
        frame.add(btnBack,gbc);

        frame.setVisible(true);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JPanel roomPanel = new JPanel();
            new RoomView("R001", "CT002", roomPanel);
        });
    }
}