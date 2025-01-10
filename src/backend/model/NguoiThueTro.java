package backend.model;

import backend.connectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NguoiThueTro {
    private String idNguoiThue;
    private String userId;
    private String fullName;
    private String phone;
    private String cccd;

    // Constructor
    public NguoiThueTro(String idNguoiThue, String userId, String fullName, String phone, String cccd) {
        this.idNguoiThue = idNguoiThue;
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.cccd = cccd;
    }

    public static String getIdNguoiThueFromUserID(String userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT IDNguoiThue FROM NguoiThueTro WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("IDNguoiThue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // Getters
    public String getIdNguoiThue() {
        return idNguoiThue;
    }

    public String getUserId() {
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
    public static NguoiThueTro getNguoiThueTroByUserId(String userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM NguoiThueTro WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new NguoiThueTro(
                        rs.getString("IDNguoiThue"),
                        rs.getString("UserID"),
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

    public static String getIdNguoiThueFromCCCD(String CCCD) {
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
                String idNguoiThue = rs.getString("IDNguoiThue");
                System.out.println("Tìm thấy IDNguoiThue: " + idNguoiThue);
                return idNguoiThue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Không tìm thấy IDNguoiThue với CCCD: " + CCCD);
        return null; // Trả về null nếu không tìm thấy
    }



    public static String getTenantRoomId(String tenantId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn kiểm tra id_nguoiThueTro có trong TTPhongtro hay không
            String sql = """
            SELECT IDPhong
            FROM TTPhongtro
            WHERE IDNguoiThue = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tenantId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("IDPhong"); // Trả về ID phòng nếu tìm thấy
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không có dữ liệu
    }

}