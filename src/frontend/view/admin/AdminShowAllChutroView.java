package frontend.view.admin;

import backend.model.Chutro;
import frontend.components.ButtonEditorChutro;
import frontend.components.ButtonRendererChutro;
import controller.AdminController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminShowAllChutroView extends JFrame {
    public AdminShowAllChutroView(List<Object[]> chutroData) {
        setTitle("Danh sách Chủ trọ");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel title = new JLabel("DANH SÁCH CHỦ TRỌ", SwingConstants.CENTER);
        title.setFont(new Font("Be Vietnam Pro", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        // Dữ liệu bảng
        String[] columnNames = {"STT", "Tên Chủ trọ", "Số Phòng Quản Lý", "SĐT", "CCCD", "Chức năng"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Chỉ cột "Chức năng" có thể chỉnh sửa
            }
        };

        for (int i = 0; i < chutroData.size(); i++) {
            Object[] row = new Object[6];
            row[0] = i + 1;
            row[1] = chutroData.get(i)[0];
            row[2] = chutroData.get(i)[1];
            row[3] = chutroData.get(i)[2];
            row[4] = chutroData.get(i)[3];
            row[5] = "Xem chi tiết";
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        table.getColumn("Chức năng").setCellRenderer(new ButtonRendererChutro());
        table.getColumn("Chức năng").setCellEditor(new ButtonEditorChutro());

        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // Nút vô hiệu hóa hoạt động của chủ trọ
        JButton buttonSetVisibleFalse = new JButton("Tạm ngưng hoạt động chủ trọ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonSetVisibleFalse.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String cccd = table.getValueAt(selectedRow, 4).toString();
                AdminController.disableChutro(cccd); // Gọi controller để vô hiệu hóa
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một chủ trọ để tạm ngưng hoạt động!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(buttonSetVisibleFalse, gbc);

        // Nút quay lại Admindashboard
        JButton buttonBackAdminDash = new JButton("Quay lại");
        gbc.gridx = 1;
        gbc.gridy = 2;
        buttonBackAdminDash.addActionListener(e -> AdminController.goBackToAdminDashboard(this));
        add(buttonBackAdminDash, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        List<Object[]> chutroData = Chutro.getAllChutroData();
        SwingUtilities.invokeLater(() -> new AdminShowAllChutroView(chutroData));
    }
}
