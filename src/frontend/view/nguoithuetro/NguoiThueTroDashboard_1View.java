// NguoiThueTroDashboard_1View.java
package frontend.view.nguoithuetro;

import backend.model.Invoices;
import backend.model.NguoiThueTro;
import backend.model.Room;
import backend.model.Invoices;
import controller.NguoiThueTroController;
import frontend.view.Invoices.InvoiceFormView;
import frontend.view.login_register.loginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NguoiThueTroDashboard_1View extends JFrame {
    private int userId;
    private JPanel mainPanel;

    public NguoiThueTroDashboard_1View(int userId) {
        this.userId = userId;
        int id_nguoithuetro = NguoiThueTro.getIdNguoiThueFromUserID(userId);
        setTitle("Người Thuê Trọ Đã Có Phòng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Dashboard Người Thuê Trọ", SwingConstants.CENTER);
        label.setBounds(250, 10, 300, 30);
        mainPanel.add(label);

        // Hiển thị thông tin phòng
        JLabel roomInfoLabel = new JLabel("Thông tin phòng");
        roomInfoLabel.setBounds(20, 50, 200, 25);
        mainPanel.add(roomInfoLabel);

        JTable roomInfoTable = new JTable();
        roomInfoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane roomScrollPane = new JScrollPane(roomInfoTable);
        roomScrollPane.setBounds(20, 80, 750, 100);
        mainPanel.add(roomScrollPane);

        // Load dữ liệu thông tin phòng
        DefaultTableModel roomTableModel = new DefaultTableModel(new String[]{"Tên Phòng", "Tên Chủ Trọ", "Số Điện Thoại", "Địa Chỉ", "Ngày Bắt Đầu Thuê"}, 0);
        List<Object[]> roomInfo = Room.getRoomInfoByTenantId(userId);
        for (Object[] info : roomInfo) {
            roomTableModel.addRow(info);
        }
        roomInfoTable.setModel(roomTableModel);

        // Bảng danh sách hóa đơn
        JLabel invoiceLabel = new JLabel("Danh sách hóa đơn");
        invoiceLabel.setBounds(20, 200, 200, 25);
        mainPanel.add(invoiceLabel);

        JTable invoiceTable = new JTable();
        invoiceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane invoiceScrollPane = new JScrollPane(invoiceTable);
        invoiceScrollPane.setBounds(20, 230, 750, 200);
        mainPanel.add(invoiceScrollPane);

        // Load dữ liệu hóa đơn
        DefaultTableModel invoiceTableModel = new DefaultTableModel(new String[]{"Ngày Xuất Hóa Đơn", "Tổng Giá Trị", "Trạng Thái", "Xem Hóa Đơn"}, 0);
        List<Object[]> invoices = Invoices.getInvoicesByTenantId(userId);
        for (Object[] invoice : invoices) {
            Object[] row = new Object[4];
            row[0] = invoice[0]; // Ngày Xuất Hóa Đơn
            row[1] = invoice[1]; // Tổng Giá Trị
            row[2] = (boolean) invoice[2] ? "Đã Thanh Toán" : "Chưa Thanh Toán"; // Trạng Thái

            JButton viewButton = new JButton("Xem");
            viewButton.addActionListener(e -> {
                int invoiceId = (int) invoice[3];
                NguoiThueTroController.goToOpenInvoiceView(
                        Integer.parseInt(String.valueOf(roomInfo.get(6)) ), // IDChutro
                        id_nguoithuetro,
                        invoiceId,
                        Integer.parseInt(String.valueOf(roomInfo.get(5)))  // IDPhong
                );
            });
            row[3] = viewButton;

            invoiceTableModel.addRow(row);
        }
        invoiceTable.setModel(invoiceTableModel);

        // Nút Đăng xuất
        JButton logoutBtn = new JButton("Đăng xuất");
        logoutBtn.setBounds(680, 500, 100, 30);
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new loginView();
            }
        });
        mainPanel.add(logoutBtn);

        setVisible(true);
    }

    public static void main(String[] args) {
        new NguoiThueTroDashboard_1View(19);
    }
}
