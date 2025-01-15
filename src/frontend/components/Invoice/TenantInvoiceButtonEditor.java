package frontend.components.Invoice;

import backend.model.Invoices;
import backend.model.Room;
import controller.NguoiThueTroController;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionListener;

public class TenantInvoiceButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private final JButton button;
    private String label;

    public TenantInvoiceButtonEditor(JFrame frame, JTable table, int idNguoiThueTro, String landlordName) {
        button = new JButton();
        button.setOpaque(true);

        // Hành động khi nhấn nút
        ActionListener actionListener = e -> {
            fireEditingStopped(); // Dừng chỉnh sửa
            int row = table.getSelectedRow();
            int invoiceId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
            int idPhong = Invoices.getRoomIdFromInvoiceID(invoiceId);
            int idChutro = Room.getIdChutrobyRoomId(idPhong);

            // Gọi controller để xử lý
            NguoiThueTroController.goToOpenInvoiceView(idNguoiThueTro, idPhong, idChutro, invoiceId);
        };

        button.addActionListener(actionListener);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "Xem" : value.toString();
        button.setText(label);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }
}
