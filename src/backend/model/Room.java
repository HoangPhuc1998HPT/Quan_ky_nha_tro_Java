package backend.model;

import backend.connectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static backend.model.NguoiThueTro.getIdNguoiThueFromCCCD;

public class Room {
    private int idRoom;
    private String name;
    private String tenantName;
    private double roomPrice;
    private double electricityPrice;
    private double waterPrice;
    private double garbagePrice;
    private int currentElectricity;
    private int currentWater;



    // Constructor
    public Room(int idRoom, String name, String tenantName, double roomPrice, double electricityPrice, double waterPrice, double garbagePrice) {
        this.idRoom = idRoom;
        this.name = name;
        this.tenantName = null;
        this.roomPrice = roomPrice;
        this.electricityPrice = electricityPrice;
        this.waterPrice = waterPrice;
        this.garbagePrice = garbagePrice;
        this.currentElectricity = 0;
        this.currentWater = 0;
    }
    public static List<Object[]> getRoomInfoByTenantId(int userId) {
        List<Object[]> roomInfoList = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                    SELECT TTPhongtro.TenPhong, 
                           Chutro.HoTen AS TenChuTro, 
                           Chutro.Phone AS SoDienThoai, 
                           TTPhongtro.Address AS DiaChi, 
                           NguoiThueTro.NgayBatDauThue, 
                           TTPhongtro.IDPhong,
                           Chutro.IDChutro
                    FROM TTPhongtro
                    JOIN Chutro ON TTPhongtro.IDChutro = Chutro.IDChutro
                    LEFT JOIN NguoiThueTro ON TTPhongtro.IDNguoiThue = NguoiThueTro.IDNguoiThue
                    WHERE TTPhongtro.IDNguoiThue = ?
                    """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                roomInfoList.add(new Object[]{
                        rs.getString("TenPhong"),         // Tên Phòng
                        rs.getString("TenChuTro"),       // Tên Chủ Trọ
                        rs.getString("SoDienThoai"),     // Số Điện Thoại
                        rs.getString("DiaChi"),          // Địa Chỉ
                        rs.getDate("NgayBatDauThue"),    // Ngày Bắt Đầu Thuê
                        rs.getString("IDPhong"),         // ID Phòng
                        rs.getString("IDChutro")         // ID Chủ Trọ
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomInfoList;
    }

    public static List<Object[]> getAllRoomData() {
        List<Object[]> data = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT r.IDPhong, r.TenPhong, r.Address, r.GiaPhong, ct.HoTen AS TenChuTro, nt.HoTen AS TenNguoiThue " +
                    "FROM TTPhongtro r " +
                    "LEFT JOIN Chutro ct ON r.IDChutro = ct.IDChutro " +
                    "LEFT JOIN NguoiThueTro nt ON r.IDNguoiThue = nt.IDNguoiThue";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                data.add(new Object[]{
                        rs.getString("IDPhong"),
                        rs.getString("TenPhong"),
                        rs.getString("Address"),
                        rs.getDouble("GiaPhong"),
                        rs.getString("TenChuTro"),
                        rs.getString("TenNguoiThue")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    public static void disableRoom(String roomId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE TTPhongtro SET IsActive = 0 WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
            System.out.println("Đã tạm ngưng hoạt động phòng: " + roomId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getIdChutrobyRoomId(int roomId) {
        int idChutro = -1; // Giá trị mặc định nếu không tìm thấy
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn SQL để lấy ID chủ trọ dựa trên ID phòng
            String sql = "SELECT IDChutro FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId); // Gán giá trị roomId vào truy vấn

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                idChutro = rs.getInt("IDChutro"); // Lấy ID chủ trọ
            } else {
                System.out.println("Không tìm thấy ID chủ trọ cho ID phòng: " + roomId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khi lấy ID chủ trọ cho phòng: " + roomId);
        }
        return idChutro;
    }



    // Getters
    public int getIdRoom() {
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

    public double getGarbagePrice() {
        return garbagePrice;
    }

    public int getCurrentElectricity() {
        return currentElectricity;
    }

    public int getCurrentWater() {
        return currentWater;
    }



    // Static method to fetch room details from database
    public static Room getRoomDetails(int idRoom) {
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
                ISNULL(TTphongtro.Giarac, 0) AS Giarac
            FROM TTPhongtro
            LEFT JOIN NguoiThueTro ON TTPhongtro.IDPhong = NguoiThueTro.IDnguoithue
            WHERE TTPhongtro.IDPhong = ?
        """;
            // Chuẩn bị câu lệnh PreparedStatement
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRoom);

