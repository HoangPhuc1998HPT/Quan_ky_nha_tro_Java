package controller;

import javax.swing.*;
import java.awt.*;

public class RoomController {
    // Hàm xử lý các hành động (cần triển khai thực tế trong Controller)
    public static void goToUpdateNguoiThue(JFrame frame, String idPhong) {
        JOptionPane.showMessageDialog(frame, "Cập nhật người thuê cho phòng " + idPhong);
    }

    public static void updateInforRoom(JFrame frame, String idPhong) {
        JOptionPane.showMessageDialog(frame, "Cập nhật thông tin phòng " + idPhong);
    }

    public static void goToUpdateHoaDon(JFrame frame, String idPhong) {
        JOptionPane.showMessageDialog(frame, "Cập nhật hóa đơn cho phòng " + idPhong);
    }

    public static void goToXuatHoaDon(JFrame frame, String idPhong) {
        JOptionPane.showMessageDialog(frame, "Xuất hóa đơn cho phòng " + idPhong);
    }

    public static void deletePhong(JFrame frame,String idPhong) {
        JOptionPane.showMessageDialog(frame, "Xóa phòng " + idPhong);
    }

    public void goToBackDashboard(JFrame frame ,String id_chutro) {
        JOptionPane.showMessageDialog(frame, "Quay lại trang chính");
    }

}
