package backend.model;

import backend.connectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InvoiceDetail {
    private double rentPrice;
    private double electricPrice;
    private double waterPrice;
    private double garbagePrice;

    public InvoiceDetail(double rentPrice, double electricPrice, double waterPrice, double garbagePrice) {
        this.rentPrice = rentPrice;
        this.electricPrice = electricPrice;
        this.waterPrice = waterPrice;
        this.garbagePrice = garbagePrice;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public double getElectricPrice() {
        return electricPrice;
    }

    public void setElectricPrice(double electricPrice) {
        this.electricPrice = electricPrice;
    }

    public double getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(double waterPrice) {
        this.waterPrice = waterPrice;
    }

    public double getGarbagePrice() {
        return garbagePrice;
    }

    public void setGarbagePrice(double garbagePrice) {
        this.garbagePrice = garbagePrice;
    }

    // Hàm lấy thông tin chi tiết hóa đơn từ database
    public static InvoiceDetail getInvoiceDetail(String idRoom) {
        InvoiceDetail invoiceDetail = null;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT GiaPhong, Giadien, GIanuoc, Tienrac FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idRoom);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double rentPrice = rs.getDouble("GiaPhong");
                double electricPrice = rs.getDouble("Giadien");
                double waterPrice = rs.getDouble("GIanuoc");
                double garbagePrice = rs.getDouble("Tienrac");

                invoiceDetail = new InvoiceDetail(rentPrice, electricPrice, waterPrice, garbagePrice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceDetail;
    }
    public static String getRoomName(String id_room) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT TenPhong FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_room);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("TenPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không xác định";
    }

    public static String getTenantName(String id_room) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT nt.Hoten FROM TTPhongtro pt JOIN NguoiThueTro nt ON pt.IDPhong = nt.UserID WHERE pt.IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_room);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Hoten");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không có";
    }

    public static String getStartDate(String id_room) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Ngaybatdauthue FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_room);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Ngaybatdauthue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Chưa có dữ liệu";
    }

    public static int getOldElectricReading(String id_room) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Sodienhientai FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_room);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Sodienhientai");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Giá trị mặc định nếu không có dữ liệu
    }

    public static int getOldWaterReading(String id_room) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Sonuochientai FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_room);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Sonuochientai");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Giá trị mặc định nếu không có dữ liệu
    }

    public static String getLastPaymentDate(String id_room) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Ngaythutienthangtruoc FROM HoaDon WHERE IDPhong = ? ORDER BY Ngayxuathoadon DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_room);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Ngaythutienthangtruoc");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Chưa có";
    }
}
