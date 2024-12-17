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
    public static Object[] getThongTinPhong(String id_room, String id_chutro) {
        System.out.println("Lấy thông tin phòng trọ");
        Object[] roomInfor = {"id_phong","id_chutro","id)_nguoithue","gia_dien","gia_nuoc","gia_rac","chi_phi_khac",123,"bla bla"};
  // 6-7-8 tùy thuộc vào tạo thong tin phòng như nèo
        return roomInfor;
    }
    public static Object[] getDataRoomFromDataBase(String id_room) {
        System.out.println(" lấy thông tin phòng phục vụ cập nhật data");

        // Thay thế kết quả roomData bằng hàm xử lý ở backend

        Object[] roomData = {"Tên Phòng","Địa chỉ","Giá phòng (VNĐ)","Giá điện (VNĐ)","Giá nước (VNĐ)","Số điện hiện tại","Số nước hiện tại","Giá rác (VNĐ)","Chi phí khác (VNĐ)"};
        return roomData;
    }

}
