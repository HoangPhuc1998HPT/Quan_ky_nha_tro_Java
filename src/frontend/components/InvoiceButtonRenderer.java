package frontend.components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class InvoiceButtonRenderer extends JButton implements TableCellRenderer {
    public InvoiceButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value == null ? "Nút" : value.toString());
        return this;
    }
}
