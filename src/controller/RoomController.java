package controller;

import backend.connectDatabase;
//import frontend.view.rooms.RoomUpdateInforRoomView;
import backend.model.Chutro;
import backend.model.Room;
import frontend.view.Invoices.InvoiceBefoeSentToNguoiThueView;
import frontend.view.Invoices.InvoiceDetailUpdateView;
import frontend.view.rooms.RoomInforView;
import frontend.view.rooms.RoomUpdateInforRoomView;
import frontend.view.rooms.RoomUpdateNguoithueView;
import frontend.view.rooms.RoomView;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static backend.model.InvoiceDetail.*;
import static backend.model.Room.updateNguoiThueTroInRoom;

public class RoomController {
    // Hàm xử lý các hành động (cần triển khai thực tế trong Controller)
    public static void goToUpdateNguoiThue(JFrame frame, int idPhong) {
        //JOptionPane.showMessageDialog(frame, "Cập nhật người thuê cho phòng " + idPhong);
        frame.setVisible(false);
        new RoomUpdateNguoithueView(idPhong);
    }

    public static void updateInforRoom(JFrame frame, int idPhong,int id_chutro) {
        //JOptionPane.showMessageDialog(frame, "Cập nhật thông tin phòng " + idPhong);
        frame.setVisible(false);
        new RoomUpdateInforRoomView(idPhong,id_chutro);
    }

    public static void goToUpdateHoaDon(JFrame frame, int id_room,int id_chutro) {
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

    // TODO: đang lỗi ở đây nè
    public static void goToXuatHoaDon(JFrame frame, int idPhong) {

        new InvoiceBefoeSentToNguoiThueView(idPhong);
        JOptionPane.showMessageDialog(frame, "Xuất hóa đơn cho phòng " + idPhong);
    }


    public static Object[] getThongTinPhong(int id_room, int id_chutro) {
        System.out.println("Lấy thông tin phòng trọ");
        Object[] roomInfor = {"id_phong", "id_chutro", "id)_nguoithue", "gia_dien", "gia_nuoc", "gia_rac", "chi_phi_khac", 123, "bla bla"};
        // 6-7-8 tùy thuộc vào tạo thong tin phòng như nèo
        return roomInfor;
    }

    public static Object[] getDataRoomFromDataBase(int id_room) {
        System.out.println(" lấy thông tin phòng phục vụ cập nhật data");

        // Thay thế kết quả roomData bằng hàm xử lý ở backend

        Object[] roomData = {"Tên Phòng", "Địa chỉ", "Giá phòng (VNĐ)", "Giá điện (VNĐ)", "Giá nước (VNĐ)", "Số điện hiện tại", "Số nước hiện tại", "Giá rác (VNĐ)", "Chi phí khác (VNĐ)"};
        return roomData;
    }

    public static void GoToUpdateNguoiThue(String cccdValue, JFrame frame, int idPhong) {
        // Khởi tạo đối tượng NguoiThueTro và gọi phương thức UpdateNguoiThue
        //System.out.println(" đã gọi controller GoToUpdateNguoiThue");
        //NguoiThueTro ngThueTro = new NguoiThueTro();guoiThueTro.UpdateNguoiThueInRoom(cccdValue, idPhong, frame);
        // Update xong gửi thông báo về ==> Đã thêm người thuê trọ "name" vào"
        updateNguoiThueTroInRoom(idPhong, cccdValue);
        frame.setVisible(false);
    }

    public static ActionListener GoToBackRoomView(JFrame frame, int id_room, int id_chutro) {
        frame.setVisible(false);
        new RoomView(id_room, id_chutro);

        return null;
    }

    //new function

    // Hiếu -- Function này có thể đưa vào Class Room
    public static void UpdateRoomInfoWE(int id_room, int newElectric, int newWater) {
        // Ví dụ cập nhật cơ sở dữ liệu
        System.out.println("Cập nhật thông tin phòng: " + id_room);
        System.out.println("Số điện mới: " + newElectric + ", Số nước mới: " + newWater);

        // Thực hiện truy vấn cập nhật
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE PhongTro SET SoDien = ?, SoNuoc = ? WHERE IDPhong = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newElectric);
            pstmt.setInt(2, newWater);
            pstmt.setInt(3, id_room);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Số dòng được cập nhật: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRoomPrice(int roomId, double newValue, String type) {
        // Cập nhật giá phòng vào database
        System.out.println("Cập nhật " + type + " cho phòng " + roomId + " thành: " + newValue);
        // TODO: Thêm logic cập nhật database
        // TODO: Hiếu xử lý logic trong Class ROOM để add vào chổ này nha
    }
    public static void GoToBackRoomViewFromUpdate(JFrame frame, int id_room, int id_chutro) {
        frame.setVisible(false);
        new RoomView(id_room,id_chutro);
    }
    // Lấy danh sách phòng dựa trên ID chủ trọ
    public static List<String[]> getRoomListByChutro(int idChutro) {
        return Chutro.getRoomList(idChutro);
    }

    // Lấy RoomID dựa trên tên phòng và ID chủ trọ
    public static String getRoomIdByName(String roomName, int idChutro) {
        List<String[]> roomList = getRoomListByChutro(idChutro);
        for (String[] room : roomList) {
            if (room[1].equals(roomName)) {
                return room[0]; // RoomID ở cột 0
            }
        }
        return null;
    }

    // Mở RoomView cho một phòng cụ thể
    public static void openRoomView(int roomId, int idChutro) {
        new RoomView(roomId, idChutro);
    }


    public static double getGarbageFee (int id_phong){
        // TODo: Truy xuất từ Table HoaDon, nếu chưa có thì cho bằng 0. Khi cập nhật cho số hóa đơn gần nhất
        return 0;
    }


    public static void deletePhong(JFrame frame, int idRoom) {
        int confirm = JOptionPane.showConfirmDialog(
                frame,
                "Bạn có chắc chắn muốn xóa phòng này không?",
                "Xác nhận xóa phòng",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            boolean isDeleted = Room.deleteRoom(idRoom);
            if (isDeleted) {
                JOptionPane.showMessageDialog(frame, "Xóa phòng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); // Đóng frame sau khi xóa
            } else {
                JOptionPane.showMessageDialog(frame, "Xóa phòng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void goToBackRoomView(JFrame frame, int idRoom, int idChutro) {
        frame.dispose();
        new frontend.view.rooms.RoomView(idRoom, idChutro); // Điều hướng quay lại RoomView
    }


    public static void goToRoomInforView(int roomId) {
//        Room room = Room.getRoomInForViewbyRoomId(roomId);
//        new RoomInforView(room);
    }
}
