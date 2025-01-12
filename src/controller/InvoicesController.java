package controller;

import backend.connectDatabase;
import backend.model.Chutro;
import backend.model.Invoices;
import backend.model.NguoiThueTro;
import backend.model.Room;
import frontend.view.Invoices.InvoiceCreateNewInvoice;
import frontend.view.Invoices.InvoiceFormView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class InvoicesController {
    private InvoiceCreateNewInvoice view;
    private int id_chutro;
    private int id_phong;

    public InvoicesController(InvoiceCreateNewInvoice view, int id_chutro, int id_phong) {
        this.view = view;
        this.id_chutro = id_chutro;
        this.id_phong = id_phong;
    }





    public void loadThongTinHoadon( int id_phong) {
        try {
            Object[] hoadonData = Invoices.getInvoiceData(id_phong);
            double giadien = Invoices.getGiaDien(id_phong);
            double gianuoc = Invoices.getGiaNuoc(id_phong);
            double tongCong = Invoices.calculateTongChiPhi(hoadonData, giadien, gianuoc);

            Object[][] data = {
                    {"Tiền nhà", 1, hoadonData[4], hoadonData[4]},
                    {"Tiền điện", hoadonData[1], giadien, (int) hoadonData[1] * giadien},
                    {"Tiền nước", hoadonData[2], gianuoc, (int) hoadonData[2] * gianuoc},
                    {"Tiền rác", 1, hoadonData[6], hoadonData[6]},
                    {"Chi phí khác", 1, hoadonData[5], hoadonData[5]},
                    {"Giảm giá", 1, hoadonData[7], hoadonData[7]}
            };

            view.updateView(hoadonData[0].toString(), data, tongCong);
        } catch (Exception e) {
            view.showErrorMessage("Không thể tải thông tin hóa đơn!");
            e.printStackTrace();
        }
    }

    public void goToNhapHoadon() {
        view.showSuccessMessage("Hóa đơn đã được xuất thành công!");
        // TODO: Xử lý xuất hóa đơn logic
    }

    public void goBackToRoomList(JFrame frame, int id_chutro) {
        frame.dispose();
        // TODO: Điều hướng quay lại danh sách phòng
    }

    // Hàm xử lý cập nhật hóa đơn vào cơ sở dữ liệu
    public static void GoToUpdateDetailInvoice(
            String id_room,
            int id_chutro,
            String id_nguoiThueTro,
            double tienNha,
            double tienDien,
            double tienNuoc,
            double tienRac,
            double chiPhiKhac,
            double giamGia
    ) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                INSERT INTO HoaDon (Tiennha, Tiendien, Tiennuoc, Tienrac, Chiphikhac, Giamgia, Tongchiphi, Ngayxuathoadon)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, GETDATE())
                Join 
                Where 
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(2, tienNha);
            pstmt.setDouble(3, tienDien);
            pstmt.setDouble(4, tienNuoc);
            pstmt.setDouble(5, tienRac);
            pstmt.setDouble(6, chiPhiKhac);
            pstmt.setDouble(7, giamGia);
            pstmt.setDouble(8, tienNha + tienDien + tienNuoc + tienRac + chiPhiKhac - giamGia); // Tính tổng chi phí

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hóa đơn đã được cập nhật thành công!");
                JOptionPane.showMessageDialog(null, "Hóa đơn đã được cập nhật thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Không thể cập nhật hóa đơn!");
                JOptionPane.showMessageDialog(null, "Không thể cập nhật hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Đang hiệu chỉnh
    public static int getTotalInvoices(int idChutro) {
        return Invoices.getTotalInvoices(idChutro);
    }

    public static int getPaidInvoices(int idChutro) {
        return Invoices.getPaidInvoices(idChutro);
    }

    public static int getUnpaidInvoices(int idChutro) {
        return Invoices.getUnpaidInvoices(idChutro);
    }

    public static double getTotalValue(int idChutro) {
        return Invoices.getTotalValue(idChutro);
    }

    public static double getUnpaidValue(int idChutro) {
        return Invoices.getUnpaidValue(idChutro);
    }

    public static double getPaidRate(int idChutro) {
        int total = getTotalInvoices(idChutro);
        int paid = getPaidInvoices(idChutro);
        return total == 0 ? 0 : ((double) paid / total) * 100;
    }

    //TODO: phần bên dưới có thể có lỗi ==> đang lỗi trưa fix

    public static void openInvoiceDetails(int idChutro, int idNguoiThueTro, int idhoadon, int idRoom) {
        // Lấy thông tin các đối tượng liên quan
        Chutro chutro = Chutro.getChutrobyChutroID(idChutro);
        NguoiThueTro nguoithuetro = NguoiThueTro.getTenantById(idNguoiThueTro);
        Room room = Room.getRoomById(idRoom);
        Invoices invoice = Invoices.getInvoiceDetails(idhoadon);

        if (chutro != null && nguoithuetro != null && room != null && invoice != null) {
            // Mở giao diện chi tiết hóa đơn
            new InvoiceFormView(chutro, nguoithuetro, invoice, room);
        } else {
            JOptionPane.showMessageDialog(null, "Không thể tải thông tin chi tiết hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }



    // hàm bên trên
}
