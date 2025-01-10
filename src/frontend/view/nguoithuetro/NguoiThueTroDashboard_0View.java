// NguoiThueTroDashboard_0View.java
package frontend.view.nguoithuetro;

import backend.model.NguoiThueTro;
import backend.model.Room;
import frontend.view.login_register.loginView;
import frontend.view.rooms.RoomInforView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NguoiThueTroDashboard_0View extends JFrame {
    private String userId;
    private JPanel mainPanel;

    public NguoiThueTroDashboard_0View(String userId) {
        this.userId = userId;
        setTitle("Người Thuê Trọ Chưa Có Phòng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Dashboard Người Thuê Trọ", SwingConstants.CENTER);
        label.setBounds(250, 10, 300, 30);
        mainPanel.add(label);

        // Hiển thị thông tin người thuê trọ
        NguoiThueTro tenant = NguoiThueTro.getNguoiThueTroByUserId(userId);
        JLabel nameLabel = new JLabel("Tên: " + (tenant != null ? tenant.getFullName() : "Không xác định"));
        nameLabel.setBounds(20, 50, 300, 25);
        mainPanel.add(nameLabel);

        JLabel phoneLabel = new JLabel("Số điện thoại: " + (tenant != null ? tenant.getPhone() : "Không xác định"));
        phoneLabel.setBounds(20, 80, 300, 25);
        mainPanel.add(phoneLabel);

        JLabel cccdLabel = new JLabel("CCCD: " + (tenant != null ? tenant.getCCCD() : "Không xác định"));
        cccdLabel.setBounds(20, 110, 300, 25);
        mainPanel.add(cccdLabel);

        // Bảng hiển thị gợi ý phòng
        JLabel tableLabel = new JLabel("Danh sách phòng trống:");
        tableLabel.setBounds(20, 150, 200, 25);
        mainPanel.add(tableLabel);

        JTable roomTable = new JTable();
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBounds(20, 180, 750, 200);
        mainPanel.add(scrollPane);

        // Load dữ liệu phòng
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Tên Phòng", "Người liên hệ", "Số điện thoại", "Địa chỉ", "Xem thông tin"}, 0);
        List<Object[]> rooms = Room.getEmptyRoomsForTenant(userId);
        for (Object[] room : rooms) {
            Object[] row = new Object[5];
            //room[0] = roomId
            row[0] = room[1]; // Tên Phòng
            row[1] = room[3]; // Người liên hệ
            row[2] = room[4]; // Số điện thoại
            row[3] = room[2]; // Địa chỉ

            JButton infoButton = new JButton("Xem");
            infoButton.addActionListener(e -> {
                String roomId = (String) room[0];
                new RoomInforView(roomId);
            });
            row[4] = infoButton;

            tableModel.addRow(row);
        }
        roomTable.setModel(tableModel);

        // Nút Đăng xuất
        JButton logoutBtn = new JButton("Đăng xuất");
        logoutBtn.setBounds(680, 400, 100, 30);
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new loginView();
            }
        });
        mainPanel.add(logoutBtn);

        setVisible(true);
    }

    public static void main(String[] args) {
        new NguoiThueTroDashboard_0View("user1");
    }
}
