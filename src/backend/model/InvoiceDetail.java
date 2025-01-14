package backend.model;

import backend.connectDatabase;

import java.sql.*;
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
    private Date invoiceDate;
    private int oldElectricReading;
    private int oldWaterReading;
    private double additionalCost;
    private double garbagePrice;
    private double discount;


    // Constructor đầy đủ
    public InvoiceDetail(int idRoom,int idCTHD, int oldElectricReading, int oldWaterReading, int useElectricReading, int useWaterReading,
                         int dayInMonth,double rentPrice, double garbagePrice,double additionalCost, double discount, Date invoiceDate) {
        this.idRoom = idRoom;
        this.idCTHD = idCTHD;
        this.oldElectricReading = oldElectricReading;
        this.oldWaterReading = oldWaterReading;
        this.useElectricReading = useElectricReading;
        this.useWaterReading = useWaterReading;
        this.dayInMonth = dayInMonth;
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

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    // phía trên OK


    // Hàm lấy thông tin chi tiết hóa đơn từ database
    //TODO: Kiểm tra lại hàm bên dưới

    public static InvoiceDetail getInvoiceDetailForUpdate(int idRoom) {
        InvoiceDetail invoiceDetail = null;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT 
                chd.idCTHD,
                ISNULL(chd.sodienused, 0) AS sodienused,
                ISNULL(chd.sonuocused, 0) AS sonuocused,
                ISNULL(pt.Sodienhientai, 0) AS sodienthangtruoc,
                ISNULL(pt.Sonuochientai, 0) AS sonuocthangtruoc,
                pt.GiaPhong AS tiennha,
                pt.Giadien AS giadien,
                pt.Gianuoc AS gianuoc,
                pt.Giarac AS tienrac,
                ISNULL(chd.Giamgia, 0) AS giamgia,
                ISNULL(chd.Ngaythutiendukien, GETDATE()) AS ngayhoadon
            FROM TTPhongtro pt
            LEFT JOIN CTHoaDon chd ON pt.IDPhong = chd.IDPhong
            WHERE pt.IDPhong = ?
            ORDER BY chd.Ngaythutiendukien DESC
        """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRoom);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idCTHD = rs.getInt("idCTHD");
                int sodienused = rs.getInt("sodienused");
                int sonuocused = rs.getInt("sonuocused");
                int sodienthangtruoc = rs.getInt("sodienthangtruoc");
                int sonuocthangtruoc = rs.getInt("sonuocthangtruoc");
                double tiennha = rs.getDouble("tiennha");
                double giadien = rs.getDouble("giadien");
                double gianuoc = rs.getDouble("gianuoc");
                double tienrac = rs.getDouble("tienrac");
                double giamgia = rs.getDouble("giamgia");
                Date ngayhoadon = rs.getDate("ngayhoadon");
                ;

                // Tạo đối tượng InvoiceDetail
                invoiceDetail = new InvoiceDetail(
                        idRoom,
                        idCTHD,
                        sodienthangtruoc,
                        sonuocthangtruoc,
                        sodienused,
                        sonuocused,
                        0, // Số ngày sẽ được tính sau
                        tiennha,
                        tienrac,
                        0, // Chi phí phát sinh (default)
                        giamgia,
                        ngayhoadon
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
    public static boolean updateInvoiceDetail(int idRoom, InvoiceDetail updatedDetail, Date ngaythutien, int dayInMonth) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        System.out.println("Kiểm tra IDPhong: " + idRoom);
        System.out.println("Kiểm tra dịnh dạng: " + ngaythutien);
        try {
            conn = connectDatabase.DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Cập nhật vào bảng CTHoaDon
            String sqlInsertCTHD = """
                INSERT INTO CTHoaDon (SodienUsed, SonuocUsed, DaysInMonth, Tiennha, Tienrac, Chiphiphatsinh, Giamgia, sodienthangtruoc, sonuocthangtruoc, ngaythutiendukien, IDPhong)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;



            pstmt = conn.prepareStatement(sqlInsertCTHD);
            pstmt.setInt(1, updatedDetail.getUseElectricReading());
            pstmt.setInt(2, updatedDetail.getUseWaterReading());
            pstmt.setInt(3, dayInMonth);//TODO: cần xem lại
            pstmt.setDouble(4, updatedDetail.getRentPrice());
            pstmt.setDouble(5, updatedDetail.getGarbagePrice());
            pstmt.setDouble(6, updatedDetail.getAdditionalCost());
            pstmt.setDouble(7, updatedDetail.getDiscount());
            pstmt.setInt(8, updatedDetail.getOldElectricReading());
            pstmt.setInt(9, updatedDetail.getOldWaterReading());
            pstmt.setDate(10, ngaythutien); // Sử dụng ngày đã định dạng
            pstmt.setInt(11, idRoom);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                // Cập nhật số điện/nước hiện tại vào bảng TTPhongtro
                String sqlUpdateTTPhongtro = "UPDATE TTPhongtro SET Sodienhientai = ?, Sonuochientai = ? WHERE IDPhong = ?";
                pstmt = conn.prepareStatement(sqlUpdateTTPhongtro);
                pstmt.setInt(1, updatedDetail.getUseElectricReading() +updatedDetail.getOldElectricReading() );
                pstmt.setInt(2, updatedDetail.getUseWaterReading() + updatedDetail.getOldWaterReading());
                pstmt.setInt(3, idRoom);

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



    // phục vụ cho InvoiceFormView

    public static Object[] getInvoiceDetailByIdRoom(int idPhong) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn SQL để lấy thông tin chi tiết hóa đơn từ CTHoaDon hoặc TTPhongtro
            String sql = """
        SELECT TOP 1
            chd.idCTHD,
            ISNULL(chd.sodienused, 0) AS sodienused,
            ISNULL(chd.sonuocused, 0) AS sonuocused,
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
                // Trả về một mảng đối tượng chứa thông tin cần thiết
                return new Object[]{
                        rs.getInt("idCTHD"),              // ID chi tiết hóa đơn
                        rs.getInt("sodienused"),    // Số điện use
                        rs.getInt("sonuocused"),    // Số nước use
                        rs.getDouble("tiennha"),          // Tiền nhà
                        rs.getDouble("giadien"),          // Giá điện
                        rs.getDouble("gianuoc"),          // Giá nước
                        rs.getDouble("Tienrac"),          // Giá rác
                        rs.getDouble("giamgia"),          // Giảm giá
                        rs.getString("ngayhoadon")        // Ngày hóa đơn
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy dữ liệu
    }

    public static Date getPreDayInMonth(int idRoom) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn ngày xuất hóa đơn gần nhất
            String sqlLastInvoiceDate = """
            SELECT TOP 1 NgayxuatHoaDon 
            FROM HoaDon 
            WHERE IDPhong = ? 
            ORDER BY NgayxuatHoaDon DESC
        """;

            PreparedStatement pstmt = conn.prepareStatement(sqlLastInvoiceDate);
            pstmt.setInt(1, idRoom);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Trả về ngày xuất hóa đơn nếu có
                return rs.getDate("NgayxuatHoaDon");
            }

            // Nếu không tìm thấy, truy vấn ngày bắt đầu thuê từ bảng NguoiThueTro
            String sqlStartRentalDate = """
            SELECT Ngaybatdauthue 
            FROM NguoiThueTro 
            WHERE IDNguoiThue = (
                SELECT IDNguoiThue 
                FROM TTPhongtro 
                WHERE IDPhong = ?
            )
        """;

            pstmt = conn.prepareStatement(sqlStartRentalDate);
            pstmt.setInt(1, idRoom);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Trả về ngày bắt đầu thuê nếu có
                return rs.getDate("Ngaybatdauthue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy thông tin
    }

    public static int calculateDaysBetweenDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Ngày bắt đầu và ngày kết thúc không được null.");
        }
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
}
