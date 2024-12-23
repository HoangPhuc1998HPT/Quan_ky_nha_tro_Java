package frontend.view;

import javax.swing.*;
import java.awt.*;

import static controller.RoomController.GoToUpdateNguoiThue;

public class RoomUpdateNguoithueView {
    private JFrame frame;
    private String id_room;
    private String id_chutro;

    public RoomUpdateNguoithueView(String id_room) {
        this.id_room = id_room;
        this.id_chutro = "DefaultID"; // Khởi tạo id_chutro với giá trị mặc định

        // Tạo giao diện thêm người thuê
        frame = new JFrame("Thêm người thuê trọ");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel CCCDtitle = new JLabel("Nhập mã số Căn Cước Công Dân");
        CCCDtitle.setFont(new Font("Be Vietnam Pro", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(CCCDtitle, gbc);

        // Label cho trường nhập CCCD
        JLabel CCCDTlb = new JLabel("Nhập mã số CCCD");
        CCCDTlb.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa
        gbc.fill = GridBagConstraints.NONE;
        panel.add(CCCDTlb, gbc);

        // Trường nhập CCCD
        JTextField CCCDtext = new JTextField(20);
        CCCDtext.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(CCCDtext, gbc);

        // lấy text nhập từ trường CCCDtext
        String CCCDValue = CCCDtext.getText();

        // Nút xác nhận
        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        confirmButton.addActionListener(e->GoToUpdateNguoiThue(CCCDValue.trim(),frame,id_room));
        panel.add(confirmButton, gbc);

        frame.add(panel, gbc);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RoomUpdateNguoithueView("CT002");
        });
    }
}
