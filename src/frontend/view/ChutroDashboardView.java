package frontend.view;

import controller.DashboardChutroController;
import controller.LoginController;

import javax.swing.*;
import java.awt.*;

import static backend.model.chutro.get_id_chutro_from_username;

public class ChutroDashboardView {
    private final JFrame frame;

    public ChutroDashboardView(String entryUsername) {
        String id_chutro = get_id_chutro_from_username(entryUsername);
        frame = new JFrame("ChutroDashboardView");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        // Panel chính với GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel DashboarLabel = new JLabel("Cháo mừng ai đó đến với Dasboard Chủ trọ"); // Thêm tên chủ vào vị trí ai đó
        DashboarLabel.setFont(new Font("Be Vietnam Pro", Font.PLAIN,16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(DashboarLabel, gbc);

        frame.add(panel, BorderLayout.CENTER);
        // Tạo nút Xem thông tin chủ trọ
        JButton show_information_chutro = new JButton(" Xem thông tin chủ trọ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 2;
        show_information_chutro.addActionListener(e-> DashboardChutroController.go_to_show_information_churtro(entryUsername));
        panel.add(show_information_chutro, gbc);
        // Tạo nút tạo phòng trọ
        JButton create_rooms = new JButton("Tạo phòng trọ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        create_rooms.addActionListener(e-> DashboardChutroController.go_to_create_room(id_chutro));
        panel.add(create_rooms, gbc);
        // Tạo nút xem danh sách phòng trọ
        JButton show_list_rooms = new JButton(" Xem danh sách phòng trọ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        show_list_rooms.addActionListener(e->DashboardChutroController.go_to_show_list_room());
        panel.add(show_list_rooms, gbc);
        // Tạo nút xem danh sách hóa đơn
        JButton show_list_invoices = new JButton("Xem danh sách hóa đơn");
        gbc.gridx = 0;
        gbc.gridy = 4;
        show_list_invoices.addActionListener(e-> DashboardChutroController.go_to_show_list_invoices());
        panel.add(show_list_invoices, gbc);
        // Tao nút thoát ra login
        JButton logout = new JButton("Đăng xuất");
        gbc.gridx = 0;
        gbc.gridy = 5;
        logout.addActionListener(e-> LoginController.logout(frame));
        panel.add(logout, gbc);





        frame.setVisible(true);





    }

    public static void main(String[] args) {
        String entryUsername = "demo";
        SwingUtilities.invokeLater(() -> new ChutroDashboardView(entryUsername));
    }

}
