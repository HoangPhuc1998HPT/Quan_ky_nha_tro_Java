package frontend.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminShowAllChutroView extends JFrame {
    private JTable table;

    public AdminShowAllChutroView(List<Object[]> data) {
        setTitle("Danh sách chủ trọ");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Danh sách chủ trọ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        String[] columnNames = {"Username", "Họ tên", "CCCD", "Số điện thoại", "Tổng số phòng"};
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
