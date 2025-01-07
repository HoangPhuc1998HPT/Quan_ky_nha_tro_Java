package backend.model;

import backend.connectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Invoices {
    // Hàm lấy thông tin hóa đơn mới nhất cho một phòng
    public static Object[] getInvoiceData(String idPhong) throws SQLException {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM HoaDon WHERE IDPhong = ? ORDER BY NgayXuatHoaDon DESC LIMIT 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idPhong);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Object[]{
                        rs.getInt("BillID"),
                        rs.getInt("SodienUsed"),
                        rs.getInt("SonuocUsed"),
                        rs.getDate("NgayXuatHoaDon"),
                        rs.getDouble("TienNha"),
                        rs.getDouble("ChiPhiKhac"),
                        rs.getDouble("TienRac"),
                        rs.getDouble("GiamGia"),
                        rs.getDouble("TongChiPhi")
                };
            }
        } catch (SQLException e) {
            throw new SQLException("Không thể lấy thông tin hóa đơn cho phòng ID: " + idPhong, e);
        }
        throw new SQLException("Không tìm thấy hóa đơn!");
    }

    // Hàm lấy giá điện cho một phòng
    public static double getGiaDien(String idPhong) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Giadien FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idPhong);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Giadien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Giá trị mặc định nếu không có dữ liệu
    }

    // Hàm lấy giá nước cho một phòng
    public static double getGiaNuoc(String idPhong) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Gianuoc FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idPhong);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Gianuoc");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Giá trị mặc định nếu không có dữ liệu
    }

    // Hàm tính tổng chi phí hóa đơn
    public static double calculateTongChiPhi(Object[] invoiceData, double giaDien, double giaNuoc) {
        double tongChiPhi = (int) invoiceData[1] * giaDien + // Tiền điện
                (int) invoiceData[2] * giaNuoc +             // Tiền nước
                (double) invoiceData[4] +                   // Tiền nhà
                (double) invoiceData[5] +                   // Chi phí khác
                (double) invoiceData[6] -                   // Tiền rác
                (double) invoiceData[7];                    // Giảm giá
        return tongChiPhi;
    }

    // Hàm tạo mới hóa đơn
    public static boolean createInvoice(String idPhong, double tienNha, double tienDien, double tienNuoc, double tienRac,
                                        double chiPhiKhac, double giamGia, double tongChiPhi, Date ngayXuatHoaDon) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO HoaDon (IDPhong, TienNha, TienDien, TienNuoc, TienRac, ChiPhiKhac, GiamGia, TongChiPhi, NgayXuatHoaDon) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idPhong);
            pstmt.setDouble(2, tienNha);
            pstmt.setDouble(3, tienDien);
            pstmt.setDouble(4, tienNuoc);
            pstmt.setDouble(5, tienRac);
            pstmt.setDouble(6, chiPhiKhac);
            pstmt.setDouble(7, giamGia);
            pstmt.setDouble(8, tongChiPhi);
            pstmt.setDate(9, new java.sql.Date(ngayXuatHoaDon.getTime()));

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
