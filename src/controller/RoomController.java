package controller;

import backend.connectDatabase;
import backend.model.InvoiceDetail;
//import frontend.view.RoomUpdateInforRoomView;
import backend.model.Room;
import frontend.view.InvoiceDetailUpdateView;
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

import static backend.model.InvoiceDetail.*;
import static frontend.view.RoomView.*;

public class RoomController {
    // Hàm xử lý các hành động (cần triển khai thực tế trong Controller)
    public static void goToUpdateNguoiThue(JFrame frame, String idPhong) {
        //JOptionPane.showMessageDialog(frame, "Cập nhật người thuê cho phòng " + idPhong);
        frame.setVisible(false);
        new RoomUpdateNguoithueView(idPhong);
    }

    public static void updateInforRoom(JFrame frame, String idPhong,String id_chutro) {
        //JOptionPane.showMessageDialog(frame, "Cập nhật thông tin phòng " + idPhong);
        frame.setVisible(false);
        new RoomUpdateInforRoomView(idPhong);
    }

    public static void goToUpdateHoaDon(JFrame frame, String id_room,String id_chutro) {
        try {
            // Lấy thông tin từ database
            String roomName = getRoomName(id_room); // Hàm để lấy tên phòng từ database
            String tenantName = getTenantName(id_room); // Hàm để lấy tên người thuê từ database
            String startDate = getStartDate(id_room); // Hàm để lấy ngày bắt đầu thuê từ database
            int oldElectric = getOldElectricReading(id_room); // Hàm để lấy số điện cũ
            int oldWater = getOldWaterReading(id_room); // Hàm để lấy số nước cũ
            String lastPaymentDate = getLastPaymentDate(id_room); // Hàm để lấy ngày thu tiền nhà tháng trước

            // Ẩn frame hiện tại
            frame.setVisible(false);

            // Hiển thị giao diện cập nhật chi tiết hóa đơn
            new InvoiceDetailUpdateView(id_chutro, id_room, roomName, tenantName, startDate, oldElectric, oldWater, lastPaymentDate);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Không thể tải dữ liệu phòng: " + id_room, "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    public static void goToXuatHoaDon(JFrame frame, String idPhong) {
        JOptionPane.showMessageDialog(frame, "Xuất hóa đơn cho phòng " + idPhong);
    }

    public static void deletePhong(JFrame frame, String idPhong) {
        JOptionPane.showMessageDialog(frame, "Xóa phòng " + idPhong);
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

    public static ActionListener GoToBackRoomView(JFrame frame, String id_room, String id_chutro) {
        frame.setVisible(false);
        new RoomView(id_room, id_chutro);

        return null;
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
    public Room getRoomDetails(String roomId) {
        // Truy vấn dữ liệu phòng từ database (giả lập)
        return new Room(roomId, "Phòng A", "Nguyễn Văn A");
    }

    public InvoiceDetail getRoomPrices(String roomId) {
        // Truy vấn giá phòng từ database (giả lập)
        return new InvoiceDetail(3000000, 3000, 15000, 50000);
    }

    public static void updateRoomPrice(String roomId, double newValue, String type) {
        // Cập nhật giá phòng vào database
        System.out.println("Cập nhật " + type + " cho phòng " + roomId + " thành: " + newValue);
        // TODO: Thêm logic cập nhật database
    }
    public static void GoToBackRoomViewFromUpdate(JFrame frame, String id_room, String id_chutro) {
        frame.setVisible(false);
        new RoomView(id_room,id_chutro);
    }









}
