package frontend.view.admin;


import backend.model.NguoiThueTro;
import frontend.components.ButtonEditorNguoiThueTro;
import frontend.components.ButtonRendererNguoiThueTro;
import controller.AdminController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminShowAllNguoiThueTroView extends JFrame {
    public AdminShowAllNguoiThueTroView(List<Object[]> nguoithueData) {
        setTitle("Danh sách Người Thuê Trọ");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("DANH SÁCH NGƯỜI THUÊ TRỌ", SwingConstants.CENTER);
        title.setFont(new Font("Be Vietnam Pro", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        String[] columnNames = {"ID", "STT", "Họ Tên", "Số Điện Thoại", "CCCD", "Chức năng"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Chỉ cột "Chức năng" có thể chỉnh sửa
            }
        };

        for (int i = 0; i < nguoithueData.size(); i++) {
            Object[] row = new Object[6];
            row[0] = nguoithueData.get(i)[0]; // ID (ẩn)
            row[1] = i + 1; // STT
            row[2] = nguoithueData.get(i)[1]; // Họ Tên
            row[3] = nguoithueData.get(i)[2]; // Số Điện Thoại
            row[4] = nguoithueData.get(i)[3]; // CCCD
            row[5] = "Xem chi tiết";
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        table.getColumn("Chức năng").setCellRenderer(new ButtonRendererNguoiThueTro());
        table.getColumn("Chức năng").setCellEditor(new ButtonEditorNguoiThueTro(new JCheckBox()));
        table.removeColumn(table.getColumnModel().getColumn(0)); // Ẩn cột ID

        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        JButton buttonSetVisibleFalse = new JButton("Tạm ngưng hoạt động người thuê");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonSetVisibleFalse.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String cccd = table.getValueAt(selectedRow, 4).toString();
                AdminController.disableNguoiThueTro(cccd);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một người thuê để tạm ngưng hoạt động!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(buttonSetVisibleFalse, gbc);

        JButton buttonBackAdminDash = new JButton("Quay lại");
        gbc.gridx = 1;
        gbc.gridy = 2;
        buttonBackAdminDash.addActionListener(e -> AdminController.goBackToAdminDashboard(this));
        add(buttonBackAdminDash, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        List<Object[]> nguoithueData = NguoiThueTro.getAllNguoiThueData();
        SwingUtilities.invokeLater(() -> new AdminShowAllNguoiThueTroView(nguoithueData));
    }
}
