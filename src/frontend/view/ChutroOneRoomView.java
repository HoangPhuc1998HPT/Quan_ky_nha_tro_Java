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

    // Xử lý lại vùng hiển thị ==>
    // Tạo 1 frame chưa 1 bảng danh sách các phòng
    // Danh sách đó hiển thị các phòng chủ trọ sở hữu kèm cac chi tiết theo kèm



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChutroOneRoomView("P001"));

    }
}


