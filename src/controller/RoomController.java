package controller;

import backend.connectDatabase;
import frontend.view.RoomUpdateInforRoomView;
import frontend.view.RoomUpdateNguoithueView;
import backend.model.NguoiThueTro;
import frontend.view.RoomView;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomController {
    // Hàm xử lý các hành động (cần triển khai thực tế trong Controller)
    public static void goToUpdateNguoiThue(JFrame frame, String idPhong) {
        //JOptionPane.showMessageDialog(frame, "Cập nhật người thuê cho phòng " + idPhong);
        new RoomUpdateNguoithueView(idPhong);
    }

    public static void updateInforRoom(String idPhong,String id_chutro) {
        //JOptionPane.showMessageDialog(frame, "Cập nhật thông tin phòng " + idPhong);
        new RoomUpdateInforRoomView(idPhong, id_chutro);
    }

    public static void goToUpdateHoaDon(JFrame frame, String idPhong) {
        JOptionPane.showMessageDialog(frame, "Cập nhật hóa đơn cho phòng " + idPhong);
    }

    public static void goToXuatHoaDon(JFrame frame, String idPhong) {
        JOptionPane.showMessageDialog(frame, "Xuất hóa đơn cho phòng " + idPhong);
    }

    public static void deletePhong(JFrame frame, String idPhong) {
        JOptionPane.showMessageDialog(frame, "Xóa phòng " + idPhong);
    }

    public void goToBackDashboard(JFrame frame, String id_chutro) {
        JOptionPane.showMessageDialog(frame, "Quay lại trang chính");
    }

    public static Object[] getThongTinPhong(String id_room, String id_chutro) {
        System.out.println("Lấy thông tin phòng trọ");
        Object[] roomInfor = {"id_phong", "id_chutro", "id)_nguoithue", "gia_dien", "gia_nuoc", "gia_rac", "chi_phi_khac", 123, "bla bla"};
        // 6-7-8 tùy thuộc vào tạo thong tin phòng như nèo
        return roomInfor;
    }

    public static Object[] getDataRoomFromDataBase(String id_room) {
        System.out.println(" lấy thông tin phòng phục vụ cập nhật data");

        // Thay thế kết quả roomData bằng hàm xử lý ở backend

        Object[] roomData = {"Tên Phòng", "Địa chỉ", "Giá phòng (VNĐ)", "Giá điện (VNĐ)", "Giá nước (VNĐ)", "Số điện hiện tại", "Số nước hiện tại", "Giá rác (VNĐ)", "Chi phí khác (VNĐ)"};
        return roomData;
    }

    public static void GoToUpdateNguoiThue(String cccdValue, JFrame frame, String idPhong) {
        // Khởi tạo đối tượng NguoiThueTro và gọi phương thức UpdateNguoiThue
        System.out.println(" đã gọi controller GoToUpdateNguoiThue");
        NguoiThueTro ngThueTro = new NguoiThueTro();
        NguoiThueTro.UpdateNguoiThueInRoom(cccdValue, idPhong, frame);
        // Update xong gửi thông báo về ==> Đã thêm người thuê trọ "name" vào"

    }

    public static ActionListener GoToBackRoomView(String id_room, String id_chutro, JPanel roomPanel) {
        //roomPanel.setVisible(false);
        new RoomView(id_room, id_chutro, roomPanel);

        return null;
    }

    public static String GetNameRoom(String id_room) {
        // lấy tên phòng -> xuất ra
        String name_room = "Phòng  01";
        return name_room;
    }

    public static int[] GetNumberElectricAndWater(String id_room) {

        // Hiếu viết hàm truy xuất database để lấy thông tin
        // Check ngày và Lấy ngày gần nhất.

        int oldNumberE = 100; // Số điện giả lập
        int oldNumberW = 50;  // Số nước giả lập

        int[] numberElecAndWater;
        return new int[]{oldNumberE, oldNumberW};
    }

    public static ActionListener GoToUpdateInfor(JTextField textE, JTextField textW, String id_room) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Lấy giá trị từ JTextField
                    int newElectric = Integer.parseInt(textE.getText());
                    int newWater = Integer.parseInt(textW.getText());

                    // In giá trị để kiểm tra
                    System.out.println("Số điện mới: " + newElectric);
                    System.out.println("Số nước mới: " + newWater);

                    // Gọi controller để xử lý cập nhật
                    UpdateRoomInfoWE(id_room, newElectric, newWater);
                    // Hiển thị thông báo thành công
                    JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    // Hiển thị lỗi nếu giá trị nhập vào không hợp lệ
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

    }
    //new function

    // Hiếu -- Function này có thể đưa vào Class Room
    public static void UpdateRoomInfoWE(String id_room, int newElectric, int newWater) {
        // Ví dụ cập nhật cơ sở dữ liệu
        System.out.println("Cập nhật thông tin phòng: " + id_room);
        System.out.println("Số điện mới: " + newElectric + ", Số nước mới: " + newWater);

        // Thực hiện truy vấn cập nhật
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE PhongTro SET SoDien = ?, SoNuoc = ? WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newElectric);
            pstmt.setInt(2, newWater);
            pstmt.setString(3, id_room);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Số dòng được cập nhật: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }









}
