// ButtonEditorRoom.java
package frontend.components.Room;

import javax.swing.*;
import java.awt.*;

public class ButtonEditorRoom extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private boolean clicked;

    public ButtonEditorRoom(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> performAction());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        button.setText((value == null) ? "" : value.toString());
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        clicked = false;
        return button.getText();
    }

    private void performAction() {
        if (table != null) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Object roomId = table.getValueAt(selectedRow, 0);
                System.out.println("Xem chi tiết phòng: " + roomId);
                // Thực hiện logic chi tiết ở đây
            }
        }
        fireEditingStopped();
    }
}