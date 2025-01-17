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

    public static NguoiThueTro getTenantById(int idNguoithuetro) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM NguoiThueTro WHERE IDNguoiThue = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idNguoithuetro);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Trả về đối tượng NguoiThueTro
                return new NguoiThueTro(
                        rs.getInt("IDNguoiThue"),  // ID người thuê
                        rs.getInt("UserID"),      // ID người dùng
                        rs.getString("Hoten"),       // Họ tên
                        rs.getString("Phone"),       // Số điện thoại
                        rs.getString("CCCD")         // CCCD
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public static boolean updateTenantInformation(int userId, String name, String phone, String cccd) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE NguoiThueTro SET Hoten = ?, Phone = ?, CCCD = ? WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, cccd);
            pstmt.setInt(4, userId);

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu cập nhật thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Trả về false nếu xảy ra lỗi
        }
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
        // Khởi tạo đối tượng kết quả là null
        NguoiThueTro nguoiThueTro = null;

        // Mở kết nối đến cơ sở dữ liệu
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Câu lệnh SQL lấy thông tin từ bảng NguoiThueTro
            String sql = "SELECT IDNguoiThue, UserID, Hoten, Phone, CCCD FROM NguoiThueTro WHERE UserID = ?";

            // Chuẩn bị câu lệnh
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId); // Gán tham số userId

                // Thực thi câu lệnh và nhận kết quả
                try (ResultSet rs = pstmt.executeQuery()) {
                    // Kiểm tra nếu có dữ liệu
                    if (rs.next()) {
                        nguoiThueTro = new NguoiThueTro(
                                rs.getInt("IDNguoiThue"),
                                rs.getInt("UserID"),
                                rs.getString("Hoten"),
                                rs.getString("Phone"),
                                rs.getString("CCCD")
                        );
                    }
                }
            }
        } catch (Exception e) {
            // In ra lỗi nếu có
            e.printStackTrace();
        }

        // Trả về kết quả (null nếu không tìm thấy)
        return nguoiThueTro;
    }


    public static NguoiThueTro getNguoiThueTroByIDnguoithue(int idNguoiThue) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM NguoiThueTro WHERE IDnguoithue = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idNguoiThue);
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


    // truy vấn này kiềm tra id người thuê trọ nằ trong TT Phòng
    public static NguoiThueTro getTenantByRoomId(int roomId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn SQL để kiểm tra ID người thuê trong bảng TTPhongtro
            String sql = """
            SELECT 
                nt.IDNguoiThue, 
                nt.UserID, 
                nt.Hoten AS FullName, 
                nt.Phone, 
                nt.CCCD 
            FROM TTPhongtro pt
            JOIN NguoiThueTro nt ON pt.IDNguoiThue = nt.IDNguoiThue
            WHERE pt.IDPhong = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Trả về đối tượng NguoiThueTro nếu tìm thấy dữ liệu
                return new NguoiThueTro(
                        rs.getInt("IDNguoiThue"), // Sử dụng getInt
                        rs.getInt("UserID"),     // Sử dụng getInt
                        rs.getString("FullName"),
                        rs.getString("Phone"),
                        rs.getString("CCCD")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không có dữ liệu
    }


    public static int getUserIDFromIdNguoiThuetro(int idNguoiThue) {
        int userId = -1; // Giá trị mặc định nếu không tìm thấy
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT UserID FROM NguoiThueTro WHERE IDNguoiThue = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idNguoiThue);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("UserID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    public static int getUserIDFromCCCD(String cccd) {
        int userId = -1; // Giá trị mặc định nếu không tìm thấy
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT UserID FROM NguoiThueTro WHERE CCCD = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cccd);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("UserID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

}