package frontend.view.admin;

import backend.model.Invoices;
import controller.AdminController;
import frontend.components.InvoiceButtonEditor_1;
import frontend.components.InvoiceButtonRenderer;
import frontend.components.InvoiceButtonEditor;
import frontend.components.InvoiceButtonRenderer_1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class AdminShowAllHoaDonView extends JFrame {

    public AdminShowAllHoaDonView(List<Object[]> invoiceData) {
        setTitle("Danh sách hóa đơn");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("DANH SÁCH HÓA ĐƠN", SwingConstants.CENTER);
        title.setFont(new Font("Be Vietnam Pro", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        String[] columnNames = {"ID Hóa Đơn (Ẩn)", "STT", "Tên phòng", "Tên người thuê", "Tổng chi phí", "Ngày xuất", "Tình trạng", "Chức năng"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Chỉ cột "Chức năng" có thể chỉnh sửa
            }
        };

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        for (int i = 0; i < invoiceData.size(); i++) {
            Object[] row = new Object[8];
            row[0] = invoiceData.get(i)[0]; // ID Hóa đơn (ẩn)
            row[1] = i + 1; // STT
            row[2] = invoiceData.get(i)[1]; // Tên phòng
            row[3] = invoiceData.get(i)[2]; // Tên người thuê
            row[4] = invoiceData.get(i)[4] instanceof java.sql.Date
                    ? ((java.sql.Date) invoiceData.get(i)[4]).toLocalDate().format(dateFormatter)
                    : "N/A"; // Ngày xuất
            row[5] = invoiceData.get(i)[4]; // Ngày xuất
            row[6] = invoiceData.get(i)[5]; // Tình trạng
            row[7] = "Chi tiết"; // Button
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        table.getColumn("Chức năng").setCellRenderer(new InvoiceButtonRenderer_1());
        table.getColumn("Chức năng").setCellEditor(new InvoiceButtonEditor_1(table));
        table.removeColumn(table.getColumnModel().getColumn(0)); // Ẩn cột "ID Hóa Đơn"

        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        JButton buttonBackAdminDash = new JButton("Quay lại");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonBackAdminDash.addActionListener(e -> AdminController.goBackToAdminDashboard(this));
        add(buttonBackAdminDash, gbc);

        setVisible(true);
    }
}
