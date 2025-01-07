package frontend.view;

import backend.model.Chutro;
import controller.DashboardChutroController;

import javax.swing.*;
import java.awt.*;

public class ChutroInformationView {
    private JFrame frame;

    public ChutroInformationView(String idChutro) {
        // Tạo JFrame
        frame = new JFrame("Thông Tin Chủ Trọ");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông Tin Chủ Trọ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Be Vietnam Pro", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Tiêu đề chiếm cả 3 cột
        frame.add(titleLabel, gbc);

        // Lấy thông tin chủ trọ từ database
        Chutro chutro = Chutro.getChutrobyChutroID(idChutro);
        if (chutro == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin chủ trọ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hiển thị thông tin
        gbc.gridwidth = 1; // Reset gridwidth về 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 1;
        frame.add(new JLabel(chutro.getFullName()), gbc);
        gbc.gridx = 2; // Nút cập nhật
        JButton updateNameButton = new JButton("Cập nhật");
        updateNameButton.addActionListener(e->{});
        //TODO: Thiết lập liên kết cho nút cập nhật ChutroInformationView
        frame.add(updateNameButton, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(new JLabel("Số CCCD:"), gbc);
        gbc.gridx = 1;
        frame.add(new JLabel(chutro.getCccd()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridx = 1;
        frame.add(new JLabel(chutro.getPhone()), gbc);
        gbc.gridx = 2; // Nút cập nhật
        JButton updatePhoneButton = new JButton("Cập nhật");
        updatePhoneButton.addActionListener(e->{});
        //TODO: Thiết lập liên kết cho nút cập nhật ChutroInformationView
        frame.add(updatePhoneButton, gbc);

        // Hiển thị mật khẩu
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx = 1;
        frame.add(new JLabel("********"), gbc);
        // Nút đổi mật khẩu
        gbc.gridx = 2;
        gbc.gridy = 5;
        JButton changePasswordButton = new JButton("Đổi mật khẩu");
        changePasswordButton.addActionListener(e->{});
        //TODO: Thiết lập liên kết cho nút cập nhật ChutroInformationView
        frame.add(changePasswordButton, gbc);

        // Nút quay lại

        gbc.gridx = 1;
        gbc.gridy = 6;
        JButton backButton = new JButton("Quay lại");
        backButton.addActionListener(e -> DashboardChutroController.go_back_dashboardchutro(frame, idChutro));
        frame.add(backButton, gbc);

        // Hiển thị JFrame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChutroInformationView("9"));
    }
}
