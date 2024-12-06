package frontend.view;

import javax.swing.*;
import java.awt.*;

public class ChutroDashboardView {
    private final JFrame frame;

    public ChutroDashboardView() {
        frame = new JFrame("ChutroDashboardView");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Giữ các thành phần ở giữa
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel DashboarLabel = new JLabel("Dashboard Chủ trọ");
        DashboarLabel.setFont(new Font("Be Vietnam Pro", Font.PLAIN,16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(DashboarLabel, gbc);

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);





    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChutroDashboardView::new);
    }

}
