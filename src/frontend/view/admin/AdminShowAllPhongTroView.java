package frontend.view.admin;

import backend.model.Room;
import frontend.components.Room.ButtonEditorRoom;
import frontend.components.Room.ButtonRendererRoom;
import controller.AdminController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminShowAllPhongTroView extends JFrame {
    public AdminShowAllPhongTroView(List<Object[]> roomData) {
        setTitle("Danh sách phòng trọ");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("DANH SÁCH PHÒNG TRỌ", SwingConstants.CENTER);
        title.setFont(new Font("Be Vietnam Pro", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        String[] columnNames = {"ID", "STT", "Tên phòng", "Địa chỉ", "Giá phòng", "Tên chủ trọ", "Tên người thuê", "Chức năng"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Chỉ cột "Chức năng" có thể chỉnh sửa
            }
        };

        for (int i = 0; i < roomData.size(); i++) {
            Object[] row = new Object[8];
            row[0] = roomData.get(i)[0]; // ID (ẩn)
            row[1] = i + 1; // STT
            row[2] = roomData.get(i)[1]; // Tên phòng
            row[3] = roomData.get(i)[2]; // Địa chỉ
            row[4] = roomData.get(i)[3]; // Giá phòng
            row[5] = roomData.get(i)[4]; // Tên chủ trọ
            row[6] = roomData.get(i)[5]; // Tên người thuê
            row[7] = "Xem chi tiết";
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        table.getColumn("Chức năng").setCellRenderer(new ButtonRendererRoom());
        table.getColumn("Chức năng").setCellEditor(new ButtonEditorRoom(new JCheckBox())); // Xử lý chức năng xem chi tiết phòng
        table.removeColumn(table.getColumnModel().getColumn(0)); // Ẩn cột ID

        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        JButton buttonSetVisibleFalse = new JButton("Tạm ngưng hoạt động phòng");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonSetVisibleFalse.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String roomId = table.getValueAt(selectedRow, 0).toString();
                Room.disableRoom(roomId);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một phòng để tạm ngưng hoạt động!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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


}
