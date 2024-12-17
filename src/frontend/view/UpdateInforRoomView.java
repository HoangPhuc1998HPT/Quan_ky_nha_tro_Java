package frontend.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static controller.RoomController.getDataRoomFromDataBase;

public class UpdateInforRoomView {
    private String id_room ;
    private JFrame frame;
    private String room_name;
    // 17-12-2024
// đang lỗi chua truy xuất được thông tin để hiên thị cái cần

    public UpdateInforRoomView(String id_room){
        this.id_room = id_room;
        this.room_name = room_name;

        Object roomData = getDataRoomFromDataBase(id_room);

        //"Tên Phòng","Địa chỉ","Giá phòng (VNĐ)","Giá điện (VNĐ)","Giá nước (VNĐ)","Số điện hiện tại","Số nước hiện tại","Giá rác (VNĐ)","Chi phí khác (VNĐ)"};
        // 0            1           2                   3               4               5               6                   7                   8
        // Khởi tạo JFrame
        frame = new JFrame("Cập nhật thông tin phòng");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel chính với GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel updateLable = new JLabel("Cập nhật thông tin phòng " + room_name);
        updateLable.setFont(new Font("Be Vietnam Pro", Font.PLAIN,16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(updateLable, gbc);

        // Tạo các chỉ mục lưu dữ liệu
        // dãy x - 0 tên các label
        // dãy x - 1 các trường hiển thị thông tin hiện tại, nếu không có để trống

        // dãy x - 2 là 1 loạt các nút thay đổi => nếu có thay đổi thì nhấn vào

        // Cập nhật tên phòng:
        JLabel updataRoomName = new JLabel("Tên Phòng");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(updataRoomName, gbc);

        JTextField updateRoomName = new JTextField("20");



        // Cập nhật địa chỉ:
        JLabel updataddress = new JLabel("Địa chỉ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(updataddress, gbc);



        // Cập nhật Giá điện:
        JLabel updataCostElectric = new JLabel("Giá điện");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(updataCostElectric, gbc);


        // Cập nhật Giá nước:
        JLabel updataCostWater = new JLabel("Giá nước");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(updataCostWater, gbc);


        // Cập nhật Số điện hiện tại:
        JLabel numberElectricPresent = new JLabel("Số điện hiện tại");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(numberElectricPresent, gbc);


        // Cập nhật Số nước hiện tại:
        JLabel numberWaterPresent = new JLabel("Số nước hiện tại");
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(numberWaterPresent, gbc);

        // Cập nhật Số nước hiện tại:
        JLabel garbagePrice = new JLabel("Giá rác");
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(garbagePrice, gbc);

        // Chi phí khác
        JLabel anotherPrice = new JLabel("Giá rác");
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(anotherPrice, gbc);



        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    // Phương thức helper để chuyển Object[] thành List<String>

    public static void main(String[] args) {
        String id_phong = "T001";
        String room_name = "Phòng 101";
        SwingUtilities.invokeLater(() -> new UpdateInforRoomView(id_phong));
    }
}
