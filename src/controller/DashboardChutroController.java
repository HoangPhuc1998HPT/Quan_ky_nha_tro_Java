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
    public static void go_to_show_information_churtro(String id_chutro) {
        System.out.println(" đi đến xem thông tin chủ trọ ");
        // Gọi backend xử lý get thông tin để truyển về cho viewshow thông tin
        Chutro chutro = Chutro.getChutrobyChutroID(id_chutro);
        if (chutro == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin chủ trọ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new ChutroInformationView(id_chutro, chutro);
        // Xử lý backend tra về 1 mảng chưa full thông tin chủ tr
        String chutroList = new String();

    }

    public static void go_to_create_room(String id_chutro) {
        System.out.println(" đi đên tạo view phòng trọ");
        new ChutroCreateRoomsView(id_chutro);
    }

    public static void go_to_show_list_room(String id_chutro) {
        System.out.println(" đi đến xem danh sách phòng trọ");
        new ChutroRoomsTableView(id_chutro);
    }

    public static void go_to_show_list_invoices(String idChutro) {
        System.out.println("Đi đến view xem danh sách tất cả hóa đơn thuộc chur trọ");
        String nameChutro = getNameChutroFromIdChutro(idChutro);
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

    public static void save_room_into_database(String name, String address, double roomPrice, double electricityPrice, double waterPrice, double garbagePrice, JFrame frame) {

        // Kiểm tra các thông tin đầy đủ ko thiếu dữ kiện
        // dẫn đến hàm lưu vào cơ sở dữ liệu, tại đó id_rooms được tạo và lưu thông tin vào các CSDL liên quan
        // cơ sở dữ liệu phòng trọ
        // id_rooms, id_chutro, các thông tin liên quan, vị trí id_nguoithuetro để trống
        boolean newRoom = Room.addRoom(name, address, roomPrice, electricityPrice, waterPrice, garbagePrice);
        if (newRoom) {
            JOptionPane.showMessageDialog(frame, "Đã lưu thông tin phòng trọ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Đã lưu thông tin phòng trọ");
        } else {
            JOptionPane.showMessageDialog(frame, "Thêm phòng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void   go_back_dashboardchutro(JFrame frame, String idChutro) {
        String username = getUsernameFromIdChutro(idChutro);
        frame.setVisible(false);
        //new ChutroDashboardView(username);

    }

}
