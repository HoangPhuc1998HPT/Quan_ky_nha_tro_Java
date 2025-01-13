package frontend.view.rooms;

import backend.model.NguoiThueTro;
import backend.model.Room;
import controller.RoomController;

import javax.swing.*;
import java.awt.*;

public class RoomView {
    private JFrame frame;

    public RoomView(int idRoom, int idChutro) {
        Room room = Room.getRoomDetails(idRoom); // Lấy thông tin phòng từ backend


        frame = new JFrame("Phòng trọ: " + (room != null ? room.getName() : "Không xác định"));
        frame.setSize(400, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel tenantLabel = new JLabel("Tên Người Thuê: " + (Room.getNameNguoiThueFromIDRoom(idRoom)));
        tenantLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(tenantLabel, gbc);

        JButton btnUpdateTenant = new JButton("Cập nhật người thuê");
        btnUpdateTenant.addActionListener(e -> RoomController.goToUpdateNguoiThue(frame, idRoom));
        gbc.gridy = 1;
        frame.add(btnUpdateTenant, gbc);

        JButton btnUpdateRoom = new JButton("Cập nhật thông tin phòng");
        btnUpdateRoom.addActionListener(e -> RoomController.updateInforRoom(frame, idRoom, idChutro));
        gbc.gridy = 2;
        frame.add(btnUpdateRoom, gbc);

        JButton btnUpdateInvoice = new JButton("Cập nhật hóa đơn");
        btnUpdateInvoice.addActionListener(e -> RoomController.goToUpdateHoaDon(frame, idRoom, idChutro));
        gbc.gridy = 3;
        frame.add(btnUpdateInvoice, gbc);

        JButton btnExportInvoice = new JButton("Xuất hóa đơn");
        btnExportInvoice.addActionListener(e -> RoomController.goToXuatHoaDon(frame, idRoom, idChutro));
        gbc.gridy = 4;
        frame.add(btnExportInvoice, gbc);

        JButton btnDeleteRoom = new JButton("Xóa phòng");
        btnDeleteRoom.addActionListener(e -> RoomController.deletePhong(frame, idRoom));
        gbc.gridy = 5;
        frame.add(btnDeleteRoom, gbc);

        JButton btnBack = new JButton("Quay lại");
        btnBack.addActionListener(e -> frame.dispose());
        gbc.gridy = 6;
        frame.add(btnBack, gbc);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new RoomView(3,12);
    }
}
