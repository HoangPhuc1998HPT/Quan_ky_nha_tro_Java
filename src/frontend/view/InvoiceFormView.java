package frontend.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
// test thoi được thì xóa của nợ này

public class InvoiceFormView {
    public InvoiceFormView() {
        // Tạo JFrame
        JFrame frame = new JFrame("HÓA ĐƠN GIÁ TRỊ GIA TĂNG");
        frame.setSize(800, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tiêu đề hóa đơn
        JLabel title = new JLabel("HÓA ĐƠN GIÁ TRỊ GIA TĂNG", SwingConstants.CENTER);
        title.setFont(new Font("Be Vietnam Pro", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        // Thông tin chung
        JPanel infoPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        infoPanel.add(new JLabel("Ký hiệu:", SwingConstants.LEFT));
        infoPanel.add(new JLabel("1C21TAA"));
        infoPanel.add(new JLabel("Số:", SwingConstants.LEFT));
        infoPanel.add(new JLabel("123"));
        infoPanel.add(new JLabel("Ngày:", SwingConstants.LEFT));
        infoPanel.add(new JLabel("02/01/2021"));

        infoPanel.add(new JLabel("Tên người bán:", SwingConstants.LEFT));
        infoPanel.add(new JLabel("CÔNG TY TNHH A"));
        infoPanel.add(new JLabel("Mã số thuế:", SwingConstants.LEFT));
        infoPanel.add(new JLabel("123456789"));
        infoPanel.add(new JLabel("Địa chỉ:", SwingConstants.LEFT));
        infoPanel.add(new JLabel("45 phố X, quận Y, thành phố Hà Nội"));

        mainPanel.add(infoPanel);

        // Tạo bảng hóa đơn
        String[] columnNames = {
                "STT", "Tên hàng hóa, dịch vụ", "Đơn vị tính", "Số lượng", "Đơn giá",
                "Thành tiền chưa có thuế GTGT", "Thuế suất", "Tiền thuế GTGT", "Tổng cộng"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 5); // 5 hàng trống
        JTable table = new JTable(tableModel);

        // Kích thước cột
        table.getColumnModel().getColumn(0).setPreferredWidth(30); // STT
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Tên hàng hóa
        table.getColumnModel().getColumn(2).setPreferredWidth(80); // Đơn vị tính
        table.getColumnModel().getColumn(3).setPreferredWidth(80); // Số lượng
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Đơn giá
        table.getColumnModel().getColumn(5).setPreferredWidth(150); // Thành tiền chưa thuế
        table.getColumnModel().getColumn(6).setPreferredWidth(80); // Thuế suất
        table.getColumnModel().getColumn(7).setPreferredWidth(150); // Tiền thuế
        table.getColumnModel().getColumn(8).setPreferredWidth(150); // Tổng cộng

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane);

        // Tổng cộng
        JPanel totalPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        totalPanel.add(new JLabel("Tổng tiền chưa có thuế GTGT: ..................."));
        totalPanel.add(new JLabel("Tổng tiền thuế GTGT: ..................."));
        totalPanel.add(new JLabel("Tổng tiền thanh toán: ..................."));
        mainPanel.add(totalPanel);

        // Người ký
        JPanel signPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        signPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel leftSign = new JPanel(new GridLayout(2, 1));
        leftSign.add(new JLabel("Người mua hàng", SwingConstants.CENTER));
        leftSign.add(new JLabel("(Ký, ghi rõ họ tên)", SwingConstants.CENTER));
        signPanel.add(leftSign);

        JPanel rightSign = new JPanel(new GridLayout(2, 1));
        rightSign.add(new JLabel("Người bán hàng", SwingConstants.CENTER));
        rightSign.add(new JLabel("(Ký, ghi rõ họ tên)", SwingConstants.CENTER));
        signPanel.add(rightSign);

        mainPanel.add(signPanel);

        // Thêm panel chính vào frame
        frame.add(mainPanel, BorderLayout.CENTER);

        // Hiển thị JFrame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InvoiceFormView::new);
    }
}
