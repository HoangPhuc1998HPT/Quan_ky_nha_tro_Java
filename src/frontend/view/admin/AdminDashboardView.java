package frontend.view.admin;

import controller.AdminController;
import frontend.components.ButtonEditor;
import frontend.components.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminDashboardView extends JFrame {
    public AdminDashboardView() {
        setTitle("Admin Dashboard");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sử dụng GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL; // Mở rộng theo chiều ngang

        // Tiêu đề
        JLabel titleLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Chiếm 2 cột
        add(titleLabel, gbc);

        // Khu vực nút chức năng
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 hàng, 2 cột, khoảng cách 10px

        JButton btnShowChutro = new JButton("Danh sách chủ trọ");
        btnShowChutro.addActionListener(e -> new AdminShowAllChutroView(null));
        buttonPanel.add(btnShowChutro);

        JButton btnShowPhongTro = new JButton("Danh sách phòng trọ");
        btnShowPhongTro.addActionListener(e -> new AdminShowAllPhongTroView(null));
        buttonPanel.add(btnShowPhongTro);

        JButton btnShowNguoiThueTro = new JButton("Danh sách người thuê trọ");
        btnShowNguoiThueTro.addActionListener(e -> new AdminShowAllNguoiThueTroView(null));
        buttonPanel.add(btnShowNguoiThueTro);

        JButton btnShowHoadon = new JButton("Danh sách hóa đơn");
        btnShowHoadon.addActionListener(e -> new AdminShowAllHoadonView(null));
        buttonPanel.add(btnShowHoadon);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Tiêu đề danh sách tài khoản chưa active
        JLabel inactiveUsersLabel = new JLabel("Danh sách tài khoản chưa kích hoạt:");
        inactiveUsersLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(inactiveUsersLabel, gbc);



        // Bảng hiển thị danh sách tài khoản chưa kích hoạt
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Username", "Role", "IsActive", "Action"}, 0);
        JTable inactiveUsersTable = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Chỉ cho phép chỉnh sửa cột Action
            }
        };

        // Sử dụng ButtonRenderer và ButtonEditor
        inactiveUsersTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        inactiveUsersTable.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(inactiveUsersTable));

        // Load danh sách tài khoản chưa kích hoạt
        AdminController.loadInactiveUsers(inactiveUsersTable);

        // Thêm bảng vào giao diện
        JScrollPane scrollPane = new JScrollPane(inactiveUsersTable);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH; // Mở rộng cả chiều ngang và chiều dọc
        gbc.weightx = 1.0; // Cho phép mở rộng ngang
        gbc.weighty = 1.0; // Cho phép mở rộng dọc
        add(scrollPane, gbc);

        setLocationRelativeTo(null); // Căn giữa màn hình
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminDashboardView::new);
    }
}
