package frontend.view;

import backend.model.NguoiThueTro;
import controller.DashboardChutroController;
import controller.RoomController;

import javax.swing.*;
import java.awt.*;


import static controller.DashboardChutroController.get_room_list;

import static controller.DashboardChutroController.go_back_dashboardchutro;
import static controller.RoomController.deletePhong;

// chưa hoạt động được

public class ChutroOneRoomView  {
    private JFrame frame;
    private JPanel roomFrame;
    private String id_chutro;
    private DashboardChutroController controller;

    public ChutroOneRoomView(String id_chutro) {
        this.id_chutro = id_chutro;
        //this.controller = new DashboardChutroController();

        // Tạo frame
        JFrame frame = new JFrame("Danh sách phòng trọ");
        frame.setSize(1800, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        // Tạo Canvas (Scroll Pane)
        JScrollPane scrollPane = new JScrollPane();
        frame.add(scrollPane, BorderLayout.CENTER);

        // Tạo JPanel bên trong scroll pane
        roomFrame = new JPanel();
        roomFrame.setLayout(new GridLayout());;
        scrollPane.setViewportView(roomFrame);

        // Lấy danh sách phòng qua controller
        // Đoạn code lấy danh sách phòng sẽ fix lại sau khi có được truy vấn từ backend

        get_room_list(id_chutro); // get information của phòng để kích hoạt displayroom

        frame.setVisible(true);
    }
    // tách room riêng ra 1 class

    public void displayRooms(String id_room) {
        // Lấy tên người thuê

        String tenantName = NguoiThueTro.getTenNguoiThueInRoom(id_room);
        JLabel tenantLabel = new JLabel("Tên Người Thuê: " + (tenantName != null ? tenantName : "Không có") + " - Phòng: " + "?? tên phòng ??");
        tenantLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        tenantLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(tenantLabel);

        // Nút cập nhật người thuê
        JButton btnUpdateTenant = new JButton("Cập nhật người thuê");
        btnUpdateTenant.addActionListener(e -> RoomController.goToUpdateNguoiThue(frame,id_room));
        frame.add(btnUpdateTenant);

        // Nút cập nhật thông tin phòng
        JButton btnUpdateRoom = new JButton("Cập nhật thông tin phòng");
        btnUpdateRoom.addActionListener(e -> RoomController.updateInforRoom(frame,id_room));
        frame.add(btnUpdateRoom);

        // Nút cập nhật hóa đơn
        JButton btnUpdateInvoice = new JButton("Cập nhật hóa đơn");
        btnUpdateInvoice.addActionListener(e -> RoomController.goToUpdateHoaDon(frame,id_room));
        frame.add(btnUpdateInvoice);

        // Nút xuất hóa đơn
        JButton btnExportInvoice = new JButton("Xuất hóa đơn");
        btnExportInvoice.addActionListener(e -> RoomController.goToXuatHoaDon(frame,id_room));
        frame.add(btnExportInvoice);

        // Nút xóa phòng
        JButton btnDeleteRoom = new JButton("Xóa phòng");
        btnDeleteRoom.addActionListener(e -> deletePhong(frame,id_room));
        frame.add(btnDeleteRoom);

        // Nút quay lại
        JButton btnBack = new JButton("Quay lại");
        btnBack.addActionListener(e -> go_back_dashboardchutro(new JFrame() ,id_chutro));
        frame.add(btnBack);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChutroOneRoomView("P001"));

    }
}


