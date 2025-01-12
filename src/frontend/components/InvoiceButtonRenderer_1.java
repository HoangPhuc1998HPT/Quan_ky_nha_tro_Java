package frontend.components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class InvoiceButtonRenderer_1 extends JButton implements TableCellRenderer {

    public InvoiceButtonRenderer_1() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value == null ? "Chi tiết" : value.toString());
        return this;
    }
}
