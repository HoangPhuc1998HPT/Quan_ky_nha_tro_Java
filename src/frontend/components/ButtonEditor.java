package frontend.components;

import controller.AdminController;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private final JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;

    public ButtonEditor(JTable table) {
        this.table = table;
        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(e -> {
            fireEditingStopped(); // Dừng chế độ chỉnh sửa
            int row = table.getSelectedRow();
            AdminController.activateUser(row, table);
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "Kích hoạt" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
