package frontend.components.Invoice;

import backend.model.Invoices;
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
                try {
                    // Lấy ID hóa đơn từ bảng
                    int idhoadon = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());

                    // Lấy thông tin liên quan qua AdminController
                    int idChutro = Invoices.getIdChutroFromIdhoadon(idhoadon);
                    int idNguoiThueTro = Invoices.getIdNguoiThueFromIdhoadon(idhoadon);
                    int idRoom = Invoices.getIdRoomFromIdhoadon(idhoadon);

                    // Mở chi tiết hóa đơn
                    InvoicesController.openInvoiceDetails(idChutro, idNguoiThueTro, idhoadon, idRoom);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xử lý hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
