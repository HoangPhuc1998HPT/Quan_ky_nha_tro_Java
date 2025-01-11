package frontend.components.Admin;

import controller.AdminController;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditorAdmin extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton btnActive;
    private JButton btnDelete;
    private JTable table; // Bảng hiện tại để truy cập dữ liệu hàng

    public ButtonEditorAdmin(JTable table) {
        this.table = table;
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Nút Active
        btnActive = new JButton("Active");
        btnActive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Dừng chế độ chỉnh sửa
                int row = table.getSelectedRow();
                AdminController.activateUser(row, table);
            }
        });

        // Nút Delete
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Dừng chế độ chỉnh sửa
                int row = table.getSelectedRow();
                AdminController.deleteUser(row, table);
            }
        });

        // Thêm hai nút vào panel
        panel.add(btnActive);
        panel.add(btnDelete);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null; // Không cần giá trị trả về
    }
}
