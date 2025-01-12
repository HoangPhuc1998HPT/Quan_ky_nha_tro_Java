package controller;

import backend.connectDatabase;
import backend.model.Chutro;
import backend.model.Room;
import frontend.view.Invoices.InvoiceListsView;
import frontend.view.chutro.ChutroCreateRoomsView;
import frontend.view.chutro.ChutroDashboardView;
import frontend.view.chutro.ChutroInformationView;
import frontend.view.chutro.ChutroRoomsTableView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static backend.model.Chutro.*;


public class DashboardChutroController {
    public static void go_to_show_information_churtro(int id_chutro) {
        System.out.println(" đi đến xem thông tin chủ trọ ");
        // Gọi backend xử lý get thông tin để truyển về cho viewshow thông tin
        Chutro chutro = Chutro.getChutrobyChutroID(id_chutro);
        if (chutro == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin chủ trọ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new ChutroInformationView(id_chutro, chutro);
        // Xử lý backend tra về 1 mảng chưa full thông tin chủ tr

    }

    public static void go_to_create_room(int id_chutro) {
        System.out.println("Đi đến giao diện tạo phòng trọ mới");
        new ChutroCreateRoomsView(id_chutro);
    }


    public static void go_to_show_list_room(int id_chutro) {
        System.out.println(" đi đến xem danh sách phòng trọ");

        new ChutroRoomsTableView(id_chutro);
    }

    public static void go_to_show_list_invoices(int idChutro) {
        String nameChutro = getNameChutroFromIdChutro(idChutro);
        System.out.println("Đi đến view xem danh sách tất cả hóa đơn thuộc chủ trọ" + nameChutro);
        int roomCount = getCountRoomFromIdChutro(idChutro);
        new InvoiceListsView(idChutro, nameChutro, roomCount);
        //InvoiceListsView(String idChutro, String landlordName, int roomCount)
    }

//    public static void go_back_dashboardchutro(JFrame frame, String id_chutro) {
//        System.out.println(id_chutro);
//        // đóng tab khi dashboard chủ trọ khi mở
//        //String username = getUsernameFromIdChutro(id_chutro); // trả về username
//        new ChutroDashboardView(getUsernameFromIdChutro(id_chutro));
//        frame.setVisible(false);
//    }
    public static void save_room_into_database(String name, String address, double roomPrice, double electricityPrice, double waterPrice, double garbagePrice, int id_chutro, JFrame frame) {
    // Kiểm tra các thông tin không được bỏ trống
    if (name.isEmpty() || address.isEmpty() || roomPrice <= 0 || electricityPrice <= 0 || waterPrice <= 0 || garbagePrice < 0) {
        JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ và hợp lệ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Lưu thông tin vào cơ sở dữ liệu
    boolean newRoom = Room.addRoom(name, address, roomPrice, electricityPrice, waterPrice, garbagePrice, id_chutro);
    if (newRoom) {
        JOptionPane.showMessageDialog(frame, "Đã lưu thông tin phòng trọ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Đã lưu thông tin phòng trọ: " + name);
        frame.setVisible(false);
    } else {
        JOptionPane.showMessageDialog(frame, "Thêm phòng thất bại! Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        System.err.println("Không thể lưu thông tin phòng trọ: " + name);
    }
}



    public static void   go_back_dashboardchutro(JFrame frame, int idChutro) {
        String username = getUsernameFromIdChutro(idChutro);
        frame.setVisible(false);
        //new ChutroDashboardView(username);

    }

}
