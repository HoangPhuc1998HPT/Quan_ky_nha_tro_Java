package frontend.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChutroInforView {
    public ChutroInforView(ArrayList<Object> id_chutro_infor) {
        JFrame frame = new JFrame("Thông tin chủ trọ");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField ho_ten = new JTextField();
        JTextField dia_chi = new JTextField();







        frame.setVisible(true);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChutroInforView("DemoUser", "DemoPassword"));
    }
}

