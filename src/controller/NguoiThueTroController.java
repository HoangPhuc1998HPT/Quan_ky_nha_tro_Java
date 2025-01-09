package controller;

import backend.model.NguoiThueTro;
import backend.model.Room;
import backend.model.Invoices;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class NguoiThueTroController {
    private String userId;
    private JFrame frame;

    public NguoiThueTroController(String userId, JFrame frame) {
        this.userId = userId;
        this.frame = frame;
    }

    public void showDashboard() throws SQLException {
        NguoiThueTro tenant = NguoiThueTro.getNguoiThueTroByUserId(userId);
        if (tenant == null) {
            JOptionPane.showMessageDialog(frame, "Không tìm thấy thông tin người thuê trọ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String roomId = Room.getTenantRoomId(tenant.getIdNguoiThue());
        if (roomId != null) {
            showRoomDetails(roomId);
        } else {
            showEmptyRooms();
        }
    }

    private void showRoomDetails(String roomId) throws SQLException {
        // Hiển thị nút "Xem thông tin phòng"
        JButton btnRoomInfo = new JButton("Xem thông tin phòng");
        //btnRoomInfo.addActionListener(e -> RoomController.showRoomInfo(frame, roomId));

        // Hiển thị table hóa đơn mới nhất
        Object[] invoiceData = Invoices.getInvoiceData(roomId);
        JTable invoiceTable = new JTable(new Object[][]{invoiceData}, new String[]{"ID", "Ngày", "Tổng tiền"});

        // Nút "Xem danh sách hóa đơn"
        JButton btnViewInvoices = new JButton("Xem danh sách hóa đơn");
        //btnViewInvoices.addActionListener(e -> InvoicesController.showInvoiceList(frame, roomId));

        frame.add(btnRoomInfo);
        frame.add(new JScrollPane(invoiceTable));
        frame.add(btnViewInvoices);
    }

    private void showEmptyRooms() {
        // Lấy danh sách phòng trống
        List<Object[]> emptyRooms = Room.getEmptyRooms();

        JTable roomTable = new JTable(emptyRooms.toArray(new Object[0][]), new String[]{"ID", "Tên phòng", "Chủ trọ", "Giá"});
        frame.add(new JScrollPane(roomTable));
    }
}
