package frontend.components;

import controller.InvoicesController;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
public class InvoiceButtonEditor_1 extends AbstractCellEditor implements TableCellEditor {
    private final JButton button;
    private boolean isPushed;

    public InvoiceButtonEditor_1(JTable table) {
        button = new JButton("Chi tiết");
        button.setOpaque(true);
        // TODO: cần fix
        button.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int idhoadon = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
                InvoicesController.openInvoiceDetails(
                        AdminController.getChutroId(), // ID chủ trọ
                        AdminController.getTenantIdByRoomId(idhoadon), // ID người thuê
                        idhoadon,
                        AdminController.getRoomIdByInvoiceId(idhoadon) // ID phòng
                );
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Chi tiết";
    }
}
