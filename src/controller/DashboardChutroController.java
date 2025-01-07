package controller;

import backend.connectDatabase;
import frontend.view.ChutroCreateRoomsView;
import frontend.view.ChutroDashboardView;
import frontend.view.ChutroInformationView;
import frontend.view.ChutroRoomsTableView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static backend.model.Chutro.getUsernameFromIdChutro;


public class DashboardChutroController {
    public static void go_to_show_information_churtro(String id_chutro) {
        System.out.println(" đi đến xem thông tin chủ trọ ");
        // Gọi backend xử lý get thông tin để truyển về cho viewshow thông tin

        new ChutroInformationView(id_chutro);
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

    public static void go_to_show_list_invoices() {
        System.out.println("Đi đến view xem danh sách tất cả hóa đơn thuộc chur trọ");

    }

    public static void go_back_dashboardchutro(JFrame frame, String id_chutro) {
        System.out.println(id_chutro);
        // đóng tab khi dashboard chủ trọ khi mở
        //String username = getUsernameFromIdChutro(id_chutro); // trả về username
        new ChutroDashboardView(getUsernameFromIdChutro(id_chutro));
        frame.setVisible(false);
    }

    public static void save_room_into_database(String id_chutro) {

        // Kiểm tra các thông tin đầy đủ ko thiếu dữ kiện
        // dẫn đến hàm lưu vào cơ sở dữ liệu, tại đó id_rooms được tạo và lưu thông tin vào các CSDL liên quan
        // cơ sở dữ liệu phòng trọ
        // id_rooms, id_chutro, các thông tin liên quan, vị trí id_nguoithuetro để trống
        System.out.println("Đã lưu thông tin phòng trọ");
    }


    public static List<String[]> getRoomList(String id_chutro) {
        List<String[]> roomList = new ArrayList<>();
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Truy vấn SQL
            String sql = """
            SELECT 
                TTPhongtro.IDPhong,
                TTPhongtro.TenPhong,
                ISNULL(NguoiThueTro.Hoten, N'Không có') AS TenNguoiThue
            FROM TTPhongtro
            LEFT JOIN NguoiThueTro ON TTPhongtro.IDPhong = NguoiThueTro.IDnguoithue
            WHERE TTPhongtro.IDChutro = ?
        """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_chutro); // Gán giá trị IDChutro
            ResultSet rs = pstmt.executeQuery();

            // Lấy dữ liệu và thêm vào danh sách
            while (rs.next()) {
                String idPhong = rs.getString("IDPhong");
                String tenPhong = rs.getString("TenPhong");
                String tenNguoiThue = rs.getString("TenNguoiThue");
                roomList.add(new String[]{idPhong, tenPhong, tenNguoiThue, "Xem Chi Tiết"});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomList;
    }
}
