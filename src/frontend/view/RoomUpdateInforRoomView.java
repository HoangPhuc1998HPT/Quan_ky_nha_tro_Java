package frontend.view;

import javax.swing.*;

import java.awt.*;

import static controller.RoomController.*;

public class RoomUpdateInforRoomView {
    private String id_room;
    private String id_chutro;
    private JFrame frame;
    private JPanel roomPanel;

    public RoomUpdateInforRoomView(String id_room, String id_chutro) {
        this.id_room = id_room;
        this.id_chutro = id_chutro;
        this.roomPanel = roomPanel;
        String tenphong = GetNameRoom(id_room);
        int oldNumberE = GetNumberElectricAndWater(id_room)[0];
        int oldNumberW = GetNumberElectricAndWater(id_room)[1];

        // Tạo frame
        frame = new JFrame("Cập nhật thông tin phòng:" + (tenphong));
        frame.setSize(550, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tạo JPanel để chứa tiêu đề
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titlelb = new JLabel("Nhập thông tin phòng: " + tenphong, SwingConstants.CENTER);
        titlelb.setFont(new Font("Be Vietnam Pro", Font.BOLD, 18)); // Chỉnh font chữ lớn hơn cho đẹp
        titlePanel.add(titlelb, BorderLayout.CENTER);

        // Cấu hình GridBagConstraints để căn giữa toàn bộ panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Chiếm toàn bộ chiều ngang
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 20, 10); // Tạo khoảng cách giữa các thành phần
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Để tiêu đề chiếm toàn bộ chiều ngang

        // Thêm panel tiêu đề vào frame
        frame.add(titlePanel, gbc);

        // Thêm khoảng trắng
        JLabel titlespace = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        frame.add(titlespace, gbc);

        // Hiển thị số điện cũ ==> nếu chưa từng tập nhật ==> mặc định là  0
        JLabel labelOldElb = new JLabel("Số điện cũ: ");
        labelOldElb.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Quay lại 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Căn trái
        frame.add(labelOldElb, gbc);

        // Lấy thông tin số điện cũ:
        JLabel numberE = new JLabel(String.valueOf(oldNumberE));
        numberE.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Quay lại 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Căn trái
        frame.add(numberE, gbc);


        // Label Nhập thông tin tiền điện
        JLabel labelCostELb = new JLabel("Số điện mới ");
        labelCostELb.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1; // Quay lại 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Căn trái
        frame.add(labelCostELb, gbc);

        // TextField Nhập liệu
        JTextField textE = new JTextField(20);
        textE.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        textE.setPreferredSize(new Dimension(200, 25)); // Chiều rộng 200px, chiều cao 25px
        gbc.gridx = 1;
        gbc.gridy = 4;
        frame.add(textE, gbc);

        // Hiển thị số nước cũ ==> nếu chưa từng tập nhật ==> mặc định là  0
        JLabel labelOldWater = new JLabel("Số nước cũ: ");
        labelOldWater.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1; // Quay lại 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Căn trái
        frame.add(labelOldWater, gbc);

        // Lấy thông tin số điện cũ:
        JLabel numberW = new JLabel(String.valueOf(oldNumberW));
        numberW.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1; // Quay lại 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Căn trái
        frame.add(numberW, gbc);


        // Label Nhập thông tin tiền điện
        JLabel labelCostWLb = new JLabel("Số nước mới ");
        labelCostWLb.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1; // Quay lại 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Căn trái
        frame.add(labelCostWLb, gbc);

        // TextField Nhập liệu
        JTextField textW = new JTextField(20);
        textW.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        textW.setPreferredSize(new Dimension(200, 25)); // Chiều rộng 200px, chiều cao 25px
        gbc.gridx = 1;
        gbc.gridy = 6;
        frame.add(textW, gbc);

        // Lập thông tin ghi đơn vị cho các thông số
        // Ghi đơn vị cho số điện cũ
        JLabel unitOldE = new JLabel("kWh");
        unitOldE.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 3; // Hàng số điện cũ
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(unitOldE, gbc);

        // Ghi đơn vị cho số điện mới
        JLabel unitNewE = new JLabel("kWh");
        unitNewE.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 4; // Hàng số điện mới
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(unitNewE, gbc);

        // Ghi đơn vị cho số nước cũ
        JLabel unitOldW = new JLabel("m³");
        unitOldW.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 5; // Hàng số nước cũ
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(unitOldW, gbc);

        // Ghi đơn vị cho số nước mới
        JLabel unitNewW = new JLabel("m³");
        unitNewW.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 6; // Hàng số nước mới
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(unitNewW, gbc);


        // Lấy nội dung entry Electric nhập vào để gửi thông tin cập nhật đến database
        String inputTextE = textE.getText(); // Lấy chuỗi nhập từ JTextField
        int getTextE = 0;
        try {
            getTextE = Integer.parseInt(inputTextE);
            //System.out.println("Giá trị nhập vào: " + getTextE);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập vào một số nguyên hợp lệ!");
        }

        // Lấy nội dung entry Water nhập vào để gửi thông tin cập nhật đến database
        String inputTextW = textW.getText(); // Lấy chuỗi nhập từ JTextField
        int getTextW = 0;
        try {
            getTextW = Integer.parseInt(inputTextW);
            //System.out.println("Giá trị nhập vào: " + getTextW);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập vào một số nguyên hợp lệ!");
        }


        // Nút cập nhật thông tin
        JButton updateButton = new JButton("Cập nhật thông tin");
        updateButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 8; // Hàng số nước mới
        gbc.gridwidth = 1;
        //gbc.anchor = GridBagConstraints.WEST;
        updateButton.addActionListener(GoToUpdateInfor(textE, textW, id_room));
        frame.add(updateButton, gbc);

        // Nút quay lại
        // Nút cập nhật thông tin
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Be Vietnam Pro", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 9; // Hàng số nước mới
        gbc.gridwidth = 1;
        //gbc.anchor = GridBagConstraints.WEST;
        backButton.addActionListener(GoToBackRoomView(id_room, id_chutro, roomPanel));
        frame.add(backButton, gbc);

        // Thêm frame hiển thị
        frame.setLocationRelativeTo(null); // Căn giữa màn hình
        frame.setVisible(true);


    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RoomUpdateInforRoomView("R001", "CT002");
        });
    }
}
