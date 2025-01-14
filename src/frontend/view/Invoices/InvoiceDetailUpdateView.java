package frontend.view.Invoices;

import backend.model.InvoiceDetail;
import backend.model.NguoiThueTro;
import backend.model.Room;
import controller.RoomController;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static backend.model.InvoiceDetail.getPreDayInMonth;
import static backend.model.InvoiceDetail.updateInvoiceDetail;
import static controller.RoomController.GoToBackRoomView;


public class InvoiceDetailUpdateView {

    private JFrame frame;
    private int idCTHD;
    public InvoiceDetailUpdateView(int id_chutro, Room room, NguoiThueTro nguoiThueTro, java.util.Date startDate) {
        // Tạo JFrame
        this.idCTHD = idCTHD;
        Date lastPaymentDate = getPreDayInMonth(room.getIdRoom());
        frame = new JFrame("Cập nhật chi tiết hóa đơn");
        frame.setSize(800, 800);
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
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);

        gbc.gridwidth = 1; // Reset gridwidth

        // Thông tin phòng
        JLabel roomInfoLabel = new JLabel("Thông tin phòng:");
        roomInfoLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(roomInfoLabel, gbc);

        JLabel roomNameLabel = new JLabel("Tên phòng: " + room.getName());
        roomNameLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(roomNameLabel, gbc);

        JLabel tenantNameLabel = new JLabel("Tên người thuê: " + nguoiThueTro.getFullName());
        tenantNameLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(tenantNameLabel, gbc);

        // Giả sử startDate là một đối tượng Date
        SimpleDateFormat formatterstartDate = new SimpleDateFormat("dd/MM/yy");
        String formattedStartDate = formatterstartDate.format(startDate);

        JLabel startDateLabel = new JLabel("Ngày bắt đầu thuê: " + formattedStartDate);
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

        JLabel oldElectricLabelShow = new JLabel(String.valueOf(room.getCurrentElectricity()));
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

        JLabel oldWaterLabelShow = new JLabel(String.valueOf(room.getCurrentWater()));
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


        JLabel lastPaymentValue = new JLabel(String.valueOf(lastPaymentDate) != null ? String.valueOf(lastPaymentDate) : "Chưa có");
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

        // Chi phí phát sinh
        JLabel additionalCostLabel = new JLabel("Chi phí phát sinh:");
        additionalCostLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(additionalCostLabel, gbc);

        JTextField additionalCostField = new JTextField(10);
        additionalCostField.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 11;
        panel.add(additionalCostField, gbc);

        JLabel additionalCostUnitLabel = new JLabel("VNĐ"); // Đơn vị
        additionalCostUnitLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 11;
        panel.add(additionalCostUnitLabel, gbc);


        // Nút cập nhật
        JButton updateButton = new JButton("Cập nhật");
        updateButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 3; // Chiếm 3 cột
        gbc.anchor = GridBagConstraints.CENTER;
        updateButton.addActionListener(e -> {
            try {
                // Lấy và kiểm tra các giá trị đầu vào
                if (newElectricField.getText().trim().isEmpty() || newWaterField.getText().trim().isEmpty() ||
                        discountField.getText().trim().isEmpty() || additionalCostField.getText().trim().isEmpty()) {
                    throw new IllegalArgumentException("Vui lòng điền đầy đủ các trường dữ liệu.");
                }

                int newElectric = Integer.parseInt(newElectricField.getText());
                int newWater = Integer.parseInt(newWaterField.getText());
                double discount = Double.parseDouble(discountField.getText());
                double additionalCost = Double.parseDouble(additionalCostField.getText());

                String invoiceDateStr = dateField.getText().trim();
                LocalDate invoiceDate;
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    invoiceDate = LocalDate.parse(invoiceDateStr, formatter);
                } catch (Exception parseEx) {
                    throw new IllegalArgumentException("Định dạng ngày không hợp lệ. Vui lòng nhập ngày theo định dạng dd/MM/yyyy.");
                }
                Date sqlInvoiceDate = Date.valueOf(invoiceDate);

                // Xử lý ngày tháng trước
                //lastPaymentDate = getPreDayInMonth(room.getIdRoom());
                if (lastPaymentDate == null) {
                    JOptionPane.showMessageDialog(frame, "Không tìm thấy ngày thu tiền nhà tháng trước.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                LocalDate invoiceLastMonth = lastPaymentDate.toLocalDate();
                int dayInMonth = InvoiceDetail.calculateDaysBetweenDates(invoiceLastMonth, invoiceDate);
                System.out.println(invoiceLastMonth);
                System.out.println(invoiceLastMonth);
                System.out.println(dayInMonth);

                // Tạo đối tượng InvoiceDetail
                InvoiceDetail updatedDetail = new InvoiceDetail(
                        idCTHD,
                        room.getIdRoom(),
                        room.getCurrentElectricity(),
                        room.getCurrentWater(),
                        newElectric - room.getCurrentElectricity(),
                        newWater - room.getCurrentWater(),
                        dayInMonth,
                        room.getRoomPrice(),
                        additionalCost,
                        room.getGarbagePrice(),
                        discount,
                        sqlInvoiceDate
                );
                // check
                System.out.println("Chi tiết hóa đơn cập nhật:");
                System.out.println("ID Phòng: " + updatedDetail.getIdRoom());
                System.out.println("ID Phòng: " + updatedDetail.getidCTHD());
                System.out.println("Số điện cũ: " + updatedDetail.getOldElectricReading());
                System.out.println("Số điện mới: " + updatedDetail.getUseElectricReading());
                System.out.println("Số nước cũ: " + updatedDetail.getOldWaterReading());
                System.out.println("Số nước mới: " + updatedDetail.getUseWaterReading());
                System.out.println("Giá thuê: " + updatedDetail.getRentPrice());
                System.out.println("Giá điện: " + updatedDetail.getAdditionalCost());
                //System.out.println("Giá nước: " + updatedDetail.getWaterPrice());
                System.out.println("Giảm giá: " + updatedDetail.getDiscount());
                System.out.println("Ngày hóa đơn: " + updatedDetail.getInvoiceDate());

                // Gọi hàm cập nhật CSDL
                String formattedDate = LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                boolean isSuccess = InvoiceDetail.updateInvoiceDetail(room.getIdRoom(), updatedDetail, Date.valueOf(formattedDate),dayInMonth);

                if (isSuccess) {
                    JOptionPane.showMessageDialog(frame, "Cập nhật hóa đơn và số điện/nước thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    RoomController.GoToBackRoomView(frame,room.getIdRoom(), id_chutro); // Quay lại RoomView
                } else {
                    JOptionPane.showMessageDialog(frame, "Cập nhật hóa đơn thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Đã xảy ra lỗi trong quá trình cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(updateButton, gbc);


        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 3; // Chiếm 3 cột
        gbc.anchor = GridBagConstraints.CENTER;
        backButton.addActionListener(e -> GoToBackRoomView( frame,  room.getIdRoom(),  id_chutro));
        panel.add(backButton, gbc);


        // Thêm panel vào frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
