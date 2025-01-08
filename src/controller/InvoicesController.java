package controller;

import backend.connectDatabase;
import backend.model.Invoices;
import frontend.view.Invoices.InvoiceCreateNewInvoice;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InvoicesController {
    private InvoiceCreateNewInvoice view;
    private String id_chutro;
    private String id_phong;

    public InvoicesController(InvoiceCreateNewInvoice view, String id_chutro, String id_phong) {
        this.view = view;
        this.id_chutro = id_chutro;
        this.id_phong = id_phong;
    }
    public void loadThongTinHoadon( String id_phong) {
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

    public void goBackToRoomList(JFrame frame, String id_chutro) {
        frame.dispose();
        // TODO: Điều hướng quay lại danh sách phòng
    }

    // Hàm xử lý cập nhật hóa đơn vào cơ sở dữ liệu
    public static void GoToUpdateDetailInvoice(
            String id_room,
            String id_chuTro,
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




    // hàm bên trên
}
