// NguoiThueTroDashboard_1View.java
package frontend.view.nguoithuetro;

import backend.model.Invoices;
import backend.model.NguoiThueTro;
import backend.model.Room;
import controller.NguoiThueTroController;
import frontend.view.login_register.loginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class NguoiThueTroDashboard_1View extends JFrame {
    private int userId;
    private JPanel mainPanel;
    private JTable invoiceTable;

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

        /// Load dữ liệu thông tin phòng
        DefaultTableModel roomTableModel = new DefaultTableModel(
                new String[]{"Tên Phòng", "Tên Chủ Trọ", "Số Điện Thoại", "Địa Chỉ", "Ngày Bắt Đầu Thuê"}, 0
        );
        int TenantId = NguoiThueTro.getIdNguoiThueFromUserID(userId);
        List<Object[]> roomInfo = Room.getRoomInfoByTenantId(TenantId);

        // Kiểm tra dữ liệu trả về
        if (roomInfo.isEmpty()) {
            System.out.println("Không có dữ liệu phòng cho userId: " + userId);
        } else {
            for (Object[] info : roomInfo) {
                roomTableModel.addRow(info);
                System.out.println(Arrays.toString(info)); // In dữ liệu từng hàng
            }
        }
        // Gán dữ liệu cho JTable
        roomInfoTable.setModel(roomTableModel);
        roomInfoTable.repaint();
        mainPanel.revalidate();
        mainPanel.repaint();

        // Bảng danh sách hóa đơn
        JLabel invoiceLabel = new JLabel("Danh sách hóa đơn");
        invoiceLabel.setBounds(20, 200, 200, 25);
        mainPanel.add(invoiceLabel);

        invoiceTable = new JTable() {
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        invoiceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane invoiceScrollPane = new JScrollPane(invoiceTable);
        invoiceScrollPane.setBounds(20, 230, 750, 200);
        mainPanel.add(invoiceScrollPane);

        // Load dữ liệu hóa đơn
        DefaultTableModel invoiceTableModel = new DefaultTableModel(
                new String[]{"idHoaDon (ẩn)", "Ngày Xuất Hóa Đơn", "Tổng Giá Trị", "Trạng Thái", "Xem Hóa Đơn"}, 0);
        List<Object[]> invoices = Invoices.getInvoicesByTenantId(TenantId);

        for (Object[] invoice : invoices) {
            Object[] row = new Object[5]; // Tăng kích thước lên 5 phần tử
            row[0] = invoice[3]; // idHoaDon (ẩn)
            row[1] = invoice[0]; // Ngày Xuất Hóa Đơn
            row[2] = invoice[1]; // Tổng Giá Trị
            row[3] = (boolean) invoice[2] ? "Đã Thanh Toán" : "Chưa Thanh Toán"; // Trạng Thái

            JButton viewButton = getjButton(invoice, roomInfo, id_nguoithuetro);
            row[4] = viewButton;

            invoiceTableModel.addRow(row);
        }
        invoiceTable.setModel(invoiceTableModel);
        invoiceTable.setDefaultRenderer(JButton.class, new ButtonRenderer());
        invoiceTable.setDefaultEditor(JButton.class, new ButtonEditor(new JCheckBox()));

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

    private static JButton getjButton(Object[] invoice, List<Object[]> roomInfoList, int id_nguoithuetro) {
        JButton viewButton = new JButton("Xem");
        viewButton.addActionListener(e -> {
            if (roomInfoList != null && !roomInfoList.isEmpty()) {
                Object[] roomInfo = roomInfoList.get(0); // Assuming you want the first element of the list
                if (roomInfo.length > 6) {
                    int invoiceId = Integer.parseInt(String.valueOf(invoice[3])); // Sử dụng ID Hóa Đơn'
                    int roomId = Integer.parseInt(String.valueOf(roomInfo[5]));
                    int chutroId = Integer.parseInt(String.valueOf(roomInfo[6]));
                    NguoiThueTroController.goToOpenInvoiceView(
                            roomId,  // IDPhong
                            id_nguoithuetro,
                            chutroId, // IDChutro
                            invoiceId
                    );
                } else {
                    JOptionPane.showMessageDialog(null, "roomInfo does not have enough elements.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "roomInfoList is empty or null.");
            }
        });
        return viewButton;
    }


    public static void main(String[] args) {
        new NguoiThueTroDashboard_1View(1);
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JButton) {
            JButton button = (JButton) value;
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            return button;
        } else {
            return this;
        }
    }
}

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof JButton) {
            button = (JButton) value;
        }
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            // Perform your action here
            JOptionPane.showMessageDialog(button, button.getText() + ": Ouch!");
        }
        isPushed = false;
        return button;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
