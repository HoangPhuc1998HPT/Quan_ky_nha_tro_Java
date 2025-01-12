package backend;

import backend.connectDatabase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Register {
    public static boolean userNameCheck(String username) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
//            System.out.println("RS Result: " + rs.next());
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean registerUser(String role, String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword) || userNameCheck(username)) {
            return false;
        }
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Users (Username, Password, Role) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Hash mật khẩu nếu cần
            pstmt.setString(3, role);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getUserIDFromUsers(String username) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            System.out.println("Đang lấy userID");
            String sql = "SELECT UserID FROM Users WHERE Username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                System.out.println("RS: " + rs.getString("UserID"));
                return rs.getInt("UserID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean cccdCheck(String cccd, String role) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String tableName;
            if(role == "chutro") {
                tableName = "Chutro";
            } else {
                tableName = "NguoiThueTro";
            }
            String sql = "SELECT CCCD FROM "+ tableName + " WHERE CCCD = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cccd);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    };

    public static boolean sdtCheck(String phone, String role) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String tableName;
            if(role == "chutro") {
                tableName = "Chutro";
            } else {
                tableName = "NguoiThueTro";
            }
            String sql = "SELECT Phone FROM "+ tableName + " WHERE Phone = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    };


    // # STRIKE TODO: Fix Hiếu chỉnh lại phương thức thành update được cho cả người thuê lẫn chủ trọ ( khi register)
    public static boolean updateChutroInfo(int userID, String fullName, String phone, String cccd) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
//            String sql = "UPDATE Chutro SET Hoten = ?, Phone = ?, CCCD = ? WHERE Username = ?";
            if(!cccdCheck(cccd, "chutro") && !sdtCheck(phone, "chutro")) {
                String sql = "INSERT INTO Chutro (Hoten, CCCD, Phone, UserID) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, fullName);
                pstmt.setString(2, phone);
                pstmt.setString(3, cccd);
                pstmt.setInt(4, userID);
                return pstmt.executeUpdate() > 0;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateNguoiTTInfo(int userID, String fullName, String phone, String cccd) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
//            String sql = "UPDATE Chutro SET Hoten = ?, Phone = ?, CCCD = ? WHERE Username = ?";
            if(!cccdCheck(cccd, "nguoithuetro") && !sdtCheck(phone, "nguoithuetro")) {
                String sql = "INSERT INTO NguoiThueTro (Hoten, CCCD, Phone, UserID, Ngaybatdauthue, Ngayketthucthue) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, fullName);
                pstmt.setString(2, phone);
                pstmt.setString(3, cccd);
                pstmt.setInt(4, userID);
                pstmt.setString(5, null);
                pstmt.setString(6, null);
                return pstmt.executeUpdate() > 0;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



}
