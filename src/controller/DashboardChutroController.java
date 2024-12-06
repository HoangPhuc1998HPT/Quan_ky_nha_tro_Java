package controller;

import frontend.view.ChutroInforView;

import java.util.ArrayList;

public class DashboardChutroController {
    public static void go_to_show_information_churtro(String username) {
        System.out.println(" đi đến xem thông tin chủ trọ ");
        // Gọi backend xử lý get thông tin để truyển về cho viewshow thông tin
        // Xử lý backend tra về 1 mảng chưa full thông tin chủ tr
        ArrayList<Object> chutroList = new ArrayList<>();
        new ChutroInforView(chutroList);
    }

    public static void go_to_create_room() {
        System.out.println(" đi đên tạo view phòng trọ");
    }

    public static void go_to_show_list_room() {
        System.out.println(" đi đến xem danh sách phòng trọ");
    }

    public static void go_to_show_list_invoices() {
        System.out.println("Đi đến view xem danh sách tất cả hóa đơn thuộc chur trọ");

    }
}
