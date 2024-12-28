package controller;

import backend.connectDatabase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InvoicesController {

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
}
