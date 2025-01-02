package frontend.view;

import backend.Login;
import controller.LoginController;
import frontend.components.CustomButton;
import frontend.components.ModernGradientButton;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class loginView {
    private final JFrame f;
    private final JTextField entryUsername;
    private final JPasswordField entryPassword ;

    public loginView() {

        f = new JFrame("Đăng nhập");
        f.setSize(400, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        // Tạo JPanel để căn giữa nội dung
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Giữ các thành phần ở giữa
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tạo tiêu đề cho JFrame
        JLabel labelTitle = new JLabel("Đăng nhập");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(labelTitle, gbc);


        // Tạo nhãn và trường nhập cho tên tài khoản
        JLabel labelUsername = new JLabel("Tên tài khoản");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelUsername, gbc);

        entryUsername = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(entryUsername, gbc);

        // Tạo nhãn và trường nhập cho mật khẩu
        JLabel labelPassword = new JLabel("Nhập mật khẩu");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelPassword, gbc);

        entryPassword = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(entryPassword, gbc);




        // Tạo nút Đăng nhập
        CustomButton buttonLogin = new CustomButton("Đăng nhập");
        gbc.gridx = 1;
        gbc.gridy = 3;
        //gbc.anchor = GridBagConstraints.LINE_END; // Căn nút về phía phải`
        buttonLogin.addActionListener(e -> {
            String username = entryUsername.getText().trim();
            String password = new String(entryPassword.getPassword());
            System.out.println(username);
            System.out.println(password.toCharArray());
            LoginController.checkLogin(username, password, f);
        });
        panel.add(buttonLogin, gbc);

        // Thêm JPanel vào trung tâm của JFrame
        f.add(panel, BorderLayout.CENTER);

        JButton existWindow = new JButton("Thoát");
        gbc.gridx = 1;
        gbc.gridy = 4;
        existWindow.addActionListener(e -> LoginController.exist_Window());
        panel.add(existWindow, gbc);

        // Đặt JFrame ở giữa màn hình
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(loginView::new);
    }


}


