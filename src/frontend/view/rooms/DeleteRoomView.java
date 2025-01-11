package frontend.view.rooms;

import controller.RoomController;

import javax.swing.*;
import java.awt.*;

public class DeleteRoomView {
    private JFrame frame;

    public DeleteRoomView(int idRoom, JFrame parentFrame) {
        frame = new JFrame("Xóa phòng");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel confirmationLabel = new JLabel("Bạn có chắc chắn muốn xóa phòng này không?", SwingConstants.CENTER);
        confirmationLabel.setFont(new Font("Be Vietnam Pro", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(confirmationLabel, gbc);

        JButton confirmButton = new JButton("Xóa");
        confirmButton.addActionListener(e -> {
            RoomController.deletePhong(parentFrame, idRoom);
            frame.dispose();
        });
        gbc.gridy = 1;
        gbc.gridx = 0;
        frame.add(confirmButton, gbc);

        JButton cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(e -> frame.dispose());
        gbc.gridx = 1;
        frame.add(cancelButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
