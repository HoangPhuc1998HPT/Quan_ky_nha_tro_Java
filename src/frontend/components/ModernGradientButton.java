package frontend.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ModernGradientButton extends JButton {
    public ModernGradientButton(String text) {
        super(text);
        setContentAreaFilled(false); // Xóa nền mặc định
        setFocusPainted(false); // Xóa viền khi được chọn
        setBorderPainted(false); // Xóa đường viền
        setFont(new Font("Be Vietnam Pro", Font.BOLD, 14)); // Thiết lập font
        setForeground(Color.WHITE); // Màu chữ
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Hiệu ứng bóng
        g2.setColor(new Color(0, 0, 0, 50)); // Màu bóng (đen trong suốt)
        g2.fillRoundRect(2, 4, getWidth() - 4, getHeight() - 4, 40, 40);

        // Tạo gradient màu cho nút
        GradientPaint gradient = new GradientPaint(0, 0, new Color(72, 191, 227), getWidth(), getHeight(), new Color(123, 237, 159));
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

        // Vẽ chữ
        super.paintComponent(g);
    }

    @Override
    public void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255, 150)); // Viền trắng mờ
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);
    }

    public static void main(String[] args) {
        // Tạo JFrame để hiển thị nút
        JFrame frame = new JFrame("Modern Gradient Button");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Tạo nút gradient hiện đại
        ModernGradientButton loginButton = new ModernGradientButton("Đăng nhập");
        loginButton.setPreferredSize(new Dimension(200, 50)); // Kích thước nút

        // Thêm nút vào JFrame
        frame.add(loginButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
