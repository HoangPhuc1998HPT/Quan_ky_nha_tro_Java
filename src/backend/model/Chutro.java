package backend.model;

import backend.connectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Chutro {
    private String idChutro;
    private String userId;
    private String fullName;
    private String phone;
    private String cccd;

    // Constructor
    public Chutro(String idChutro, String userId, String fullName, String phone, String cccd) {
        this.idChutro = idChutro;
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.cccd = cccd;
    }

    // Getters
    public static String getIdChutroByUsername(String username) {
        String idChutro = null;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn để lấy UserID từ bảng Users
            String sqlGetUserId = "SELECT UserID FROM Users WHERE Username = ?";
            PreparedStatement pstmtUser = conn.prepareStatement(sqlGetUserId);
            pstmtUser.setString(1, username);
            ResultSet rsUser = pstmtUser.executeQuery();

            if (rsUser.next()) {
                String userId = rsUser.getString("UserID");

                // Truy vấn để lấy IDChutro từ bảng Chutro
                String sqlGetIdChutro = "SELECT IDChutro FROM Chutro WHERE UserID = ?";
                PreparedStatement pstmtChutro = conn.prepareStatement(sqlGetIdChutro);
                pstmtChutro.setString(1, userId);
                ResultSet rsChutro = pstmtChutro.executeQuery();

                if (rsChutro.next()) {
                    idChutro = rsChutro.getString("IDChutro");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idChutro;
    }

    public static String getUsernameFromIdChutro(String idChutro) {
        String username = "testchutro02" ; //"Hiếu lấy Username từ id chủ trọ ra nhe" ;
        // TODO: Trích tên username từ id_chủ trọ để back to Dashboard chủ trọ
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getCccd() {
        return cccd;
    }

    // Static methods for database operations
    public static Chutro getChutroByUserId(String userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            System.out.println("Đi tới CHutro.java ");
            String sql = "SELECT * FROM Chutro WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Chutro(
                        rs.getString("IDChutro"),
                        rs.getString("UserID"),
                        rs.getString("Hoten"),
                        rs.getString("Phone"),
                        rs.getString("CCCD")
                );

            }
            System.out.println(" đã trả về đối tượng chủ trọ hoàn chỉnh ");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Chutro getChutrobyChutroID(String idChutro) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            System.out.println("Đi tới CHutro.java ");
            String sql = "SELECT * FROM Chutro WHERE IDChutro = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idChutro);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Chutro(
                        rs.getString("IDChutro"),
                        rs.getString("UserID"),
                        rs.getString("Hoten"),
                        rs.getString("Phone"),
                        rs.getString("CCCD")
                );

            }
            System.out.println(" đã trả về đối tượng chủ trọ hoàn chỉnh ");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateHoTenChutro(String idChutro, String fullName) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE Chutro SET Hoten = ? WHERE IDChutro = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fullName);
            pstmt.setString(2, idChutro);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateSDTChutro(String idChutro, String phone) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE Chutro SET Phone = ? WHERE IDChutro = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);
            pstmt.setString(2, idChutro);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updatePasswordChutro(String idChutro, String password) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE Chutro SET Password = ? WHERE IDChutro = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setString(2, idChutro);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static List<String[]> getRoomList(String id_chutro) {
        List<String[]> roomList = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn SQL
            String sql = """
            SELECT 
                TTPhongtro.IDPhong,
                TTPhongtro.TenPhong,
                ISNULL(NguoiThueTro.Hoten, N'Không có') AS TenNguoiThue
            FROM TTPhongtro
            LEFT JOIN NguoiThueTro ON TTPhongtro.IDPhong = NguoiThueTro.IDnguoithue
            WHERE TTPhongtro.IDChutro = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_chutro); // Gán giá trị IDChutro
            ResultSet rs = pstmt.executeQuery();

            // Lấy dữ liệu và thêm vào danh sách
            while (rs.next()) {
                String idPhong = rs.getString("IDPhong");
                String tenPhong = rs.getString("TenPhong");
                String tenNguoiThue = rs.getString("TenNguoiThue");
                roomList.add(new String[]{idPhong, tenPhong, tenNguoiThue, "Xem Chi Tiết"});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomList;
    }
    // TODO: Xử lý lấy thông tin tên chủ trọ và tổng số phòng chủ trụ nắm giữ
    public static String getNameChutroFromIdChutro(String idChutro){
        return "Văn A";
    }
    public static int getCountRoomFromIdChutro(String idChutro){
        return 0;
    }

}
