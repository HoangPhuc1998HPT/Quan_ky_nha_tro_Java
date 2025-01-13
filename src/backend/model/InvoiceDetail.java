package backend.model;

import backend.connectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class InvoiceDetail {
    private int idRoom;
    private int oldElectricReading;
    private int oldWaterReading;
    private int newElectricReading;
    private int newWaterReading;
    private double rentPrice;
    private double electricPrice;
    private double waterPrice;
    private double garbagePrice;
    private double discount;
    private String invoiceDate;

    // Constructor đầy đủ
    public InvoiceDetail(int idRoom, int oldElectricReading, int oldWaterReading, int newElectricReading, int newWaterReading,
                         double rentPrice, double electricPrice, double waterPrice, double garbagePrice, double discount, String invoiceDate) {
        this.idRoom = idRoom;
        this.oldElectricReading = oldElectricReading;
        this.oldWaterReading = oldWaterReading;
        this.newElectricReading = newElectricReading;
        this.newWaterReading = newWaterReading;
        this.rentPrice = rentPrice;
        this.electricPrice = electricPrice;
        this.waterPrice = waterPrice;
        this.garbagePrice = garbagePrice;
        this.discount = discount;
        this.invoiceDate = invoiceDate;
    }

    // Getter và Setter

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getOldElectricReading() {
        return oldElectricReading;
    }

    public void setOldElectricReading(int oldElectricReading) {
        this.oldElectricReading = oldElectricReading;
    }

    public int getOldWaterReading() {
        return oldWaterReading;
    }

    public void setOldWaterReading(int oldWaterReading) {
        this.oldWaterReading = oldWaterReading;
    }

    public int getNewElectricReading() {
        return newElectricReading;
    }

    public void setNewElectricReading(int newElectricReading) {
        this.newElectricReading = newElectricReading;
    }

    public int getNewWaterReading() {
        return newWaterReading;
    }

    public void setNewWaterReading(int newWaterReading) {
        this.newWaterReading = newWaterReading;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    // phía trên OK


    // Hàm lấy thông tin chi tiết hóa đơn từ database

    public static InvoiceDetail getInvoiceDetail(int idRoom) {
        InvoiceDetail invoiceDetail = null;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT 
                cthd.SodienUsed, cthd.SonuocUsed, cthd.DaysInMonth, cthd.Tiennha, 
                cthd.Tienrac, cthd.Chiphiphatsinh, cthd.Giamgia, cthd.Ghichu,
                cthd.sodienthangtruoc, cthd.sonuocthangtruoc, cthd.ngaythutiendukien,
                cthd.IDPhong, pt.GiaPhong, pt.Giadien, pt.GIanuoc, pt.Giarac
            FROM CTHoaDon cthd
            JOIN TTPhongtro pt ON cthd.IDPhong = pt.IDPhong
            WHERE cthd.IDPhong = ?
            ORDER BY cthd.ngaythutiendukien DESC
            LIMIT 1
        """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRoom);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Trích xuất dữ liệu từ bảng CTHoaDon
                int oldElectric = rs.getInt("sodienthangtruoc");
                int oldWater = rs.getInt("sonuocthangtruoc");
                double rentPrice = rs.getDouble("Tiennha");
                double electricPrice = rs.getDouble("Giadien");
                double waterPrice = rs.getDouble("GIanuoc");
                double garbagePrice = rs.getDouble("Tienrac");
                double discount = rs.getDouble("Giamgia");
                String invoiceDate = rs.getString("ngaythutiendukien");

                // Tạo đối tượng InvoiceDetail với thông tin đầy đủ
                invoiceDetail = new InvoiceDetail(
                        idRoom,
                        oldElectric,
                        oldWater,
                        0, // NewElectricReading - mặc định
                        0, // NewWaterReading - mặc định
                        rentPrice,
                        electricPrice,
                        waterPrice,
                        garbagePrice,
                        discount,
                        invoiceDate
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceDetail;
    }



    // Hàm lấy tên phòng
    public static String getRoomName(int idRoom) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT TenPhong FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRoom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("TenPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không xác định";
    }

    // Hàm lấy tên người thuê
    public static String getTenantName(int idRoom) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                SELECT nt.Hoten 
                FROM TTPhongtro pt 
                JOIN NguoiThueTro nt ON pt.IDNguoiThue = nt.IDNguoiThue 
                WHERE pt.IDPhong = ?
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRoom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Hoten");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không có";
    }

    public static String getStartDate(int idRoom) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT Ngaybatdauthue 
            FROM NguoiThueTro 
            WHERE IDnguoithue = (
                SELECT IDNguoiThue FROM TTPhongtro WHERE IDPhong = ?
            )
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRoom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Ngaybatdauthue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Chưa có dữ liệu";
    }


    public static int getOldElectricReading(int id_room) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Sodienhientai FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_room);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Sodienhientai");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Giá trị mặc định nếu không có dữ liệu
    }

    public static int getOldWaterReading(int id_room) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Sonuochientai FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_room);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Sonuochientai");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Giá trị mặc định nếu không có dữ liệu
    }

    // Hàm lấy ngày hóa đơn gần nhất
    public static String getLastPaymentDate(int idRoom) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT TOP 1 Ngayxuathoadon FROM HoaDon WHERE IDPhong = ? ORDER BY Ngayxuathoadon DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRoom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Ngayxuathoadon");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Chưa có";
    }

    // 11-01-2025

    // Hàm cập nhật hóa đơn mới vào bảng CTHoaDon
    // Hàm cập nhật hóa đơn mới vào bảng CTHoaDon
    public static boolean updateInvoiceDetail(InvoiceDetail detail, String lastInvoiceDate) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            INSERT INTO CTHoaDon (SodienUsed, SonuocUsed, DaysInMonth, Tiennha, Tienrac, Chiphiphatsinh, Giamgia, Ghichu, sodienthangtruoc, sonuocthangtruoc, ngaythutiendukien, IDPhong)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

            // Tính toán DaysInMonth
            int daysInMonth = 0;
            if (lastInvoiceDate != null && !lastInvoiceDate.isEmpty()) {
                LocalDate currentInvoiceDate = LocalDate.parse(detail.getInvoiceDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate previousInvoiceDate = LocalDate.parse(lastInvoiceDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                daysInMonth = (int) ChronoUnit.DAYS.between(previousInvoiceDate, currentInvoiceDate);
            }

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, detail.getNewElectricReading() - detail.getOldElectricReading()); // SodienUsed
            pstmt.setInt(2, detail.getNewWaterReading() - detail.getOldWaterReading()); // SonuocUsed
            pstmt.setInt(3, daysInMonth); // DaysInMonth
            pstmt.setDouble(4, detail.getRentPrice()); // Tiennha
            pstmt.setDouble(5, detail.getGarbagePrice()); // Tienrac
            pstmt.setDouble(6, detail.getDiscount()); // Chiphiphatsinh
            pstmt.setDouble(7, detail.getDiscount()); // Giamgia
            pstmt.setString(8, "Cập nhật hóa đơn từ hệ thống"); // Ghichu
            pstmt.setInt(9, detail.getOldElectricReading()); // sodienthangtruoc
            pstmt.setInt(10, detail.getOldWaterReading()); // sonuocthangtruoc
            pstmt.setString(11, detail.getInvoiceDate()); // ngaythutiendukien
            pstmt.setInt(12, detail.getIdRoom()); // IDPhong

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }







}
