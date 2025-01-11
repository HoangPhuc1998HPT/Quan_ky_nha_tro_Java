package frontend.components;

import backend.model.Invoices;
import controller.InvoicesController;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class InvoiceButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private final JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;

    public InvoiceButtonEditor(JTable table) {
        this.table = table;
        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(e -> {
            fireEditingStopped(); // Dừng chỉnh sửa
            int row = table.getSelectedRow();
            if (row != -1) {
                String invoiceId = table.getValueAt(row, 0).toString(); // ID hóa đơn
                String tenantName = table.getValueAt(row, 2).toString(); // Tên người thuê

                // Hiển thị hộp thoại xác nhận thanh toán
                int result = JOptionPane.showConfirmDialog(
                        button,
                        "Bạn có muốn xác nhận hóa đơn của " + tenantName + " đã được thanh toán?",
                        "Xác Nhận Thanh Toán",
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    // Gọi controller xử lý thanh toán
                    Invoices.markInvoiceAsPaid(invoiceId);
                    table.setValueAt("Đã Thanh Toán", row, table.getColumn("Tình Trạng").getModelIndex());
                    JOptionPane.showMessageDialog(button, "Hóa đơn đã được thanh toán.");
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "Thanh Toán" : value.toString();
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

