package frontend.components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

// Button Renderer for Người Thuê Trọ
public class ButtonRendererNguoiThueTro extends JButton implements TableCellRenderer {
    public ButtonRendererNguoiThueTro() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getBackground());
        }
        return this;
    }
}