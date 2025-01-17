package frontend.view.nguoithuetro;

import backend.model.NguoiThueTro;

import javax.swing.*;
import java.awt.*;

public class UpdateNguoiThueTroInforView extends JFrame {

    public UpdateNguoiThueTroInforView(int userId, String fullName, String phone, String cccd) {
        // Thiết lập tiêu đề và kích thước giao diện
        setTitle("Cập nhật thông tin người thuê trọ");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Nhãn và trường nhập liệu cho "Họ tên"
        JLabel nameLabel = new JLabel("Họ tên:");
        nameLabel.setBounds(30, 30, 100, 30);
        add(nameLabel);

        JTextField nameField = new JTextField(fullName);
        nameField.setBounds(150, 30, 200, 30);
        add(nameField);

        // Nhãn và trường nhập liệu cho "Số điện thoại"
        JLabel phoneLabel = new JLabel("Số điện thoại:");
        phoneLabel.setBounds(30, 80, 100, 30);
        add(phoneLabel);

        JTextField phoneField = new JTextField(phone);
        phoneField.setBounds(150, 80, 200, 30);
        add(phoneField);

        // Nhãn và trường nhập liệu cho "CCCD"
        JLabel cccdLabel = new JLabel("CCCD:");
        cccdLabel.setBounds(30, 130, 100, 30);
        add(cccdLabel);

        JTextField cccdField = new JTextField(cccd);
        cccdField.setBounds(150, 130, 200, 30);
        add(cccdField);

        // Nút lưu thông tin
        JButton saveButton = new JButton("Lưu");
        saveButton.setBounds(150, 200, 100, 30);
        saveButton.addActionListener(e -> {
            // Lấy dữ liệu từ các trường nhập liệu
            String updatedName = nameField.getText().trim();
            String updatedPhone = phoneField.getText().trim();
            String updatedCCCD = cccdField.getText().trim();

            // Kiểm tra dữ liệu nhập
            if (updatedName.isEmpty() || updatedPhone.isEmpty() || updatedCCCD.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Gọi hàm cập nhật thông tin trong backend
            boolean isUpdated = NguoiThueTro.updateTenantInformation(userId, updatedName, updatedPhone, updatedCCCD);
            if (isUpdated) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
                dispose(); // Đóng cửa sổ sau khi lưu
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(saveButton);

        // Hiển thị giao diện ở giữa màn hình
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
