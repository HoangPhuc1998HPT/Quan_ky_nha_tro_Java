package backend.model;

import backend.connectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class InvoiceDetail {
    private int idRoom;
    private int oldElectricReading;
    private int oldWaterReading;
    private int useElectricReading;
    private int useWaterReading;
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
        this.useElectricReading = newElectricReading;
        this.useWaterReading = newWaterReading;
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

    public int getUseElectricReading() {
        return useElectricReading;
    }

    public void setUseElectricReading(int newElectricReading) {
        this.useElectricReading = newElectricReading;
    }

    public int getUseWaterReading() {
        return useWaterReading;
    }

    public void setUseWaterReading(int useWaterReading) {
        this.useWaterReading = useWaterReading;
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

    public static InvoiceDetail getInvoiceDetailForUpdate(int idRoom) {
        InvoiceDetail invoiceDetail = null;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT 
                ISNULL(pt.Sodienhientai,0) AS sodienthangtruoc, 
                ISNULL(pt.Sonuochientai,0) AS sonuocthangtruoc, 
                pt.GiaPhong AS Tiennha, 
                pt.Giadien AS Giadien, 
                pt.GIanuoc AS GIanuoc, 
                pt.Giarac AS Tienrac
            FROM TTPhongtro pt
            WHERE pt.IDPhong = ?
        """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRoom);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Trích xuất dữ liệu từ bảng TTPhongtro
                int oldElectric = rs.getInt("sodienthangtruoc");
                int oldWater = rs.getInt("sonuocthangtruoc");
                double rentPrice = rs.getDouble("Tiennha");
                double electricPrice = rs.getDouble("Giadien");
                double waterPrice = rs.getDouble("GIanuoc");
                double garbagePrice = rs.getDouble("Tienrac");

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
                        0, // Discount - mặc định
                        null // InvoiceDate - chưa có
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
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connectDatabase.DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Chuyển đổi ngày tháng về định dạng yyyy-MM-dd
            String formattedInvoiceDate = LocalDate.parse(detail.getInvoiceDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Cập nhật vào bảng CTHoaDon
            String sqlInsertCTHD = """
            INSERT INTO CTHoaDon (SodienUsed, SonuocUsed, DaysInMonth, Tiennha, Tienrac, Chiphiphatsinh, Giamgia, Ghichu, sodienthangtruoc, sonuocthangtruoc, ngaythutiendukien, IDPhong)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

            int daysInMonth = 0;
            if (lastInvoiceDate != null && !lastInvoiceDate.isEmpty()) {
                LocalDate currentInvoiceDate = LocalDate.parse(formattedInvoiceDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate previousInvoiceDate = LocalDate.parse(lastInvoiceDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                daysInMonth = (int) ChronoUnit.DAYS.between(previousInvoiceDate, currentInvoiceDate);
            }

            pstmt = conn.prepareStatement(sqlInsertCTHD);
            pstmt.setInt(1, detail.getUseElectricReading() - detail.getOldElectricReading());
            pstmt.setInt(2, detail.getUseWaterReading() - detail.getOldWaterReading());
            pstmt.setInt(3, daysInMonth);
            pstmt.setDouble(4, detail.getRentPrice());
            pstmt.setDouble(5, detail.getGarbagePrice());
            pstmt.setDouble(6, detail.getDiscount());
            pstmt.setDouble(7, detail.getDiscount());
            pstmt.setString(8, "Cập nhật hóa đơn từ hệ thống");
            pstmt.setInt(9, detail.getOldElectricReading());
            pstmt.setInt(10, detail.getOldWaterReading());
            pstmt.setString(11, formattedInvoiceDate); // Sử dụng ngày đã định dạng
            pstmt.setInt(12, detail.getIdRoom());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                // Cập nhật số điện/nước hiện tại vào bảng TTPhongtro
                String sqlUpdateTTPhongtro = "UPDATE TTPhongtro SET Sodienhientai = ?, Sonuochientai = ? WHERE IDPhong = ?";
                pstmt = conn.prepareStatement(sqlUpdateTTPhongtro);
                pstmt.setInt(1, detail.getUseElectricReading());
                pstmt.setInt(2, detail.getUseWaterReading());
                pstmt.setInt(3, detail.getIdRoom());

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    conn.commit(); // Commit transaction nếu cả 2 thành công
                    return true;
                }
            }

            conn.rollback(); // Rollback nếu bất kỳ thao tác nào thất bại
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


}
