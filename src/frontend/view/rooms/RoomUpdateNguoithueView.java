package frontend.view.rooms;

import javax.swing.*;
import java.awt.*;

import static controller.RoomController.*;

public class RoomUpdateNguoithueView {
    private JFrame frame;
    private int id_room;
    private int id_chutro;
    private JPanel jPanel;

    public RoomUpdateNguoithueView(int id_room) {
        this.id_room = id_room;


        // Tạo giao diện thêm người thuê
        frame = new JFrame("Thêm người thuê trọ");
        frame.setSize(400, 400);
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

        // Nút xác nhận
        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        confirmButton.addActionListener(e -> {
            String CCCDValue = CCCDtext.getText().trim(); // Lấy giá trị CCCD tại thời điểm nhấn nút
            System.out.println("CCCD nhập vào: " + CCCDValue);
            UpdateNguoiThue(CCCDValue, frame, id_room);
        });
        panel.add(confirmButton, gbc);

        // Nút quay lại

        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        backButton.addActionListener(e->GoToBackRoomView(frame,id_room, id_chutro));
        panel.add(backButton, gbc);



        frame.add(panel, gbc);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
