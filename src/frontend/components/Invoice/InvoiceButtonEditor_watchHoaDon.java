package frontend.components.Invoice;

import backend.model.*;
import frontend.view.Invoices.InvoiceFormView;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.EventObject;

public class InvoiceButtonEditor_watchHoaDon extends AbstractCellEditor implements TableCellEditor {
    private final JButton button;
    private JTable table;
    private boolean isClicked;

    public InvoiceButtonEditor_watchHoaDon(JTable table, int idChutro) {
        super();
        this.table = table;
        button = new JButton("Xem");
        button.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        button.setBackground(new Color(100, 200, 255));
        button.setFocusPainted(false);

        // Sự kiện khi nhấn nút
        button.addActionListener(e -> {
            fireEditingStopped();
            int row = table.getSelectedRow();
            if (row != -1) {
                try {
                    Object idValue = table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
                    if (idValue instanceof Integer) {
                        int idHoaDon = (int) idValue;

                        // Lấy thông tin hóa đơn và mở giao diện chi tiết
                        int roomId = Invoices.getIdRoomFromIdhoadon(idHoaDon);
                        Object[] invoiceDetails = InvoiceDetail.getInvoiceDetailByIdRoom(roomId);
                        Chutro chutro = Chutro.getChutrobyChutroID(idChutro);
                        NguoiThueTro nguoiThueTro = NguoiThueTro.getTenantByRoomId(roomId);
                        Room room = Room.getRoomById(roomId);

                        if (invoiceDetails != null && chutro != null && nguoiThueTro != null && room != null) {
                            new InvoiceFormView(chutro, nguoiThueTro, invoiceDetails, room);
                        } else {
                            throw new Exception("Không tìm thấy thông tin chi tiết hóa đơn.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            button,
                            "Lỗi khi xem chi tiết hóa đơn: " + ex.getMessage(),
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        button.setText(value == null ? "Xem" : value.toString());
        button.setBackground(new Color(50, 150, 255)); // Thay đổi màu khi được nhấn
        isClicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Xem";
    }

    @Override
    public boolean stopCellEditing() {
        isClicked = false;
        return super.stopCellEditing();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }
}
