package frontend.view.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminShowAllHoadonView extends JFrame {
    private JTable table;

    public AdminShowAllHoadonView(List<Object[]> data) {
        setTitle("Danh sách hóa đơn");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Danh sách hóa đơn", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        String[] columnNames = {"Tên phòng", "Họ tên chủ trọ", "Họ tên người thuê", "Số điện thoại", "Tổng chi phí", "Ngày xuất hóa đơn"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Object[] row : data) {
            tableModel.addRow(row);
        }

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
