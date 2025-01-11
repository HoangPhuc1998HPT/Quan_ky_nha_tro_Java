package backend.model;

import backend.connectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NguoiThueTro {
    private int idNguoiThue;
    private int userId;
    private String fullName;
    private String phone;
    private String cccd;

    // Constructor
    public NguoiThueTro(int idNguoiThue, int userId, String fullName, String phone, String cccd) {
        this.idNguoiThue = idNguoiThue;
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.cccd = cccd;
    }

    public static int getIdNguoiThueFromUserID(int userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT IDNguoiThue FROM NguoiThueTro WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("IDNguoiThue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Object[]> getAllNguoiThueData() {
        List<Object[]> data = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Sử dụng tên cột chính xác từ cơ sở dữ liệu
            String sql = "SELECT IDNguoiThue, Hoten, Phone, CCCD FROM NguoiThueTro";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                data.add(new Object[]{
                        rs.getInt("IDNguoiThue"),
                        rs.getString("Hoten"), // Thay đổi từ FullName thành Hoten
                        rs.getString("Phone"),
                        rs.getString("CCCD")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }



    // Getters
    public int getIdNguoiThue() {
        return idNguoiThue;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getCCCD() {
        return cccd;
    }


    // Static methods for database operations
    public static NguoiThueTro getNguoiThueTroByUserId(int userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM NguoiThueTro WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new NguoiThueTro(
                        rs.getInt("IDNguoiThue"),
                        rs.getInt("UserID"),
                        rs.getString("Hoten"),
                        rs.getString("Phone"),
                        rs.getString("CCCD")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getIdNguoiThueFromCCCD(String CCCD) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT IDNguoiThue
            FROM NguoiThueTro
            WHERE CCCD = ?
        """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, CCCD);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idNguoiThue = rs.getInt("IDNguoiThue");
                System.out.println("Tìm thấy IDNguoiThue: " + idNguoiThue);
                return idNguoiThue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Không tìm thấy IDNguoiThue với CCCD: " + CCCD);
        return 0; // Trả về null nếu không tìm thấy
    }



    public static int getTenantRoomId(int tenantId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn kiểm tra id_nguoiThueTro có trong TTPhongtro hay không
            String sql = """
            SELECT IDPhong
            FROM TTPhongtro
            WHERE IDNguoiThue = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tenantId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("IDPhong"); // Trả về ID phòng nếu tìm thấy
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Trả về null nếu không có dữ liệu
    }

}