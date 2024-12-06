package frontend.view;

import controller.DashboardChutroController;
import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class ChutroDashboardView {
    private final JFrame frame;

    public ChutroDashboardView() {
        frame = new JFrame("ChutroDashboardView");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Giữ các thành phần ở giữa
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel DashboarLabel = new JLabel("Cháo mừng ai đó đến với Dasboard Chủ trọ");
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
        show_information_chutro.addActionListener(e-> DashboardChutroController.go_to_show_information_churtro());
        panel.add(show_information_chutro, gbc);
        // Tạo nút tạo phòng trọ
        JButton create_rooms = new JButton("Tạo phòng trọ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        create_rooms.addActionListener(e-> DashboardChutroController.go_to_create_room());
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
        SwingUtilities.invokeLater(ChutroDashboardView::new);
    }

}
