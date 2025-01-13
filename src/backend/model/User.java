package backend.model;

import backend.connectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String username;
    private String password;
    private String role;
    private boolean isActive;

    // Constructor
    public User(int userId, String username, String password, String role, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // CRUD Operations

    // Method to save user into database
    public static boolean saveUser(User user) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Users (Username, Password, Role, is_active) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
            pstmt.setBoolean(4, user.isActive());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to retrieve user by username
    public static User getUserByUsername(String username) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Users WHERE Username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getBoolean("is_active")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getUserIdByUsername(String username) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT UserID FROM Users WHERE Username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("UserID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getRoleFromUsername(String username) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Role FROM Users WHERE Username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("Role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    // Method to update user status
    public static boolean updateUserStatus(int userId, boolean isActive) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE Users SET is_active = ? WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, isActive);
            pstmt.setInt(2, userId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete user by ID
    public static boolean deleteUser(int userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Users WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Object[]> getInactiveUsers() {
        List<Object[]> users = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT UserID, Username, Role, is_active FROM Users WHERE is_active = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                users.add(new Object[]{
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Role"),
                        rs.getBoolean("is_active")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static boolean activateUser(int userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE Users SET is_active = 1 WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
