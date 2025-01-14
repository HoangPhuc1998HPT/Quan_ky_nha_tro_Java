package frontend.view.Invoices;

import controller.InvoicesController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static controller.RoomController.GoToBackRoomView;

public class InvoiceCreateNewInvoice {
    private JFrame frame;
    private JTable table;
    private JLabel labelSohoadon;
    private JLabel totalLabel;
    private InvoicesController controller;
//  không dùng

    public InvoiceCreateNewInvoice(int id_chutro, int id_phong) {
        // Khởi tạo Frame
        frame = new JFrame("Xuất hóa đơn");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Thiết lập Controller
        controller = new InvoicesController(this, id_chutro, id_phong);

        // Tiêu đề
        JLabel titleLabel = new JLabel("HÓA ĐƠN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Phần nội dung chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // Thông tin hóa đơn
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        labelSohoadon = new JLabel("Số hóa đơn: ");
        labelSohoadon.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(labelSohoadon);
        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // Bảng hiển thị chi tiết hóa đơn
        String[] columnNames = {"Nội dung", "Số lượng", "Đơn giá", "Thành tiền"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Tổng cộng
        totalLabel = new JLabel("Tổng cộng: 0 VNĐ", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(totalLabel, BorderLayout.SOUTH);

        // Phần nút điều hướng
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnXuatHoadon = new JButton("Xuất hóa đơn");
        btnXuatHoadon.addActionListener(e -> controller.goToNhapHoadon());
        buttonPanel.add(btnXuatHoadon);

        JButton btnQuayLai = new JButton("Quay lại");
        btnQuayLai.addActionListener(e -> GoToBackRoomView(frame, id_phong, id_chutro));
        buttonPanel.add(btnQuayLai);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Hiển thị giao diện
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Nạp dữ liệu ban đầu
        controller.loadThongTinHoadon(id_phong);
    }

    // Hàm để cập nhật giao diện
    public void updateView(String soHoadon, Object[][] data, double tongCong) {
        labelSohoadon.setText("Số hóa đơn: " + soHoadon);

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        for (Object[] row : data) {
            tableModel.addRow(row);
        }

        totalLabel.setText(String.format("Tổng cộng: %,d VNĐ", (int) tongCong));
    }

    // Hàm hiển thị thông báo thành công
    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }

    // Hàm hiển thị thông báo lỗi
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }


}
