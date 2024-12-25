package frontend.view;

import javax.swing.*;
import java.awt.*;
import controller.LoginController;
import frontend.components.CustomButton;

public class homeloginView {
    JFrame f;

    homeloginView() {
        f = new JFrame();
        f.setSize(500,300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout(FlowLayout.CENTER,10,40));

        JLabel welcomeLabel = new JLabel("Chào mừng bạn đến với ứng dụng quản lý thuê trọ");
        welcomeLabel.setFont(new Font("Be Vietnam Pro", Font.PLAIN,16));
        f.add(welcomeLabel);
        System.out.println();

        // Tạo nút nhấn đăng ký
        CustomButton registerButton = new CustomButton("Đăng ký tài khoản");
        registerButton.addActionListener(e -> LoginController.showRegisterView(f));
        registerButton.setBounds(100,100,200,40);
        f.add(registerButton);


        // Tạo nút nhấn đăng nhập
        CustomButton loginButton = new CustomButton(" Đăng nhập tài khoản");
        loginButton.addActionListener(e -> LoginController.showLoginView(f));
        registerButton.setBounds(100,200,200,40);
        f.add(loginButton);


        f.setVisible(true);
    }
    public static void main (String[] args){
        SwingUtilities.invokeLater(homeloginView::new);
    }

}