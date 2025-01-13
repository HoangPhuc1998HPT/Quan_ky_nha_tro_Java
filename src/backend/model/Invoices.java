package backend.model;

import backend.connectDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoices {
    private int billID;
    private int idPhong;
    private double tienNha;
    private double tienDien;
    private double tienNuoc;
    private double tienRac;
    private double chiPhiKhac;
    private double giamGia;
    private double tongChiPhi;
    private Date ngayXuatHoaDon;
    private int thanhToan;
    // Constructor đầy đủ
    public Invoices(int billID, int idPhong, double tienNha, double tienDien, double tienNuoc, double tienRac,
                    double chiPhiKhac, double giamGia, double tongChiPhi, Date ngayXuatHoaDon, int thanhToan) {
        this.billID = billID;
        this.idPhong = idPhong;
        this.tienNha = tienNha;
        this.tienDien = tienDien;
        this.tienNuoc = tienNuoc;
        this.tienRac = tienRac;
        this.chiPhiKhac = chiPhiKhac;
        this.giamGia = giamGia;
        this.tongChiPhi = tongChiPhi;
        this.ngayXuatHoaDon = ngayXuatHoaDon;
        this.thanhToan = thanhToan;
    }

    // Constructor rỗng
    public Invoices() {}




    // Getter và Setter
    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public double getTienNha() {
        return tienNha;
    }

    public void setTienNha(double tienNha) {
        this.tienNha = tienNha;
    }

    public double getTienDien() {
        return tienDien;
    }

    public void setTienDien(double tienDien) {
        this.tienDien = tienDien;
    }

    public double getTienNuoc() {
        return tienNuoc;
    }

    public void setTienNuoc(double tienNuoc) {
        this.tienNuoc = tienNuoc;
    }

    public double getTienRac() {
        return tienRac;
    }

    public void setTienRac(double tienRac) {
        this.tienRac = tienRac;
    }

    public double getChiPhiKhac() {
        return chiPhiKhac;
    }

    public void setChiPhiKhac(double chiPhiKhac) {
        this.chiPhiKhac = chiPhiKhac;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }

    public double getTongChiPhi() {
        return tongChiPhi;
    }

    public void setTongChiPhi(double tongChiPhi) {
        this.tongChiPhi = tongChiPhi;
    }

    public Date getNgayXuatHoaDon() {
        return ngayXuatHoaDon;
    }

    public void setNgayXuatHoaDon(Date ngayXuatHoaDon) {
        this.ngayXuatHoaDon = ngayXuatHoaDon;
    }

    public int getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(int thanhToan) {
        this.thanhToan = thanhToan;
    }




    // Hàm lấy thông tin hóa đơn mới nhất cho một phòng
    public static Object[] getInvoiceData(int idPhong) throws SQLException {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM HoaDon WHERE IDPhong = ? ORDER BY NgayXuatHoaDon DESC LIMIT 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPhong);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Object[]{
                        rs.getInt("BillID"),
                        rs.getInt("SodienUsed"),
                        rs.getInt("SonuocUsed"),
                        rs.getDate("NgayXuatHoaDon"),
                        rs.getDouble("TienNha"),
                        rs.getDouble("ChiPhiKhac"),
                        rs.getDouble("Tienrac"),
                        rs.getDouble("GiamGia"),
                        rs.getDouble("TongChiPhi")
                };
            }
        } catch (SQLException e) {
            throw new SQLException("Không thể lấy thông tin hóa đơn cho phòng ID: " + idPhong, e);
        }
        throw new SQLException("Không tìm thấy hóa đơn!");
    }

    // Hàm lấy giá điện cho một phòng
    public static double getGiaDien(int idPhong) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Giadien FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPhong);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Giadien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Giá trị mặc định nếu không có dữ liệu
    }

    // Hàm lấy giá nước cho một phòng
    public static double getGiaNuoc(int idPhong) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Gianuoc FROM TTPhongtro WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPhong);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Gianuoc");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Giá trị mặc định nếu không có dữ liệu
    }

    // Hàm tính tổng chi phí hóa đơn
    public static double calculateTongChiPhi(Object[] invoiceData, double giaDien, double giaNuoc) {
        double tongChiPhi = (int) invoiceData[1] * giaDien + // Tiền điện
                (int) invoiceData[2] * giaNuoc +             // Tiền nước
                (double) invoiceData[4] +                   // Tiền nhà
                (double) invoiceData[5] +                   // Chi phí khác
                (double) invoiceData[6] -                   // Tiền rác
                (double) invoiceData[7];                    // Giảm giá
        return tongChiPhi;
    }

    // Hàm tạo hóa đơn mới
    public static boolean createInvoice(Invoices invoice) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                INSERT INTO HoaDon (IDPhong, TienNha, TienDien, TienNuoc, TienRac, ChiPhiKhac, GiamGia, TongChiPhi, NgayXuatHoaDon, ThanhToan)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, invoice.getIdPhong());
            pstmt.setDouble(2, invoice.getTienNha());
            pstmt.setDouble(3, invoice.getTienDien());
            pstmt.setDouble(4, invoice.getTienNuoc());
            pstmt.setDouble(5, invoice.getTienRac());
            pstmt.setDouble(6, invoice.getChiPhiKhac());
            pstmt.setDouble(7, invoice.getGiamGia());
            pstmt.setDouble(8, invoice.getTongChiPhi());
            pstmt.setDate(9, new java.sql.Date(invoice.getNgayXuatHoaDon().getTime()));
            pstmt.setInt(10, invoice.getThanhToan());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // TODO: Invoice list view đã tạo, Hiếu kiểm tra lại logic xem truy xuất dữ liệu cho Invoices.InvoiceListsView  nha
    // Hàm lấy danh sách hóa đơn theo ID chủ trọ
    public static List<Object[]> getInvoiceList(int idChutro) {
        List<Object[]> invoices = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                SELECT 
                    ROW_NUMBER() OVER (ORDER BY hd.NgayXuatHoaDon DESC) AS STT,
                    pt.TenPhong,
                    nt.Hoten AS TenNguoiThue,
                    hd.TongChiPhi,
                    hd.NgayXuatHoaDon,
                    hd.ThanhToan
                FROM HoaDon hd
                JOIN TTPhongtro pt ON hd.IDPhong = pt.IDPhong
                LEFT JOIN NguoiThueTro nt ON pt.IDNguoiThue = nt.IDNguoiThue
                WHERE pt.IDChutro = ?
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idChutro);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                invoices.add(new Object[]{
                        rs.getInt("STT"),
                        rs.getString("TenPhong"),
                        rs.getString("TenNguoiThue"),
                        rs.getDouble("TongChiPhi"),
                        rs.getDate("NgayXuatHoaDon"),
                        rs.getInt("ThanhToan") == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán"
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    // Tổng số hóa đơn
    public static int getTotalInvoices(int idChutro) {
        int total = 0;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                SELECT COUNT(*) AS Total
                FROM HoaDon hd
                JOIN TTPhongtro pt ON hd.IDPhong = pt.IDPhong
                WHERE pt.IDChutro = ?
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idChutro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("Total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // Tổng số hóa đơn đã thanh toán
    public static int getPaidInvoices(int idChutro) {
        int total = 0;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                SELECT COUNT(*) AS Paid
                FROM HoaDon hd
                JOIN TTPhongtro pt ON hd.IDPhong = pt.IDPhong
                WHERE pt.IDChutro = ? AND hd.ThanhToan = 1
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idChutro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("Paid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // Tổng số hóa đơn chưa thanh toán
    public static int getUnpaidInvoices(int idChutro) {
        int total = 0;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                SELECT COUNT(*) AS Unpaid
                FROM HoaDon hd
                JOIN TTPhongtro pt ON hd.IDPhong = pt.IDPhong
                WHERE pt.IDChutro = ? AND hd.ThanhToan = 0
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idChutro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("Unpaid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // Tổng giá trị các hóa đơn
    public static double getTotalValue(int idChutro) {
        double totalValue = 0;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                SELECT SUM(hd.TongChiPhi) AS TotalValue
                FROM HoaDon hd
                JOIN TTPhongtro pt ON hd.IDPhong = pt.IDPhong
                WHERE pt.IDChutro = ?
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idChutro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                totalValue = rs.getDouble("TotalValue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalValue;
    }

    // Tổng giá trị các hóa đơn chưa thanh toán
    public static double getUnpaidValue(int idChutro) {
        double unpaidValue = 0;
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
                SELECT SUM(hd.TongChiPhi) AS UnpaidValue
                FROM HoaDon hd
                JOIN TTPhongtro pt ON hd.IDPhong = pt.IDPhong
                WHERE pt.IDChutro = ? AND hd.ThanhToan = 0
            """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idChutro);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                unpaidValue = rs.getDouble("UnpaidValue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unpaidValue;
    }

    public static List<Object[]> getInvoicesByTenantId(int TenantId) {
        List<Object[]> invoices = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT hd.NgayXuatHoaDon, 
                   hd.TongChiPhi, 
                   CASE WHEN hd.TongChiPhi > 0 THEN 0 ELSE 1 END AS DaThanhToan, 
                   hd.BillID
            FROM HoaDon hd
            JOIN TTPhongtro pt ON hd.IDPhong = pt.IDPhong
            WHERE pt.IDNguoiThue = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, TenantId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                invoices.add(new Object[]{
                        rs.getDate("NgayXuatHoaDon"), // Ngày Xuất Hóa Đơn
                        rs.getDouble("TongChiPhi"),  // Tổng Giá Trị
                        rs.getBoolean("DaThanhToan"), // Trạng Thái
                        rs.getInt("BillID")       // ID Hóa Đơn
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public static void loadInvoiceData(DefaultTableModel tableModel, int idChutro) {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ

        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
        SELECT ROW_NUMBER() OVER (ORDER BY hd.NgayXuatHoaDon DESC) AS STT,
               pt.TenPhong,
               COALESCE(nt.Hoten, 'Không có dữ liệu') AS TenNguoiThue,
               COALESCE(hd.TongChiPhi, 0) AS TongChiPhi,
               hd.NgayXuatHoaDon,
               CASE WHEN hd.ThanhToan = 1 THEN 'Đã Thanh Toán' ELSE 'Chưa Thanh Toán' END AS TinhTrang
        FROM HoaDon hd
        JOIN TTPhongtro pt ON hd.IDPhong = pt.IDPhong
        LEFT JOIN NguoiThueTro nt ON pt.IDNguoiThue = nt.IDNguoiThue
        WHERE pt.IDChutro = ?
        """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idChutro);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[] {
                        rs.getInt("STT"),
                        rs.getString("TenPhong"),
                        rs.getString("TenNguoiThue"),
                        String.format("%,.2f VNĐ", rs.getDouble("TongChiPhi")),
                        rs.getDate("NgayXuatHoaDon"),
                        rs.getString("TinhTrang")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Hàm lấy thông tin hóa đơn theo ID
    public static Invoices getInvoiceDetails(int billID) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM HoaDon WHERE BillID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, billID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Invoices(
                        rs.getInt("BillID"),
                        rs.getInt("IDPhong"),
                        rs.getDouble("TienNha"),
                        rs.getDouble("TienDien"),
                        rs.getDouble("TienNuoc"),
                        rs.getDouble("Tienrac"),
                        rs.getDouble("ChiPhiKhac"),
                        rs.getDouble("GiamGia"),
                        rs.getDouble("TongChiPhi"),
                        rs.getDate("NgayXuatHoaDon"),
                        rs.getInt("ThanhToan")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Hàm đánh dấu hóa đơn đã thanh toán
    public static void markInvoiceAsPaid(String idInvoice) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Cập nhật cột ThanhToan trong bảng HoaDon
            String sql = "UPDATE HoaDon SET ThanhToan = 1 WHERE BillID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idInvoice);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Đã cập nhật trạng thái thanh toán cho hóa đơn ID: " + idInvoice, "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với ID: " + idInvoice, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thể cập nhật trạng thái thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    //TODO: phía dưới đang test có thể có lỗi
    // Lấy thông tin cần thiết của hóa đơn từ idhoadon
    public static Invoices getInvoiceById(int idhoadon) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT hd.BillID, hd.IDPhong, hd.TienNha, hd.TienDien, hd.TienNuoc, hd.Tienrac, 
                   hd.ChiPhiKhac, hd.GiamGia, hd.TongChiPhi, hd.NgayXuatHoaDon, hd.ThanhToan
            FROM HoaDon hd
            WHERE hd.BillID = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idhoadon);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Invoices(
                        rs.getInt("BillID"),
                        rs.getInt("IDPhong"),
                        rs.getDouble("TienNha"),
                        rs.getDouble("TienDien"),
                        rs.getDouble("TienNuoc"),
                        rs.getDouble("tienrac"),
                        rs.getDouble("ChiPhiKhac"),
                        rs.getDouble("GiamGia"),
                        rs.getDouble("TongChiPhi"),
                        rs.getDate("NgayXuatHoaDon"),
                        rs.getInt("ThanhToan")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Trả về null nếu không tìm thấy hóa đơn
    }

    public static int getIdChutroFromIdhoadon(int idhoadon) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT TTPhongtro.IDChutro 
            FROM TTPhongtro
            JOIN HoaDon ON TTPhongtro.IDPhong = HoaDon.IDPhong
            WHERE HoaDon.BillID = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idhoadon);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("IDChutro");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu không tìm thấy
    }

    public static int getIdNguoiThueFromIdhoadon(int idhoadon) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT TTPhongtro.IDNguoiThue 
            FROM TTPhongtro
            JOIN HoaDon ON TTPhongtro.IDPhong = HoaDon.IDPhong
            WHERE HoaDon.BillID = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idhoadon);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("IDNguoiThue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu không tìm thấy
    }

    public static int getIdRoomFromIdhoadon(int idhoadon) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT IDPhong 
            FROM HoaDon
            WHERE BillID = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idhoadon);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("IDPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu không tìm thấy
    }

    public static List<Object[]> getAllInvoices() {
        List<Object[]> invoices = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
            SELECT 
                hd.BillID, 
                pt.TenPhong, 
                nt.Hoten AS TenNguoiThue, 
                hd.NgayXuatHoaDon, 
                hd.TongChiPhi,
                CASE 
                    WHEN hd.ThanhToan = 1 THEN 'Đã Thanh Toán'
                    ELSE 'Chưa Thanh Toán'
                END AS TinhTrang -- Tạo cột tạm từ cột ThanhToan
            FROM HoaDon hd
            JOIN TTPhongtro pt ON hd.IDPhong = pt.IDPhong
            LEFT JOIN NguoiThueTro nt ON pt.IDNguoiThue = nt.IDNguoiThue
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                invoices.add(new Object[]{
                        rs.getInt("BillID"),
                        rs.getString("TenPhong"),
                        rs.getString("TenNguoiThue"),
                        rs.getDate("NgayXuatHoaDon"),
                        rs.getDouble("TongChiPhi"),
                        rs.getString("TinhTrang") // Dữ liệu từ cột tạm
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public static int getIdHoadonFromidCTHD(int idCTHD) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = """
        SELECT hd.BillID 
        FROM HoaDon hd
        WHERE idCTHD = ?
        """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idCTHD);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("IDPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu không tìm thấy
    }




}
