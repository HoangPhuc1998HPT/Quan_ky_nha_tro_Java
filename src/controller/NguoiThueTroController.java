package controller;

import backend.model.*;
import frontend.view.Invoices.InvoiceFormView;
import frontend.view.nguoithuetro.UpdateNguoiThueTroInforView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class NguoiThueTroController {
    private int userId;
    private JFrame frame;

    public NguoiThueTroController(int userId, JFrame frame) {
        this.userId = userId;
        this.frame = frame;
    }

    public static void goToOpenInvoiceView(int idNguoiThue, int idPhong, int idChutro, int invoiceId) {
        try {
            // Lấy thông tin người thuê trọ
            NguoiThueTro tenant = NguoiThueTro.getNguoiThueTroByIDnguoithue(idNguoiThue);
            if (tenant == null) {
                throw new IllegalArgumentException("Không tìm thấy thông tin người thuê trọ.");
            }

            // Lấy thông tin chủ trọ
            Chutro chutro = Chutro.getChutrobyChutroID(idChutro);
            if (chutro == null) {
                throw new IllegalArgumentException("Không tìm thấy thông tin chủ trọ.");
            }

            // Lấy thông tin phòng
            Room room = Room.getRoomDetails(idPhong);
            if (room == null) {
                throw new IllegalArgumentException("Không tìm thấy thông tin phòng.");
            }

            // Lấy thông tin hóa đơn
            Object[] invoiceDetail = InvoiceDetail.getInvoiceDetailByIdRoom(idPhong);
            if (invoiceDetail == null) {
                throw new IllegalArgumentException("Không tìm thấy thông tin hóa đơn.");
            }

            // Mở form hóa đơn
            new InvoiceFormView(chutro, tenant, invoiceDetail, room);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void goToUpdateNguoiThueTroInforView(int userId) {
        // Lấy thông tin người thuê từ UserID
        NguoiThueTro tenant = NguoiThueTro.getNguoiThueTroByUserId(userId);
        if (tenant != null) {
            new UpdateNguoiThueTroInforView(userId, tenant.getFullName(), tenant.getPhone(), tenant.getCCCD());
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin người thuê trọ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }




    public void showDashboard() throws SQLException {
        NguoiThueTro tenant = NguoiThueTro.getNguoiThueTroByUserId(userId);
        if (tenant == null) {
            JOptionPane.showMessageDialog(frame, "Không tìm thấy thông tin người thuê trọ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int roomId = Room.getTenantRoomId(tenant.getIdNguoiThue());
        if (roomId != 0) {
            showRoomDetails(roomId);
        } else {
            showEmptyRooms();
        }
    }

    private void showRoomDetails(int roomId) throws SQLException {
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
