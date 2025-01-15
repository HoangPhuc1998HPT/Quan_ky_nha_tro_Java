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
        setText(value == null ? "Xem" : value.toString());
        return this;
    }
}