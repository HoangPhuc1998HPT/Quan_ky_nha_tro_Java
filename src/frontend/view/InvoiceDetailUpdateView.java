package frontend.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvoiceDetailUpdateView {

    private JFrame frame;

    public InvoiceDetailUpdateView(String id_room, String roomName, String tenantName, String startDate, int oldElectric, int oldWater, String lastPaymentDate) {
        // Tạo JFrame
        frame = new JFrame("Cập nhật chi tiết hóa đơn");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel chính với GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel title = new JLabel("Cập nhật chi tiết hóa đơn");
        title.setFont(new Font("Be Vietnam Pro", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Chiếm 3 cột
        panel.add(title, gbc);

        gbc.gridwidth = 1; // Reset gridwidth

        // Thông tin phòng
        JLabel roomInfoLabel = new JLabel("Thông tin phòng:");
        roomInfoLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(roomInfoLabel, gbc);

        JLabel roomNameLabel = new JLabel("Tên phòng: " + roomName);
        roomNameLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(roomNameLabel, gbc);

        JLabel tenantNameLabel = new JLabel("Tên người thuê: " + tenantName);
        tenantNameLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(tenantNameLabel, gbc);

        JLabel startDateLabel = new JLabel("Ngày bắt đầu thuê: " + startDate);
        startDateLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(startDateLabel, gbc);

        // Hiển thị số điện cũ và nhập số điện mới
        JLabel oldElectricLabel = new JLabel("Số điện cũ:");
        oldElectricLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(oldElectricLabel, gbc);

        JLabel oldElectricLabelShow = new JLabel(String.valueOf(oldElectric));
        oldElectricLabelShow.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(oldElectricLabelShow, gbc);

        JLabel electricUnitLabel_0 = new JLabel("kWh"); // Đơn vị
        electricUnitLabel_0.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(electricUnitLabel_0, gbc);

        JLabel newElectricLabel = new JLabel("Số điện mới:");
        newElectricLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(newElectricLabel, gbc);

        JTextField newElectricField = new JTextField(10);
        newElectricField.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(newElectricField, gbc);

        JLabel electricUnitLabel_1 = new JLabel("kWh"); // Đơn vị
        electricUnitLabel_1.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 5;
        panel.add(electricUnitLabel_1, gbc);

        // Hiển thị số nước cũ và nhập số nước mới
        JLabel oldWaterLabel = new JLabel("Số nước cũ:");
        oldWaterLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(oldWaterLabel, gbc);

        JLabel waterUnitLabel_0 = new JLabel("m³"); // Đơn vị
        waterUnitLabel_0.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 6;
        panel.add(waterUnitLabel_0, gbc);

        JLabel oldWaterLabelShow = new JLabel(String.valueOf(oldWater));
        oldWaterLabelShow.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(oldWaterLabelShow, gbc);

        JLabel newWaterLabel = new JLabel("Số nước mới:");
        newWaterLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(newWaterLabel, gbc);

        JTextField newWaterField = new JTextField(10);
        newWaterField.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(newWaterField, gbc);

        JLabel waterUnitLabel_1 = new JLabel("m³"); // Đơn vị
        waterUnitLabel_1.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 7;
        panel.add(waterUnitLabel_1, gbc);

        // Ngày thu tiền nhà tháng trước
        JLabel lastPaymentLabel = new JLabel("Ngày thu tiền nhà tháng trước:");
        lastPaymentLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(lastPaymentLabel, gbc);

        JLabel lastPaymentValue = new JLabel(lastPaymentDate != null ? lastPaymentDate : "Chưa có");
        lastPaymentValue.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(lastPaymentValue, gbc);

        // Ngày xuất hóa đơn
        JLabel dateLabel = new JLabel("Ngày xuất hóa đơn:");
        dateLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(dateLabel, gbc);

        JTextField dateField = new JTextField(10);
        dateField.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        dateField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(dateField, gbc);

        // Giảm giá
        JLabel discountLabel = new JLabel("Giảm giá:");
        discountLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(discountLabel, gbc);

        JTextField discountField = new JTextField(10);
        discountField.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(discountField, gbc);

        JLabel discountUnitLabel = new JLabel("VNĐ"); // Đơn vị
        discountUnitLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 10;
        panel.add(discountUnitLabel, gbc);

        // Nút cập nhật
        JButton updateButton = new JButton("Cập nhật");
        updateButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 3; // Chiếm 3 cột
        gbc.anchor = GridBagConstraints.CENTER;
        updateButton.addActionListener(e -> {
            try {
                int newElectric = Integer.parseInt(newElectricField.getText());
                int newWater = Integer.parseInt(newWaterField.getText());
                double discount = Double.parseDouble(discountField.getText());
                String invoiceDate = dateField.getText();
                System.out.println("Số điện mới: " + newElectric);
                System.out.println("Số nước mới: " + newWater);
                System.out.println("Giảm giá: " + discount);
                System.out.println("Ngày xuất hóa đơn: " + invoiceDate);

                // Gọi hàm Controller để lưu thông tin
                updateInvoiceDetails(id_room, roomName, newElectric, newWater, discount, invoiceDate);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(updateButton, gbc);

        // Thêm panel vào frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Hàm giả lập để cập nhật hóa đơn
    private void updateInvoiceDetails(String id_room, String roomName, int newElectric, int newWater, double discount, String invoiceDate) {
        System.out.println("Cập nhật hóa đơn cho phòng: " + roomName + " (ID: " + id_room + ")");
        System.out.println("Số điện mới: " + newElectric);
        System.out.println("Số nước mới: " + newWater);
        System.out.println("Giảm giá: " + discount);
        System.out.println("Ngày xuất hóa đơn: " + invoiceDate);
        // TODO: Thêm logic cập nhật cơ sở dữ liệu ở đây
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InvoiceDetailUpdateView(
                "P001",
                "Phòng A",
                "Nguyễn Văn A",
                "01/01/2023",
                100,
                50,
                "01/05/2023"
        ));
    }
}
