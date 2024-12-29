package backend.model;

import backend.connectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Room {
    private String idRoom;
    private String name;
    private String tenantName;
    private double roomPrice;
    private double electricityPrice;
    private double waterPrice;
    private double garbageFee;

    // Constructor
    public Room(String idRoom, String name, String tenantName, double roomPrice, double electricityPrice, double waterPrice, double garbageFee) {
        this.idRoom = idRoom;
        this.name = name;
        this.tenantName = tenantName;
        this.roomPrice = roomPrice;
        this.electricityPrice = electricityPrice;
        this.waterPrice = waterPrice;
        this.garbageFee = garbageFee;
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

    public double getGarbageFee() {
        return garbageFee;
    }

    // Static method to fetch room details from database
    public static Room getRoomDetails(String idRoom) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                SELECT 
                    TTPhongtro.IDPhong,
                    TTPhongtro.TenPhong,
                    ISNULL(NguoiThueTro.Hoten, N'Không có') AS TenNguoiThue,
                    TTPhongtro.GiaPhong,
                    TTPhongtro.Giadien,
                    TTPhongtro.Gianuoc,
                    TTPhongtro.Tienrac
                FROM TTPhongtro
                LEFT JOIN NguoiThueTro ON TTPhongtro.IDPhong = NguoiThueTro.IDnguoithue
                WHERE TTPhongtro.IDPhong = ?
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idRoom);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Room(
                        rs.getString("IDPhong"),
                        rs.getString("TenPhong"),
                        rs.getString("TenNguoiThue"),
                        rs.getDouble("GiaPhong"),
                        rs.getDouble("Giadien"),
                        rs.getDouble("Gianuoc"),
                        rs.getDouble("Tienrac")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
