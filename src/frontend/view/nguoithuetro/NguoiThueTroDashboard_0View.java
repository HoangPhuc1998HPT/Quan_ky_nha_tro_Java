// NguoiThueTroDashboard_0View.java
package frontend.view.nguoithuetro;

import backend.model.NguoiThueTro;
import backend.model.Room;
import frontend.components.NguoiThueTro.ButtonEditorNguoiThueTro_0;
import frontend.components.NguoiThueTro.ButtonRendererNguoiThueTro_0;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class NguoiThueTroDashboard_0View extends JFrame {
    private int userId;
    private JPanel mainPanel;

    public NguoiThueTroDashboard_0View(int userId) {
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
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Room ID (Ẩn)", "Tên Phòng", "Người liên hệ", "Số điện thoại", "Địa chỉ", "Xem thông tin"}, 0);
        List<Object[]> rooms = Room.getEmptyRoomsForTenant(userId);
        for (Object[] room : rooms) {
            tableModel.addRow(new Object[]{
                    room[0],  // Room ID
                    room[1],  // Tên Phòng
                    room[4],  // Người liên hệ
                    room[5],  // Số điện thoại
                    room[3],  // Địa chỉ
                    "Xem"     // Nút xem thông tin
            });
        }
        roomTable.setModel(tableModel);

        // Ẩn cột Room ID
        roomTable.getColumnModel().getColumn(0).setMinWidth(0);
        roomTable.getColumnModel().getColumn(0).setMaxWidth(0);
        roomTable.getColumnModel().getColumn(0).setWidth(0);


        // Áp dụng Renderer và Editor cho nút "Xem thông tin"
        roomTable.getColumn("Xem thông tin").setCellRenderer(new ButtonRendererNguoiThueTro_0());
        roomTable.getColumn("Xem thông tin").setCellEditor(new ButtonEditorNguoiThueTro_0(new JCheckBox(), userId));

        // Nút Đăng xuất
        JButton logoutBtn = new JButton("Đăng xuất");
        logoutBtn.setBounds(680, 400, 100, 30);
        logoutBtn.addActionListener(e -> dispose());
        mainPanel.add(logoutBtn);

        setVisible(true);
    }
//    public static void main(String[] args) {
//        new NguoiThueTroDashboard_0View("18");
//    }
}


