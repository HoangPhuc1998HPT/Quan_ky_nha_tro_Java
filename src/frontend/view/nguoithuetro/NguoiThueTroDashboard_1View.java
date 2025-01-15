package frontend.view.nguoithuetro;

import backend.model.Chutro;
import backend.model.Invoices;
import backend.model.NguoiThueTro;
import backend.model.Room;
import controller.NguoiThueTroController;
import frontend.components.Invoice.TenantInvoiceButtonEditor;
import frontend.components.Invoice.TenantInvoiceButtonRenderer;
import frontend.view.login_register.loginView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NguoiThueTroDashboard_1View extends JFrame {
    private int userId;
    private JPanel mainPanel;
    private JTable invoiceTable;

    public NguoiThueTroDashboard_1View(int userId) {
        this.userId = userId;

        // Lấy ID Người thuê từ UserID
        int idNguoiThueTro = NguoiThueTro.getIdNguoiThueFromUserID(userId);

        // Cài đặt frame
        setTitle("Người Thuê Trọ Đã Có Phòng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
        setLocationRelativeTo(null);

        // Tiêu đề
        JLabel label = new JLabel("Dashboard Người Thuê Trọ", SwingConstants.CENTER);
        label.setBounds(250, 10, 300, 30);
        mainPanel.add(label);

        // Hiển thị thông tin phòng
        addRoomInfoSection(idNguoiThueTro);

        // Bảng danh sách hóa đơn
        addInvoiceTableSection(idNguoiThueTro);

        // Nút Đăng xuất
        JButton logoutBtn = new JButton("Đăng xuất");
        logoutBtn.setBounds(680, 500, 100, 30);
        logoutBtn.addActionListener(e -> {
            dispose();
            new loginView();
        });
        mainPanel.add(logoutBtn);

        setVisible(true);
    }

    private void addRoomInfoSection(int idNguoiThueTro) {
        JLabel roomInfoLabel = new JLabel("Thông tin phòng");
        roomInfoLabel.setBounds(20, 50, 200, 25);
        mainPanel.add(roomInfoLabel);

        JTable roomInfoTable = new JTable();
        roomInfoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane roomScrollPane = new JScrollPane(roomInfoTable);
        roomScrollPane.setBounds(20, 80, 750, 100);
        mainPanel.add(roomScrollPane);

        // Load dữ liệu thông tin phòng
        DefaultTableModel roomTableModel = new DefaultTableModel(
                new String[]{"Tên Phòng", "Tên Chủ Trọ", "Số Điện Thoại", "Địa Chỉ", "Ngày Bắt Đầu Thuê"}, 0
        );
        List<Object[]> roomInfo = Room.getRoomInfoByTenantId(idNguoiThueTro);
        roomInfo.forEach(roomTableModel::addRow);

        roomInfoTable.setModel(roomTableModel);
    }

    private void addInvoiceTableSection(int idNguoiThueTro) {
        JLabel invoiceLabel = new JLabel("Danh sách hóa đơn");
        invoiceLabel.setBounds(20, 200, 200, 25);
        mainPanel.add(invoiceLabel);

        // Cấu trúc bảng hóa đơn
        String[] columnNames = {"idHoaDon (ẩn)", "Ngày Xuất Hóa Đơn", "Tổng Giá Trị", "Trạng Thái", "Xem Hóa Đơn"};
        DefaultTableModel invoiceTableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 4 ? JButton.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Chỉ cột "Xem Hóa Đơn" có thể chỉnh sửa
            }
        };

        // Bảng danh sách hóa đơn
        invoiceTable = new JTable(invoiceTableModel);
        invoiceTable.setRowHeight(30);
        invoiceTable.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        invoiceTable.getTableHeader().setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        invoiceTable.getTableHeader().setReorderingAllowed(false);

        // Căn chỉnh dữ liệu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < invoiceTable.getColumnCount(); i++) {
            invoiceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Ẩn cột "idHoaDon (ẩn)"
        invoiceTable.removeColumn(invoiceTable.getColumnModel().getColumn(0));

        // Gán Renderer và Editor cho nút JButton
        String landlordName = Chutro.getLandlordNameFromIdNguoiThue(idNguoiThueTro);
        invoiceTable.setDefaultRenderer(JButton.class, new TenantInvoiceButtonRenderer());
        invoiceTable.setDefaultEditor(JButton.class, new TenantInvoiceButtonEditor(this, invoiceTable, idNguoiThueTro, landlordName));

        // Định nghĩa SimpleDateFormat
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày đầu vào (giả sử từ database)
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yy"); // Định dạng ngày hiển thị


        // Load dữ liệu hóa đơn
        List<Object[]> invoices = Invoices.getInvoicesByTenantId(idNguoiThueTro);
        for (Object[] invoice : invoices) {
            Object[] row = new Object[5];

            try {
                // Chuyển đổi định dạng ngày
                Date invoiceDate = inputFormat.parse(invoice[0].toString());
                String formattedDate = outputFormat.format(invoiceDate);
                row[1] = formattedDate; // Gán ngày đã định dạng
            } catch (Exception e) {
                row[1] = "Không hợp lệ"; // Nếu lỗi, hiển thị "Không hợp lệ"
            }

            row[0] = invoice[3]; // ID Hóa Đơn (ẩn)
            row[2] = invoice[1]; // Tổng Giá Trị
            row[3] = (boolean) invoice[2] ? "Đã Thanh Toán" : "Chưa Thanh Toán";
            row[4] = "Xem"; // Nội dung của nút
            invoiceTableModel.addRow(row);
        }

        JScrollPane invoiceScrollPane = new JScrollPane(invoiceTable);
        invoiceScrollPane.setBounds(20, 230, 750, 200);
        mainPanel.add(invoiceScrollPane);
    }

    public static void main(String[] args) {
        new NguoiThueTroDashboard_1View(5);
    }
}