package frontend.view;

import javax.swing.*;
import java.awt.*;

import static controller.DashboardChutroController.go_back_dashboardchutro;
// trả về 1 mảng truy cập thông tin của chủ trọ từ đó đưa thông tin vào các trường
public class ChutroInforView {
    public ChutroInforView(String id_chutro) {
        JFrame frame = new JFrame("Thông tin chủ trọ");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout để hỗ trợ GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.CENTER;

        // Label hiển thị nhãn họ tên
        JLabel lbTitle = new JLabel("Thông tin cá nhân của Chủ trọ" );
        lbTitle.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1 ;
        gbc.gridy = 0 ;
        panel.add(lbTitle, gbc);

        // Thành phần họ tên
        JLabel lblHoTen = new JLabel("Họ tên:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblHoTen, gbc);


        JTextField ho_ten = new JTextField(20);
        ho_ten.setEditable(false); // chỉ đọc
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(ho_ten, gbc);

        // Thành phần địa chỉ
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblDiaChi, gbc);

        JTextField dia_chi = new JTextField(20);
        dia_chi.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(dia_chi, gbc);

        // Thông tin CCCD
        JLabel lbCCCD = new JLabel("SỐ CCCD");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lbCCCD,gbc);

        JTextField CCCD = new JTextField(20);
        CCCD.setEditable(false); // chỉ đọc
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(CCCD, gbc);

        // Thông tin số điện thoại
        JLabel lbsdt = new JLabel("Phone");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lbsdt,gbc);

        JTextField sdt = new JTextField(20);
        sdt.setEditable(false); // chỉ đọc
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(CCCD, gbc);

        // Số phòng trọ đang quản lý
        JLabel lbcount_rooms = new JLabel("Tổng số phòng");
        gbc.gridx = 0 ;
        gbc.gridy = 5 ;
        panel.add(lbcount_rooms,gbc);

        JTextField count_rooms = new JTextField(20);
        count_rooms.setEditable(false); // chỉ đọc
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(CCCD, gbc);



        // Nút quay lại
        JButton button_back = new JButton("Quay lại");
        gbc.gridx = 1;
        gbc.gridy = 6;
        button_back.addActionListener(e -> go_back_dashboardchutro(frame,id_chutro ));
        panel.add(button_back, gbc);

        // Thêm panel vào frame
        frame.add(panel, BorderLayout.CENTER);

        // Hiển thị giao diện
        frame.setLocationRelativeTo(null); // Canh giữa màn hình
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        String id_chutro = "CT001";
        SwingUtilities.invokeLater(() -> new ChutroInforView(id_chutro));
    }
}
