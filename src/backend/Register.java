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

    public static String getUserIDFromUsers(String username) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            System.out.println("Đang lấy userID");
            String sql = "SELECT UserID FROM Users WHERE Username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                System.out.println("RS: " + rs.getString("UserID"));
                return rs.getString("UserID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateChutroInfo(String userID, String fullName, String phone, String cccd) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
//            String sql = "UPDATE Chutro SET Hoten = ?, Phone = ?, CCCD = ? WHERE Username = ?";
            String sql = "INSERT INTO Chutro (Hoten, CCCD, Phone, UserID) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fullName);
            pstmt.setString(2, phone);
            pstmt.setString(3, cccd);
            pstmt.setString(4, userID);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



}
