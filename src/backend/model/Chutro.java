package backend.model;

import backend.connectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Chutro {
    private int idChutro;
    private int userId;
    private String fullName;
    private String phone;
    private String cccd;

    // Constructor
    public Chutro(int idChutro, int userId, String fullName, String phone, String cccd) {
        this.idChutro = idChutro;
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.cccd = cccd;
    }

    public Chutro(int idChutro, String fullName, String phone, String cccd) {
        this.idChutro = idChutro;
        this.fullName = fullName;
        this.phone = phone;
        this.cccd = cccd;
    }

    // Getters
    public static int getIdChutroByUsername(String username) {
        int idChutro = 0;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn để lấy UserID từ bảng Users
            String sqlGetUserId = "SELECT UserID FROM Users WHERE Username = ?";
            PreparedStatement pstmtUser = conn.prepareStatement(sqlGetUserId);
            pstmtUser.setString(1, username);
            ResultSet rsUser = pstmtUser.executeQuery();

            if (rsUser.next()) {
                int userId = rsUser.getInt("UserID");

                // Truy vấn để lấy IDChutro từ bảng Chutro
                String sqlGetIdChutro = "SELECT IDChutro FROM Chutro WHERE UserID = ?";
                PreparedStatement pstmtChutro = conn.prepareStatement(sqlGetIdChutro);
                pstmtChutro.setInt(1, userId);
                ResultSet rsChutro = pstmtChutro.executeQuery();

                if (rsChutro.next()) {
                    idChutro = rsChutro.getInt("IDChutro");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idChutro;
    }

    public static String getUsernameFromIdChutro(int idChutro) {
        String username = null;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT u.Username 
            FROM Chutro c
            JOIN Users u ON c.UserID = u.UserID
            WHERE c.IDChutro = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idChutro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                username = rs.getString("Username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    public static int getUserIdFromCCCD(String cccd) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT UserID FROM Chutro WHERE CCCD = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cccd);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return rs.getInt("UserID");
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getFullNameByUsername(String username) {
        String fullName = null;
        int userID = User.getUserIdByUsername(username);
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT HoTen
            FROM Chutro c
            WHERE c.UserID = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("HoTen");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullName;
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

    public int getUserId() {
        return userId;
    }

    // Static methods for database operations
    public static Chutro getChutroByUserId(int userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            System.out.println("Đi tới CHutro.java ");
            String sql = "SELECT * FROM Chutro WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Chutro(
                        rs.getInt("IDChutro"),
                        rs.getInt("UserID"),
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

    public static Chutro getChutrobyChutroID(int idChutro) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            System.out.println("Đi tới CHutro.java ");
            String sql = "SELECT * FROM Chutro WHERE IDChutro = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idChutro);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Chutro(
                        rs.getInt("IDChutro"),
                        rs.getInt("UserID"),
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

    public static boolean updateHoTenChutro(int idChutro, String fullName) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE Chutro SET Hoten = ? WHERE IDChutro = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fullName);
            pstmt.setInt(2, idChutro);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateSDTChutro(int idChutro, String phone) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE Chutro SET Phone = ? WHERE IDChutro = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);
            pstmt.setInt(2, idChutro);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updatePasswordChutro(int idChutro, String password) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """  
                UPDATE Users
                SET Users.Password = ?
                FROM Users
                INNER JOIN Chutro
                ON Users.UserID = Chutro.UserID
                WHERE Chutro.IDChutro = ?
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setInt(2, idChutro);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static List<String[]> getRoomList(int id_chutro) {
        List<String[]> roomList = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn SQL
            String sql = """
            SELECT 
                TTPhongtro.IDPhong,
                TTPhongtro.TenPhong,
                ISNULL(NguoiThueTro.Hoten, N'Không có') AS TenNguoiThue
            FROM TTPhongtro
            LEFT JOIN NguoiThueTro ON TTPhongtro.IDnguoithue = NguoiThueTro.IDnguoithue
            WHERE TTPhongtro.IDChutro = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_chutro); // Gán giá trị IDChutro
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
    // STRIKE TODO: Xử lý lấy thông tin tên chủ trọ và tổng số phòng chủ trụ nắm giữ
    public static String getNameChutroFromIdChutro(int idChutro){
        try(Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT HoTen FROM Chutro WHERE IDChutro = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idChutro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("HoTen");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getIDChutroFromHoTen(String hoten){
        try(Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT IDChutro FROM Chutro WHERE HoTen = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hoten);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("IDChutro");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    /// STRIKE TODO: Xử lý lấy thông tin tên chủ trọ và tổng số phòng chủ trụ nắm giữ
    public static int getCountRoomFromIdChutro(int idChutro){
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                    SELECT COUNT (*) AS SoPhongQuanLy
                    FROM TTPhongtro LEFT JOIN Chutro ON TTPhongtro.IDChutro = Chutro.IDChutro
                    """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("SoPhongQuanLy");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    // Hàm lấy danh sách tất cả chủ trọ từ cơ sở dữ liệu

    public static List<Object[]> getAllChutroData() {
        List<Object[]> chutroData = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn SQL để lấy thông tin chủ trọ và số phòng quản lý
            String sql = """
                SELECT 
                    ct.Hoten AS TenChutro, 
                    ct.Phone AS SDT,
                    ct.CCCD AS CCCD,
                    COUNT(pt.IDPhong) AS SoPhongQuanLy
                FROM Chutro ct
                LEFT JOIN TTPhongtro pt ON ct.IDChutro = pt.IDChutro
                GROUP BY ct.Hoten, ct.Phone, ct.CCCD
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // Thêm dữ liệu vào danh sách
            while (rs.next()) {
                chutroData.add(new Object[]{
                        rs.getString("TenChutro"),       // Tên chủ trọ
                        rs.getInt("SoPhongQuanLy"),     // Số phòng quản lý
                        rs.getString("SDT"),            // Số điện thoại
                        rs.getString("CCCD")            // CCCD
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chutroData;
    }
    // Lấy username từ CCCD
    public static String getUsernameByCCCD(String cccd) {
        String username = null;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                SELECT u.Username 
                FROM Chutro c
                JOIN Users u ON c.UserID = u.UserID
                WHERE c.CCCD = ?
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cccd);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                username = rs.getString("Username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }


    public static String getLandlordNameFromIdNguoiThue(int idNguoiThueTro) {
        String landlordName = null;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn SQL để lấy tên chủ trọ
            String sql = """
            SELECT ct.Hoten AS LandlordName
            FROM TTPhongtro pt
            JOIN Chutro ct ON pt.IDChutro = ct.IDChutro
            WHERE pt.IDNguoiThue = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idNguoiThueTro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                landlordName = rs.getString("LandlordName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return landlordName != null ? landlordName : "Không xác định";
    }
}
