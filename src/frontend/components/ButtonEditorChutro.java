package frontend.components;

import backend.model.Chutro;
import controller.AdminController;
import frontend.view.chutro.ChutroDashboardView;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditorChutro extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private String cccd; // CCCD của chủ trọ
    private boolean clicked;

    public ButtonEditorChutro() {
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        cccd = table.getValueAt(row, 4).toString(); // Giả sử CCCD ở cột thứ 4
        button.setText("Xem chi tiết");
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            // Lấy username từ CCCD
            String username = Chutro.getUsernameByCCCD(cccd);
            if (username != null) {
                AdminController.goToChutroDashboardFromAdmin(username);
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy Username cho CCCD: " + cccd);
            }
        }
        clicked = false;
        return "Xem chi tiết";
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
    }
}
