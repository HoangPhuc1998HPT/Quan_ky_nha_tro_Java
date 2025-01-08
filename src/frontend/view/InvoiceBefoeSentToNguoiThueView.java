package frontend.view;

import backend.model.InvoiceDetail;
import backend.model.Invoices;
import backend.model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
//Form này được tạo để xem
public class InvoiceBefoeSentToNguoiThueView {
    private JFrame frame;

    public InvoiceBefoeSentToNguoiThueView(String idPhong) {
        // Tạo JFrame
        frame = new JFrame("HÓA ĐƠN TRƯỚC KHI GỬI NGƯỜI THUÊ");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tiêu đề hóa đơn
        JLabel title = new JLabel("HÓA ĐƠN", SwingConstants.CENTER);
        title.setFont(new Font("Be Vietnam Pro", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        // Panel thông tin phòng
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin phòng"));

        Room room = Room.getRoomDetails(idPhong);
        infoPanel.add(new JLabel("Tên phòng:"));
        infoPanel.add(new JLabel(room.getName()));

        infoPanel.add(new JLabel("Tên người thuê:"));
        infoPanel.add(new JLabel(room.getTenantName()));

        infoPanel.add(new JLabel("Giá phòng:"));
        infoPanel.add(new JLabel(String.valueOf(room.getRoomPrice())));

        mainPanel.add(infoPanel);

        // Tạo bảng hiển thị hóa đơn
        String[] columnNames = {
                "STT", "Tên hàng hóa, dịch vụ", "Đơn vị tính", "Số lượng", "Đơn giá",
                "Thành tiền"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
        mainPanel.add(scrollPane);

        try {
            // Lấy dữ liệu hóa đơn từ database
            Object[] invoiceData = Invoices.getInvoiceData(idPhong);
            double giaDien = Invoices.getGiaDien(idPhong);
            double giaNuoc = Invoices.getGiaNuoc(idPhong);

            tableModel.addRow(new Object[]{"1", "Tiền điện", "kWh", invoiceData[1], giaDien, (int) invoiceData[1] * giaDien});
            tableModel.addRow(new Object[]{"2", "Tiền nước", "m3", invoiceData[2], giaNuoc, (int) invoiceData[2] * giaNuoc});
            tableModel.addRow(new Object[]{"3", "Tiền nhà", "tháng", "1", invoiceData[4], invoiceData[4]});
            tableModel.addRow(new Object[]{"4", "Chi phí khác", "", "1", invoiceData[5], invoiceData[5]});
            tableModel.addRow(new Object[]{"5", "Tiền rác", "", "1", invoiceData[6], invoiceData[6]});
            tableModel.addRow(new Object[]{"6", "Giảm giá", "", "1", "-" + invoiceData[7], -1 * (double) invoiceData[7]});
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Không thể lấy dữ liệu hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Panel tổng cộng
        JPanel totalPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        totalPanel.setBorder(BorderFactory.createTitledBorder("Tổng cộng"));

        try {
            Object[] invoiceData = Invoices.getInvoiceData(idPhong);
            double giaDien = Invoices.getGiaDien(idPhong);
            double giaNuoc = Invoices.getGiaNuoc(idPhong);
            double tongChiPhi = Invoices.calculateTongChiPhi(invoiceData, giaDien, giaNuoc);

            totalPanel.add(new JLabel("Tổng tiền:"));
            totalPanel.add(new JLabel(String.valueOf(tongChiPhi) + " VND"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mainPanel.add(totalPanel);

        // Nút hành động
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton sendButton = new JButton("Gửi hóa đơn");
        JButton backButton = new JButton("Quay lại");

        sendButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Hóa đơn đã được gửi!", "Thành công", JOptionPane.INFORMATION_MESSAGE));
        backButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(sendButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel);

        // Thêm mainPanel vào frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InvoiceBefoeSentToNguoiThueView("01"));
    }
}
