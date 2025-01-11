package frontend.components.Chutro;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRendererChutro extends JButton implements TableCellRenderer {
    public ButtonRendererChutro() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "Xem chi tiết" : value.toString());
        return this;
    }
}
