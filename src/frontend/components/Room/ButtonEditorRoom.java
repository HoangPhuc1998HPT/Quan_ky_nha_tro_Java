// ButtonEditorRoom.java
package frontend.components.Room;

import backend.model.Room;
import frontend.view.rooms.RoomUpdateInforRoomView;

import javax.management.StringValueExp;
import javax.swing.*;
import java.awt.*;

public class ButtonEditorRoom extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private boolean clicked;

    public ButtonEditorRoom(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> performAction());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        button.setText((value == null) ? "" : value.toString());
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        clicked = false;
        return button.getText();
    }

    private void performAction() {
        if (table != null) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Object roomIdObj = table.getValueAt(selectedRow, 0);
                if (roomIdObj == null) {
                    System.out.println("Không thể lấy roomId từ hàng đã chọn.");
                    return;
                }

                try {
                    // Chuyển đổi roomId sang int
                    int roomId = Integer.parseInt(roomIdObj.toString());
                    System.out.println("Xem chi tiết phòng: " + roomId);

                    // Gọi phương thức getIdChutrobyRoomId
                    int idChutro = Room.getIdChutrobyRoomId(roomId);
                    if (idChutro == -1) {
                        System.out.println("Không tìm thấy ID chủ trọ cho phòng: " + roomId);
                        return;
                    }

                    // Tạo view cập nhật thông tin phòng
                    new RoomUpdateInforRoomView(roomId, idChutro); // Truyền trực tiếp roomId và idChutro
                } catch (NumberFormatException e) {
                    System.out.println("roomId không phải là số hợp lệ: " + roomIdObj);
                }
            }
        }
        fireEditingStopped();
    }


}