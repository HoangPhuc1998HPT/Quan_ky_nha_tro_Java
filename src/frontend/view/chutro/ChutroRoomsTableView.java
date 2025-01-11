package frontend.view.chutro;

import controller.RoomController;
import frontend.components.CustomButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static controller.DashboardChutroController.go_back_dashboardchutro;


public class ChutroRoomsTableView {
    private JFrame frame;
    private JTable roomTable;
    private DefaultTableModel tableModel;

    public ChutroRoomsTableView(int idChutro) {
        // Tạo JFrame chính
        frame = new JFrame("Danh sách phòng trọ");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Tiêu đề bảng
        JLabel title = new JLabel("Danh sách các phòng thuộc chủ trọ: " + idChutro);
        title.setFont(new Font("Be Vietnam Pro", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(title, BorderLayout.NORTH);

        // Tạo bảng JTable
        String[] columnNames = {"Tên Phòng", "Người Thuê", "Xem Chi Tiết", "ID Room (Ẩn)"}; // Thêm cột ẩn
        tableModel = new DefaultTableModel(columnNames, 0);
        roomTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // Chỉ cột "Xem Chi Tiết" cho phép tương tác
            }
        };
        // Ẩn cột ID Room
        roomTable.getColumnModel().getColumn(3).setMinWidth(0);
        roomTable.getColumnModel().getColumn(3).setMaxWidth(0);
        roomTable.getColumnModel().getColumn(3).setWidth(0);

        // Tùy chỉnh giao diện bảng
        roomTable.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        roomTable.setRowHeight(35);
        roomTable.getTableHeader().setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        roomTable.getTableHeader().setBackground(new Color(200, 200, 255));

        // Tải dữ liệu phòng từ backend
        List<String[]> roomList = RoomController.getRoomListByChutro(idChutro);
        for (String[] room : roomList) {
            tableModel.addRow(new String[]{room[1], room[2], "Xem Chi Tiết", room[0]}); // Cột 0 chứa ID Room (ẩn)
        }

        // Thêm nút "Xem Chi Tiết"
        roomTable.getColumn("Xem Chi Tiết").setCellRenderer(new CustomButton.ButtonRenderer());
        roomTable.getColumn("Xem Chi Tiết").setCellEditor(new ButtonEditor(new JCheckBox(), idChutro));

        // Đưa JTable vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(roomTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Nút quay lại
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        backButton.addActionListener(e -> go_back_dashboardchutro(frame, idChutro));
        frame.add(backButton, BorderLayout.SOUTH);

        // Hiển thị JFrame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Editor để xử lý sự kiện nút trong bảng
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        private int idChutro;

        public ButtonEditor(JCheckBox checkBox, int idChutro) {
            super(checkBox);
            this.idChutro = idChutro;
            button = new JButton();
            button.setOpaque(true);
            button.setFont(new Font("Be Vietnam Pro", Font.BOLD, 12));
            button.setBackground(new Color(100, 200, 255));
            button.addActionListener(e -> performAction());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = value != null ? value.toString() : "Xem Chi Tiết";
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
            int selectedRow = roomTable.getSelectedRow();
            Object roomIdObject = tableModel.getValueAt(selectedRow, 3); // Lấy ID Room từ cột ẩn
            int roomId = Integer.parseInt(roomIdObject.toString());
            System.out.println("Room ID: " + roomId); // Log để kiểm tra Room ID

            // Gọi RoomView qua Controller
            RoomController.openRoomView(roomId, idChutro);
        }

    }

}

