package frontend.components.NguoiThueTro;

import backend.model.Chutro;
import backend.model.Room;
import controller.RoomController;

import javax.swing.*;
import java.awt.*;

public class ButtonEditorNguoiThueTro_0 extends DefaultCellEditor {
    private String label;
    private JButton button;
    private boolean clicked;
    private JTable table; // Tham chiếu đến bảng
    private int userId;

    public ButtonEditorNguoiThueTro_0(JCheckBox checkBox, int userId) {
        super(checkBox);
        this.userId = userId;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> performAction());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table; // Lưu tham chiếu đến bảng
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        clicked = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    private void performAction() {
        if (table != null) {
            int selectedRow = table.getSelectedRow(); // Lấy hàng được chọn
            if (selectedRow != -1) {
                // Lấy Room ID từ cột ẩn (index 0)
                Object roomName = table.getValueAt(selectedRow, 1);
                Object fullName = table.getValueAt(selectedRow, 2);
                int roomId = Room.getIDRoomFromRoomName((String) roomName);
                Room room = Room.getRoomById(roomId);
                int chutroID = Chutro.getIDChutroFromHoTen((String) fullName);
                System.out.println("Room ID: " + roomId); // In ra Room ID để kiểm tra
                RoomController.goToRoomInfoView(room); // Điều hướng đến RoomInforView với roomId
            }
        }
        fireEditingStopped(); // Dừng chỉnh sửa
    }

}
