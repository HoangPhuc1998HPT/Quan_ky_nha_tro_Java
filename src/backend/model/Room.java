package backend.model;

import backend.connectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static backend.model.NguoiThueTro.getIdNguoiThueFromCCCD;

public class Room {
    private String idRoom;
    private String name;
    private String tenantName;
    private double roomPrice;
    private double electricityPrice;
    private double waterPrice;


    // Constructor
    public Room(String idRoom, String name, String tenantName, double roomPrice, double electricityPrice, double waterPrice, double tienrac) {
        this.idRoom = idRoom;
        this.name = name;
        this.tenantName = tenantName;
        this.roomPrice = roomPrice;
        this.electricityPrice = electricityPrice;
        this.waterPrice = waterPrice;
    }

    // Getters
    public String getIdRoom() {
        return idRoom;
    }

    public String getName() {
        return name;
    }

    public String getTenantName() {
        return tenantName;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public double getElectricityPrice() {
        return electricityPrice;
    }

    public double getWaterPrice() {
        return waterPrice;
    }



    // Static method to fetch room details from database
    public static Room getRoomDetails(String idRoom) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Câu truy vấn SQL
            String sql = """
            SELECT 
                TTPhongtro.IDPhong,
                TTPhongtro.TenPhong,
                ISNULL(NguoiThueTro.Hoten, N'Không có') AS TenNguoiThue,
                TTPhongtro.GiaPhong,
                TTPhongtro.Giadien,
                TTPhongtro.Gianuoc,
                ISNULL(HoaDon.Tienrac, 0) AS Tienrac
            FROM TTPhongtro
            LEFT JOIN NguoiThueTro ON TTPhongtro.IDPhong = NguoiThueTro.IDnguoithue
            LEFT JOIN HoaDon ON TTPhongtro.IDPhong = HoaDon.BillID
            WHERE TTPhongtro.IDPhong = ?
            ORDER BY HoaDon.Ngayxuathoadon DESC
        """;

            // Chuẩn bị câu lệnh PreparedStatement
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idRoom);

            // Thực thi truy vấn và xử lý kết quả
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                            rs.getString("IDPhong"),            // ID phòng
                            rs.getString("TenPhong"),           // Tên phòng
                            rs.getString("TenNguoiThue"),       // Tên người thuê
                            rs.getDouble("GiaPhong"),           // Giá phòng
                            rs.getDouble("Giadien"),            // Giá điện
                            rs.getDouble("Gianuoc"),            // Giá nước
                            rs.getDouble("Tienrac")             // Tiền rác (mặc định 0 nếu NULL)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không có dữ liệu
    }

    public static boolean deleteRoom(String idRoom) {
        // TODO: Hiếu kiểm tra Tạo truy vấn xóa phòng ==> Cơ chế tốt nhất là sau khi delete th không cho truy vấn id phòng đó nữa
        //..............
        return false;
    }

    public static void updateNguoiThueTroInRoom(String idRoom, String CCCD) {
        //TODO: Hiếu tạo truy vấn cho update thông tin người thuê vào phòng trọ lên database
       String id_nguoithue = getIdNguoiThueFromCCCD(CCCD);
    }


    public static String getTenantRoomId(String tenantId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT IDPhong FROM TTPhongtro WHERE IDNguoiThue = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tenantId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("IDPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Object[]> getEmptyRooms() {
        List<Object[]> emptyRooms = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT IDPhong, TenPhong, GiaPhong FROM TTPhongtro WHERE IDNguoiThue IS NULL";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                emptyRooms.add(new Object[]{
                        rs.getString("IDPhong"),
                        rs.getString("TenPhong"),
                        rs.getDouble("GiaPhong")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emptyRooms;
    }
    public static List<Object[]> getEmptyRoomsForTenant(String tenantId) {
        List<Object[]> emptyRooms = new ArrayList<>();
        //"Tên Phòng", "Người liên hệ", "Số điện thoại", "Địa chỉ", "Giá Phòng"
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                SELECT TTPhongtro.IDPhong, TTPhongtro.TenPhong, TTPhongtro.GiaPhong, TTPhongtro.Address, Chutro.HoTen, Chutro.Phone
                FROM TTPhongtro
                JOIN Chutro ON TTPhongtro.IDChutro = Chutro.IDChutro
                WHERE TTPhongtro.IDNguoiThue IS NULL
                """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                emptyRooms.add(new Object[]{
                        rs.getString("IDPhong"),    // ID Phòng
                        rs.getString("TenPhong"),  // Tên Phòng
                        rs.getDouble("GiaPhong"),  // Giá Phòng
                        rs.getString("Address"),   // Địa chỉ
                        rs.getString("HoTen"),     // Người liên hệ
                        rs.getString("Phone")      // Số điện thoại
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emptyRooms;
    }


}