            // Thực thi truy vấn và xử lý kết quả
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                            rs.getInt("IDPhong"),            // ID phòng
                            rs.getString("TenPhong"),           // Tên phòng
                            rs.getString("TenNguoiThue"),       // Tên người thuê
                            rs.getDouble("GiaPhong"),           // Giá phòng
                            rs.getDouble("Giadien"),            // Giá điện
                            rs.getDouble("Gianuoc"),            // Giá nước
                            rs.getDouble("Giarac")             // Tiền rác (mặc định 0 nếu NULL)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không có dữ liệu
    }

    public static boolean addRoom(String name, String address, double roomPrice, double electricityPrice, double waterPrice, double garbagePrice, int id_chutro) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO TTPhongtro (TenPhong, Address ,GiaPhong , Giadien, Gianuoc, Giarac, IDChutro) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setDouble(3, roomPrice);
            pstmt.setDouble(4, electricityPrice);
            pstmt.setDouble(5, waterPrice);
            pstmt.setDouble(6, garbagePrice);
            pstmt.setInt(7, id_chutro);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteRoom(int idRoom) {
        // TODO: Hiếu kiểm tra Tạo truy vấn xóa phòng ==> Cơ chế tốt nhất là sau khi delete th không cho truy vấn id phòng đó nữa
        //..............
        return false;
    }

    public static boolean updateNguoiThueTroInRoom(int idRoom, String CCCD) {
        // TODO: Hiếu tạo truy vấn cho update thông tin người thuê vào phòng trọ lên database => Done

        int id_nguoithue = getIdNguoiThueFromCCCD(CCCD);
        System.out.println("ID Nguoi thue: " + id_nguoithue);
        if (id_nguoithue == 0) {
            return false;
        }
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
           String sql = """
                   UPDATE TTPhongtro SET IDnguoithue = ? WHERE IDPhong = ?
                   """;
           PreparedStatement pstm = conn.prepareStatement(sql);
           pstm.setInt(1, id_nguoithue);
           pstm.setInt(2, idRoom);
           return pstm.executeUpdate() > 0;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
    }


    public static int getTenantRoomId(int tenantId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT IDPhong FROM TTPhongtro WHERE IDNguoiThue = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tenantId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("IDPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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

    public static List<Object[]> getEmptyRoomsForTenant(int tenantId) {
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

    public static Room getRoomById(int roomid) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT 
                TTPhongtro.IDPhong, 
                TTPhongtro.TenPhong, 
                ISNULL(NguoiThueTro.Hoten, 'Không có') AS TenNguoiThue, 
                TTPhongtro.GiaPhong, 
                TTPhongtro.Giadien, 
                TTPhongtro.Gianuoc, 
                ISNULL(TTPhongtro.Giarac, 0) AS Giarac 
            FROM TTPhongtro
            LEFT JOIN NguoiThueTro ON TTPhongtro.IDNguoiThue = NguoiThueTro.IDNguoiThue
            LEFT JOIN HoaDon ON TTPhongtro.IDPhong = HoaDon.IDPhong
            WHERE TTPhongtro.IDPhong = ?
        """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Room(
                        rs.getInt("IDPhong"),            // ID phòng
                        rs.getString("TenPhong"),        // Tên phòng
                        rs.getString("TenNguoiThue"),    // Tên người thuê (nếu có)
                        rs.getDouble("GiaPhong"),        // Giá phòng
                        rs.getDouble("Giadien"),         // Giá điện
                        rs.getDouble("Gianuoc"),         // Giá nước
                        rs.getDouble("Giarac")          // Tiền rác
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public static String getNameNguoiThueFromIDRoom(int idRoom) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT ISNULL(nt.Hoten, N'Không có') AS TenNguoiThue
            FROM TTPhongtro pt
            LEFT JOIN NguoiThueTro nt ON pt.IDNguoiThue = nt.IDNguoiThue
            WHERE pt.IDPhong = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRoom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("TenNguoiThue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không có dữ liệu";
    }


}
