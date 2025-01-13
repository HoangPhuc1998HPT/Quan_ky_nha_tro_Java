package frontend.view.chutro;

import backend.model.Chutro;
import controller.DashboardChutroController;
import controller.LoginController;

import javax.swing.*;
import java.awt.*;

import static backend.model.Chutro.getChutrobyChutroID;
import static backend.model.Chutro.getIdChutroByUsername;


public class ChutroDashboardView {
    private JFrame frame;


    public ChutroDashboardView(String entryUsername) {
        // entryUsername == tên tài khoản == Username được truyền vào khi đăng nhập


        // lấy USER ID trước khi lấy đối tượng Chutro

        int id_chutro = getIdChutroByUsername(entryUsername);
        System.out.println(id_chutro);


        Chutro chutro = getChutrobyChutroID(id_chutro);
        System.out.println(chutro);
        if (chutro == null) {
            JOptionPane.showMessageDialog(null,"Không tìm thấy chủ trọ");
            return;
        }

        frame = new JFrame("ChutroDashboardView");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        // Panel chính với GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String chuTroFullName = Chutro.getFullNameByUsername(entryUsername);
        System.out.println("Chu tro name: " + chuTroFullName);
        JLabel DashboarLabel = new JLabel("Chào đến với Dasboard Chủ trọ: " + chuTroFullName); // Thêm tên chủ vào vị trí ai đó
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
        show_information_chutro.addActionListener(e-> DashboardChutroController.go_to_show_information_churtro(id_chutro));
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
        show_list_rooms.addActionListener(e->DashboardChutroController.go_to_show_list_room(id_chutro));
        panel.add(show_list_rooms, gbc);

        // Tạo nút xem danh sách hóa đơn
        JButton show_list_invoices = new JButton("Xem danh sách hóa đơn");
        gbc.gridx = 0;
        gbc.gridy = 4;
        show_list_invoices.addActionListener(e-> DashboardChutroController.go_to_show_list_invoices(id_chutro));
        panel.add(show_list_invoices, gbc);

        // Tao nút thoát ra login
        JButton logout = new JButton("Đăng xuất");
        gbc.gridx = 0;
        gbc.gridy = 5;
        logout.addActionListener(e-> LoginController.logout(frame));
        panel.add(logout, gbc);

        JButton exist = new JButton("Thoát");
        gbc.gridx = 0;
        gbc.gridy = 6;
        exist.addActionListener(e->frame.setVisible(false));
        panel.add(exist,gbc);

        frame.setVisible(true);





    }



}
