package frontend.view.Invoices;


import backend.model.Invoices;
import frontend.components.InvoiceButtonEditor;
import frontend.components.InvoiceButtonRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static controller.InvoicesController.*;

public class InvoiceListsView {
    public InvoiceListsView(int idChutro, String landlordName, int roomCount) {
        // Tạo JFrame
        JFrame frame = new JFrame("Danh Sách Hóa Đơn");
        frame.setSize(1100, 850);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Tạo viền ngoài
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(emptyBorder, lineBorder));

        // Thông tin chủ trọ
        JLabel titleLabel = new JLabel("Danh Sách Hóa Đơn", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        JPanel landlordInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        landlordInfoPanel.add(new JLabel("Chủ Trọ: " + landlordName));
        landlordInfoPanel.add(new JLabel("| Tổng số Phòng: " + roomCount));
        mainPanel.add(landlordInfoPanel);

        // Bảng danh sách hóa đơn

        // TODO: Khu vực này sẽ có thay đổi vị trí Tình Trạng
        // TODO: Dự tính sẽ chuyển qua nút bấm, nếu xác nhận người thuê đẫ thanh toán ==> bấm đã thanh toán ==> không cho thay đổi
        String[] columnNames = {"STT", "Tên Phòng", "Tên Người Thuê", "Tổng Giá Trị", "Ngày Xuất", "Tình Trạng"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                // Cột "Tình Trạng" (cột thứ 5) phải luôn là kiểu Boolean để hiển thị checkbox
                return column == 5 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép chỉnh sửa cột "Tình Trạng"
                return column == 5;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(30); // Tăng chiều cao hàng
        table.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        table.getTableHeader().setReorderingAllowed(false); // Không cho phép kéo cột

        // Căn chỉnh dữ liệu trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Đặt độ rộng cột
        table.getColumnModel().getColumn(0).setPreferredWidth(50); // STT
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Tên phòng
        table.getColumnModel().getColumn(2).setPreferredWidth(200); // Tên người thuê
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Tổng giá trị
        table.getColumnModel().getColumn(4).setPreferredWidth(150); // Ngày xuất
        // Gán ButtonRenderer và ButtonEditor cho cột "Tình Trạng"
        table.getColumnModel().getColumn(5).setCellRenderer(new InvoiceButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new InvoiceButtonEditor(table));


        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(tableScrollPane);

        // Load dữ liệu
        Invoices.loadInvoiceData(tableModel, idChutro);

        // Thêm phần thống kê
        JPanel statisticsPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        statisticsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Lấy thông tin từ InvoicesController
        int totalInvoices = getTotalInvoices(idChutro);
        int paidInvoices = getPaidInvoices(idChutro);
        int unpaidInvoices = getUnpaidInvoices(idChutro);
        double totalValue = getTotalValue(idChutro);
        double unpaidValue = getUnpaidValue(idChutro);
        double paidRate = getPaidRate(idChutro);

        // Hiển thị thông tin
        statisticsPanel.add(new JLabel("Tổng số hóa đơn: " + totalInvoices));
        statisticsPanel.add(new JLabel("Tổng số hóa đơn đã thanh toán: " + paidInvoices));
        statisticsPanel.add(new JLabel("Tổng số hóa đơn chưa thanh toán: " + unpaidInvoices));
        statisticsPanel.add(new JLabel("Tổng giá trị các hóa đơn: " + String.format("%,.2f VNĐ", totalValue)));
        statisticsPanel.add(new JLabel("Tổng giá trị các hóa đơn chưa thanh toán: " + String.format("%,.2f VNĐ", unpaidValue)));
        statisticsPanel.add(new JLabel("Tỷ lệ hóa đơn đã thanh toán: " + String.format("%.2f%%", paidRate)));

        mainPanel.add(statisticsPanel);

        // Nút quay lại
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        backButton.addActionListener(e -> frame.dispose());
        actionPanel.add(backButton);

        mainPanel.add(actionPanel);

        // Thêm panel chính vào frame
        frame.add(mainPanel, BorderLayout.CENTER);

        // Hiển thị JFrame
        frame.setVisible(true);
    }



}
