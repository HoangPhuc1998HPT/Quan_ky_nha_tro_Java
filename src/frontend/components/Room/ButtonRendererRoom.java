package frontend.components.Room;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRendererRoom extends JButton implements TableCellRenderer {
    public ButtonRendererRoom() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        return this;
    }
}