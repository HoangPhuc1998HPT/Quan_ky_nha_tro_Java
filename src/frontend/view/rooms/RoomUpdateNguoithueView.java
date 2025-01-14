package frontend.view.rooms;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static controller.RoomController.*;

public class RoomUpdateNguoithueView {
    private JFrame frame;
    private int id_room;
    private int id_chutro;
    private JPanel jPanel;

    public RoomUpdateNguoithueView(int id_room) {
        this.id_room = id_room;

        // Tạo JFrame
        frame = new JFrame("Thêm người thuê trọ");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel title = new JLabel("Thêm người thuê trọ");
        title.setFont(new Font("Be Vietnam Pro", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);

        // Label cho trường nhập CCCD
        JLabel CCCDLabel = new JLabel("Nhập mã số CCCD:");
        CCCDLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(CCCDLabel, gbc);

        // Trường nhập CCCD
        JTextField CCCDField = new JTextField(20);
        CCCDField.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(CCCDField, gbc);

        // Label cho trường ngày bắt đầu thuê
        JLabel startDateLabel = new JLabel("Ngày bắt đầu thuê:");
        startDateLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(startDateLabel, gbc);

        // Trường nhập ngày bắt đầu thuê với gợi ý ngày hôm nay
        JTextField startDateField = new JTextField(20);
        startDateField.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        startDateField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))); // Gợi ý ngày hiện tại
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(startDateField, gbc);

        // Nút xác nhận
        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        confirmButton.addActionListener(e -> {
            String CCCDValue = CCCDField.getText().trim();
            String startDateValue = startDateField.getText().trim();

            // Chuyển đổi định dạng từ dd/MM/yyyy sang yyyy-MM-dd
            LocalDate localDate = LocalDate.parse(startDateValue, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Date sqlDate = Date.valueOf(localDate);

            // In thông tin để kiểm tra
            System.out.println("CCCD nhập vào: " + CCCDValue);
            System.out.println("Ngày bắt đầu thuê nhập vào: " + sqlDate);

            UpdateNguoiThue(CCCDValue, frame, id_room, sqlDate);
        });
        panel.add(confirmButton, gbc);

        // Nút quay lại
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridy = 4;
        backButton.addActionListener(e -> GoToBackRoomView(frame, id_room, id_chutro));
        panel.add(backButton, gbc);

        // Thêm panel vào JFrame
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
