package frontend.components.Invoice;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class InvoiceButtonRenderer_watchHoaDon extends JButton implements TableCellRenderer {

    public InvoiceButtonRenderer_watchHoaDon() {
        setText("Xem");
        setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        setBackground(new Color(100, 200, 255)); // Màu xanh nhạt
        setFocusPainted(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setBackground(new Color(50, 150, 255)); // Đổi màu khi chọn
        } else {
            setBackground(new Color(100, 200, 255));
        }
        return this;
    }
}
