package backend.model;

import backend.connectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoices {
    // Hàm lấy thông tin hóa đơn mới nhất cho một phòng
    public static Object[] getInvoiceData(int idPhong) throws SQLException {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM HoaDon WHERE IDPhong = ? ORDER BY NgayXuatHoaDon DESC LIMIT 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPhong);
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
    public static double getGiaDien(int idPhong) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Giadien FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPhong);
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
    public static double getGiaNuoc(int idPhong) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Gianuoc FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPhong);
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
    public static boolean createInvoice(int idPhong, double tienNha, double tienDien, double tienNuoc, double tienRac,
                                        double chiPhiKhac, double giamGia, double tongChiPhi, Date ngayXuatHoaDon) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO HoaDon (IDPhong, TienNha, TienDien, TienNuoc, TienRac, ChiPhiKhac, GiamGia, TongChiPhi, NgayXuatHoaDon) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPhong);
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
    // TODO: Invoice list view đã tạo, Hiếu kiểm tra lại logic xem truy xuất dữ liệu cho Invoices.InvoiceListsView  nha
    public static List<Object[]> getInvoiceList(int idChutro) {
        List<Object[]> invoices = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                SELECT 
                    ROW_NUMBER() OVER (ORDER BY hd.NgayXuatHoaDon DESC) AS STT,
                    pt.TenPhong,
                    nt.Hoten AS TenNguoiThue,
                    hd.TongChiPhi,
                    hd.NgayXuatHoaDon,
                    CASE WHEN hd.TongChiPhi > 0 THEN 'Chưa Thanh Toán' ELSE 'Đã Thanh Toán' END AS TinhTrang
                FROM HoaDon hd
                JOIN TTPhongtro pt ON hd.IDPhong = pt.IDPhong
                LEFT JOIN NguoiThueTro nt ON pt.IDNguoiThue = nt.IDNguoiThue
                WHERE pt.IDChutro = ?
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idChutro);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                invoices.add(new Object[]{
                        rs.getInt("STT"),
                        rs.getString("TenPhong"),
                        rs.getString("TenNguoiThue"),
                        rs.getDouble("TongChiPhi"),
                        rs.getDate("NgayXuatHoaDon"),
                        rs.getString("TinhTrang")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public static int getTotalInvoices(int idChutro) {

        return 0;
    }

    public static int getPaidInvoices(int idChutro) {

        return 0;
    }

    public static int getUnpaidInvoices(int idChutro) {
        return 0;
    }

    public static double getTotalValue(int idChutro) {
        return 0;
    }

    public static double getUnpaidValue(int idChutro) {
        return 0;
    }

    public static List<Object[]> getInvoicesByTenantId(int userId) {
        List<Object[]> invoices = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT hd.NgayXuatHoaDon, 
                   hd.TongChiPhi, 
                   CASE WHEN hd.TongChiPhi > 0 THEN 0 ELSE 1 END AS DaThanhToan, 
                   hd.BillID
            FROM HoaDon hd
            JOIN TTPhongtro pt ON hd.IDPhong = pt.IDPhong
            WHERE pt.IDNguoiThue = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                invoices.add(new Object[]{
                        rs.getDate("NgayXuatHoaDon"), // Ngày Xuất Hóa Đơn
                        rs.getDouble("TongChiPhi"),  // Tổng Giá Trị
                        rs.getBoolean("DaThanhToan"), // Trạng Thái
                        rs.getInt("BillID")       // ID Hóa Đơn
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoices;
    }


    public static Invoices getInvoiceDetails(int invoiceId) {
        //TODO: Get thông tin invoicID nhà HIếu
        // Truy xuất tất cả thông tin cần để hiển thị lên HÓa đơn: InvoiceFormView.java

        Invoices invoices = new Invoices();
        return invoices;
    }
}
