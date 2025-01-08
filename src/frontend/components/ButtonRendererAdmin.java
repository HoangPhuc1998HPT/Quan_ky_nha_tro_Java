package frontend.components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRendererAdmin extends JButton implements TableCellRenderer {
    public ButtonRendererAdmin() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value == null ? "Kích hoạt" : value.toString());
        return this;
    }

}
