package frontend.components.Invoice;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TenantInvoiceButtonRenderer extends JButton implements TableCellRenderer {
    public TenantInvoiceButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Đặt văn bản cho nút
        setText(value == null ? "Xem" : value.toString());

        // Thay đổi màu nền và màu chữ
        setBackground(new Color(81, 129, 216)); // Màu xanh nhạt
        setForeground(Color.BLACK); // Màu chữ đen

        // Đổi màu khi hàng được chọn
        if (isSelected) {
            setBackground(new Color(36, 101, 219)); // Màu xanh đậm hơn
            setForeground(Color.WHITE); // Màu chữ trắng
        }

        return this;
    }
}