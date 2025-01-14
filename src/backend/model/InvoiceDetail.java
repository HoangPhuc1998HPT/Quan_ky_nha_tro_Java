package backend.model;

import backend.connectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static javax.swing.UIManager.getInt;

public class InvoiceDetail {
    private int idCTHD;
    private int idRoom;
    private int useElectricReading;
    private int useWaterReading;
    private int dayInMonth;
    private double rentPrice;
    private String invoiceDate;
    private int oldElectricReading;
    private int oldWaterReading;
    private double additionalCost;
    private double garbagePrice;
    private double discount;


    // Constructor đầy đủ
    public InvoiceDetail(int idRoom,int idCTHD, int oldElectricReading, int oldWaterReading, int newElectricReading, int newWaterReading,
                         double rentPrice, double garbagePrice,double additionalCost, double discount, String invoiceDate) {
        this.idRoom = idRoom;
        this.idCTHD = idCTHD;
        this.oldElectricReading = oldElectricReading;
        this.oldWaterReading = oldWaterReading;
        this.useElectricReading = newElectricReading;
        this.useWaterReading = newWaterReading;
        this.rentPrice = rentPrice;
        this.additionalCost = additionalCost;
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
    // Getter và Setter cho idCTD
    public int getidCTHD() {
        return idCTHD;
    }

    public void setIdCTD(int idCTD) {
        this.idCTHD = idCTD;
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

    public int getDayInMonth(){
        return dayInMonth;
    }

    public void setDayInMonth(int dayInMonth) {
        this.dayInMonth = dayInMonth;
    }
    public void setAdditionalCost(double additionalCost){
        this.additionalCost = additionalCost;
    }
    public double getAdditionalCost() { return additionalCost; }


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
                chd.idCTHD,
                ISNULL(pt.Sodienhientai,0) AS sodienthangtruoc, 
                ISNULL(pt.Sonuochientai,0) AS sonuocthangtruoc, 
                pt.GiaPhong AS Tiennha, 
                pt.Giadien AS Giadien, 
                pt.GIanuoc AS GIanuoc, 
                pt.Giarac AS Tienrac
            FROM TTPhongtro pt
            LEFT JOIN CTHoaDon chd ON pt.IDPhong = chd.IDPhong
            WHERE pt.IDPhong = ?
        """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRoom);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Trích xuất dữ liệu từ bảng TTPhongtro
                int idCTHD = rs.getInt("idCTHD");
                int oldElectric = rs.getInt("sodienthangtruoc");
                int oldWater = rs.getInt("sonuocthangtruoc");
                double rentPrice = rs.getDouble("Tiennha");
                double electricPrice = rs.getDouble("Giadien");
                double waterPrice = rs.getDouble("GIanuoc");
                double garbagePrice = rs.getDouble("Tienrac");

                // Tạo đối tượng InvoiceDetail với thông tin đầy đủ
                invoiceDetail = new InvoiceDetail(
                        idRoom,
                        idCTHD,
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
            INSERT INTO CTHoaDon (SodienUsed, SonuocUsed, DaysInMonth, Tiennha, Tienrac, Chiphiphatsinh, Giamgia, sodienthangtruoc, sonuocthangtruoc, ngaythutiendukien, IDPhong)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

            int daysInMonth = 0;
            LocalDate currentInvoiceDate = LocalDate.parse(formattedInvoiceDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // Cần kiểm tra lại dayInMonth: số ngày người thuê ở tới khi xuất hóa đơn nếu chưa có hóa dodnwd naào
            // Nếu người thuê đã có hóa đơn, tính từ lần xuất hóa đơn trước
            if (lastInvoiceDate == null || lastInvoiceDate.isEmpty()) {
                // Lấy ngày bắt đầu thuê từ database
                String startDate = InvoiceDetail.getStartDate(detail.getIdRoom());
                LocalDate startRentalDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                daysInMonth = (int) ChronoUnit.DAYS.between(startRentalDate, currentInvoiceDate);
            } else {
                LocalDate previousInvoiceDate = LocalDate.parse(lastInvoiceDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                daysInMonth = (int) ChronoUnit.DAYS.between(previousInvoiceDate, currentInvoiceDate);
            }

            pstmt = conn.prepareStatement(sqlInsertCTHD);
            pstmt.setInt(1, detail.getUseElectricReading());
            pstmt.setInt(2, detail.getUseWaterReading());
            pstmt.setInt(3, daysInMonth);//TODO: cần xem lại
            pstmt.setDouble(4, detail.getRentPrice());
            pstmt.setDouble(5, detail.getGarbagePrice());
            pstmt.setDouble(6, detail.getAdditionalCost());
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




    public static InvoiceDetail getInvoiceDetailByIdRoom(int idPhong) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn SQL để lấy thông tin chi tiết hóa đơn từ CTHoaDon hoặc TTPhongtro
            String sql = """
            SELECT TOP 1
                chd.idCTHD,
                ISNULL(pt.Sodienhientai, 0) AS sodienthangtruoc,
                ISNULL(pt.Sonuochientai, 0) AS sonuocthangtruoc,
                pt.GiaPhong AS tiennha,
                pt.Giadien AS giadien,
                pt.Gianuoc AS gianuoc,
                pt.Giarac AS Tienrac,
                ISNULL(chd.Giamgia, 0) AS giamgia,
                ISNULL(chd.Ngaythutiendukien, GETDATE()) AS ngayhoadon
            FROM TTPhongtro pt
            LEFT JOIN CTHoaDon chd ON pt.IDPhong = chd.IDPhong
            WHERE pt.IDPhong = ?
            ORDER BY chd.Ngaythutiendukien DESC
        """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPhong);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Tạo đối tượng InvoiceDetail từ kết quả truy vấn
                return new InvoiceDetail(
                        idPhong,
                        rs.getInt("idCTHD"),// ID phòng
                        rs.getInt("sodienthangtruoc"),          // Số điện cũ
                        rs.getInt("sonuocthangtruoc"),          // Số nước cũ
                        0,                                      // Số điện mới (mặc định)
                        0,                                      // Số nước mới (mặc định)
                        rs.getDouble("tiennha"),                // Tiền nhà
                        rs.getDouble("giadien"),                // Giá điện
                        rs.getDouble("gianuoc"),                // Giá nước
                        rs.getDouble("Tienrac"),                // Giá rác
                        rs.getDouble("giamgia"),                // Giảm giá
                        rs.getString("ngayhoadon")              // Ngày hóa đơn
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy dữ liệu
    }


}
