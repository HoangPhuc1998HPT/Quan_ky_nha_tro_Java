package frontend.components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setPreferredSize(new Dimension(150, 40));// Định kích thước mặc định
        //this.setBackground(new Color(0, 123, 255)); // Màu nền xanh dương
        //this.setForeground(Color.WHITE); // Màu chữ trắng
        this.setFocusPainted(false); // Xóa viền khi được chọn

    }
    public static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setFont(new Font("Arial", Font.BOLD, 12));
            setBackground(new Color(100, 200, 255));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "Xem Chi Tiết");
            return this;
        }
    }
}
