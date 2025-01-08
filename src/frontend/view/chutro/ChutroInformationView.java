package frontend.view.chutro;

import backend.model.Chutro;

import javax.swing.*;
import java.awt.*;

public class ChutroInformationView {
    private JFrame frame;
   // private Chutro chutro;
    public ChutroInformationView(String idChutro, Chutro chutro) {
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

        // Hiển thị thông tin
        gbc.gridwidth = 1; // Reset gridwidth về 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 1;
        frame.add(new JLabel(chutro.getFullName()), gbc);
        gbc.gridx = 2; // Nút cập nhật
        JButton updateNameButton = new JButton("Cập nhật");
        updateNameButton.addActionListener(e->new UpdateFullNameView(idChutro, chutro.getFullName()));
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
        updatePhoneButton.addActionListener(e->new UpdatePhoneView(idChutro, chutro.getPhone()));
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
        changePasswordButton.addActionListener(e->new UpdatePasswordView(idChutro));
        frame.add(changePasswordButton, gbc);

        // Nút quay lại

        gbc.gridx = 1;
        gbc.gridy = 6;
        JButton backButton = new JButton("Quay lại");
        backButton.addActionListener(e -> frame.dispose());
        frame.add(backButton, gbc);

        // Hiển thị JFrame
        frame.setVisible(true);
    }


}
