package frontend.components.Invoice;

import backend.model.Invoices;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class InvoiceButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private final JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;

    public InvoiceButtonEditor(JFrame frame,JTable table,int idChutro, String landlordName, int roomCount) {
        this.table = table;
        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(e -> {
            fireEditingStopped(); // Dừng chỉnh sửa
            int row = table.getSelectedRow();
            if (row != -1) {
                try {
                    // Lấy id hóa đơn từ cột ẩn
                    Object idValue = table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
                    if (idValue == null || !(idValue instanceof Integer)) {
                        throw new NumberFormatException("ID hóa đơn không hợp lệ.");
                    }
                    int invoiceId = (int) idValue;

                    String tenantName = table.getValueAt(row, 2).toString();

                    // Hiển thị hộp thoại xác nhận thanh toán
                    int result = JOptionPane.showConfirmDialog(
                            button,
                            "Bạn có muốn xác nhận hóa đơn của " + tenantName + " đã được thanh toán?",
                            "Xác Nhận Thanh Toán",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (result == JOptionPane.YES_OPTION) {
                        // Gọi controller xử lý thanh toán
                        boolean success = Invoices.markInvoiceAsPaid(frame,invoiceId, idChutro,  landlordName,  roomCount);
                        frame.setVisible(false);
                        if (success) {
                            // Cập nhật giao diện
                            table.setValueAt(true, row, table.getColumn("Tình Trạng").getModelIndex());
                            JOptionPane.showMessageDialog(button, "Hóa đơn đã được xác nhận thanh toán.");
                        } else {
                            throw new Exception("Không thể cập nhật trạng thái thanh toán trong cơ sở dữ liệu.");
                        }
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(
                            button,
                            "ID hóa đơn không hợp lệ. Vui lòng kiểm tra dữ liệu.",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            button,
                            "Lỗi khi xử lý thanh toán: " + ex.getMessage(),
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE
                    );
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
