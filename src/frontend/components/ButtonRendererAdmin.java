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
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnActive = new JButton("Active");
        JButton btnDelete = new JButton("Delete");
        panel.add(btnActive);
        panel.add(btnDelete);
        return panel;
    }

}
