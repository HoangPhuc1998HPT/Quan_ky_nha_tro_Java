package frontend.components;

import controller.RoomController;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class ButtonEditorNguoiThueTro extends DefaultCellEditor {
    private JButton button;
    private JTable table; // Tham chiếu đến JTable

    public ButtonEditorNguoiThueTro(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> performAction());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table; // Lưu tham chiếu đến JTable
        button.setText((value == null) ? "" : value.toString());
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return button.getText();
    }

    private void performAction() {
        if (table != null) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Lấy IDNguoiThue từ cột ẩn
                Object tenantId = table.getValueAt(selectedRow, 0);
                if (tenantId != null) {
                    System.out.println("Tenant ID: " + tenantId);
                    // Gọi RoomController hoặc thực hiện logic khác
                    RoomController.goToRoomInforView((int) tenantId);
                }
            }
        }
        fireEditingStopped(); // Dừng chế độ chỉnh sửa
    }
}
