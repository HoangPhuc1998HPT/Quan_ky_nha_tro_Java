package controller;


import backend.connectDatabase;
import backend.model.Chutro;
import frontend.view.admin.AdminShowAllChutroView;
import frontend.view.chutro.ChutroDashboardView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class  AdminController {

    // Load danh sách tài khoản chưa kích hoạt
    public static void loadInactiveUsers(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Username, Role, is_active FROM Users WHERE is_active = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("Username");
                String role = rs.getString("Role");
                boolean is_active = rs.getBoolean("is_active");
                model.addRow(new Object[]{username, role, is_active, "Active"});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách tài khoản chưa kích hoạt!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Xử lý kích hoạt tài khoản
    public static void activateUser(int rowIndex, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String username = (String) model.getValueAt(rowIndex, 0);

        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE Users SET is_active = 1 WHERE Username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Kích hoạt tài khoản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                model.removeRow(rowIndex); // Xóa dòng sau khi kích hoạt thành công
            } else {
                JOptionPane.showMessageDialog(null, "Kích hoạt tài khoản thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật trạng thái tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }



    public static void deleteUser(int row, JTable table) {
        // TODO: Thực hiện thao tác delete User trong table User

    }
    public static void goToAdminShowAllChutroView() {
        List<Object[]> chutroData = Chutro.getAllChutroData();
        new AdminShowAllChutroView(chutroData);
    }

    public static void goToChutroDashboardFromAdmin(String username) {
        new ChutroDashboardView(username); // Mở Dashboard
    }



    public static void disableChutro(String cccd) {
        System.out.println("đã tạm ngưng" + cccd);
    }


    public static void disableNguoiThueTro(String cccd) {
        // Logic vô hiệu hóa người thuê trọ dựa trên CCCD
        JOptionPane.showMessageDialog(null, "Đã tạm ngưng hoạt động người thuê với CCCD: " + cccd);
    }

    public static void goBackToAdminDashboard(JFrame currentFrame) {
        currentFrame.dispose();
        // Logic để quay lại Dashboard
        JOptionPane.showMessageDialog(null, "Quay lại Admin Dashboard");
    }


}
