package controller;

import frontend.view.ChutroCreateRoomsView;
import frontend.view.ChutroDashboardView;
import frontend.view.ChutroInforView;

import javax.swing.*;
import java.util.ArrayList;


public class DashboardChutroController {
    public static void go_to_show_information_churtro(String username) {
        System.out.println(" đi đến xem thông tin chủ trọ ");
        // Gọi backend xử lý get thông tin để truyển về cho viewshow thông tin
        // Xử lý backend tra về 1 mảng chưa full thông tin chủ tr
        String chutroList = new String();
        new ChutroInforView(chutroList);
    }

    public static void go_to_create_room(String id_chutro) {
        System.out.println(" đi đên tạo view phòng trọ");
        new ChutroCreateRoomsView(id_chutro);
    }

    public static void go_to_show_list_room() {
        System.out.println(" đi đến xem danh sách phòng trọ");
    }

    public static void go_to_show_list_invoices() {
        System.out.println("Đi đến view xem danh sách tất cả hóa đơn thuộc chur trọ");

    }
    public static void go_back_dashboardchutro(JFrame frame, String id_chutro){
        System.out.println(id_chutro);
        new ChutroDashboardView(id_chutro);
        frame.setVisible(false);
    }
    public static void save_room_into_database (String id_chutro){

        // Kiểm tra các thông tin đầy đủ ko thiếu dữ kiện
        // dẫn đến hàm lưu vào cơ sở dữ liệu, tại đó id_rooms được tạo và lưu thông tin vào các CSDL liên quan
        // cơ sở dữ liệu phòng trọ
        // id_rooms, id_chutro, các thông tin liên quan, vị trí id_nguoithuetro để trống
        System.out.println("Đã lưu thông tin phòng trọ");
    }
}
