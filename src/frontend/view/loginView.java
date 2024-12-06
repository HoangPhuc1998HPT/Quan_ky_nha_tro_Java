package frontend.view;

import backend.Login;

import javax.swing.*;
import java.awt.*;

public class loginView {
    private JFrame f;
    private JTextField entryUsername;
    private JPasswordField entryPassword ;

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
        JButton buttonLogin = new JButton("Đăng nhập");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END; // Căn nút về phía phải
        panel.add(buttonLogin, gbc);

        // Thêm JPanel vào trung tâm của JFrame
        f.add(panel, BorderLayout.CENTER);

        // Đặt JFrame ở giữa màn hình
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    private void submitLogin() {
        try {
            String username = entryUsername.getText().trim();
            String password = new String(entryPassword.getPassword()).trim();
            if (!username.isEmpty() && !password.isEmpty()) {
                System.out.println(" Đăng nhập với User: " + username + " Password " + password);
                Login.check_login(f, username, password);
            } else {
                JOptionPane.showMessageDialog(f, "Please fill in both username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error in submitLogin: " + e);
            JOptionPane.showMessageDialog(f, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(loginView::new);
    }


}


