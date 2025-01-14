package frontend.view.Invoices;

import backend.model.*;
import controller.InvoicesController;

import javax.management.StringValueExp;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// Form này được tạo để hiển thị hóa đơn xem

public class InvoiceFormView {
    public InvoiceFormView(Chutro chutro,NguoiThueTro nguoithuetro, Object[] invoiceDetail, Room room) {
        //Chutro chutro, NguoiThueTro nguoithuetro, InvoiceDetail invoiceDetail, Room room
        // Detail thông tin đua vào
        // Chutro chutro (IDchutro, userID,HOten, Phone, CCCD)
        // nguoithuetro (idnguoithue, userID,hoten, phone, CCCD)
        // invoiceDetail (sodienthangtruoc,sonuocthangtruoc,tiennha,giadien,gianuoc,tienrac,giamgia,ngayhoadon) //TODO: CHờ hiếu
        // room("idRoom",TenPhong,TenNguoiThue,GiaPhong,Giadien,Gianuoc,Tienrac,currentElectricity,currentWater) // tiền rác lấy từ hóa đơn
        //InvoiceDetail invoiceDetail = getInforInvoiceDetail(invoice.get(0));
        // Tạo JFrame

        int idCTHD = (int) invoiceDetail[0];
        int sodienuse = (int) invoiceDetail[1];
        int sonuocuse = (int) invoiceDetail[2];
        double tiennha = (double) invoiceDetail[3];
        double giadien = (double)invoiceDetail[4];
        double gianuoc = (double)invoiceDetail[5];
        double tienrac = (double)invoiceDetail[6];
        double giamgia = (double)invoiceDetail[7];
        Date ngayhoadon = (Date) invoiceDetail[8];
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = ngayhoadon != null ? formatter.format(ngayhoadon) : "Không xác định";

        //TODO : phải làm
        Object invoiceDetail1 = InvoiceDetail.getInvoiceDetailForUpdate(idCTHD);


        double Tongchiphichuathue = tiennha*1.1 + giadien*sodienuse*1.1  + gianuoc*sonuocuse*1.1  + tienrac*1.1 - giamgia ;
        double Tongtienthue = tiennha*0.1 + giadien*sodienuse*0.1  + gianuoc*sonuocuse*0.1  + tienrac*0.1;
        double Tongchiphi = tiennha*1.1 + giadien*sodienuse*1.1  + gianuoc*sonuocuse*1.1  + tienrac*1.1 - giamgia - Tongtienthue ;

        int idhoadon = Invoices.getIdHoadonFromidCTHD(idCTHD);

        System.out.println("id CTHD " + idhoadon);

        //Invoices invoice = Invoices.getInvoiceById(idhoadon);

        System.out.println("id CTHD " + idhoadon);

        JFrame frame = new JFrame("HÓA ĐƠN GIÁ TRỊ GIA TĂNG");
        frame.setSize(850, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.add(mainPanel, BorderLayout.CENTER);

        // Tạo viền ngoài
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        // Tạo padding bên trong
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // Kết hợp viền + padding
        mainPanel.setBorder(BorderFactory.createCompoundBorder(emptyBorder, lineBorder));

        // Tiêu đề hóa đơn
        JLabel title = new JLabel("HÓA ĐƠN GIÁ TRỊ GIA TĂNG", SwingConstants.CENTER);
        title.setFont(new Font("Be Vietnam Pro", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        // Thông tin chung
        JPanel infoPanelTop = new JPanel(new GridLayout(2, 2, 2, 2));
        infoPanelTop.setBorder(BorderFactory.createEmptyBorder(0, 0, 0 , 10));

        infoPanelTop.add(new JLabel(""));
        // TODO: Tạo 1 hàm chạy số ký hiệu hóa đơn @Hiếu
        infoPanelTop.add(new JLabel("Ký hiệu: 1C21TAA", SwingConstants.RIGHT));
        infoPanelTop.add(new JLabel(""));
        // TODO: Tạo 1 hàm chạy số cho hóa đơn @Hiếu
        infoPanelTop.add(new JLabel("Số: 123", SwingConstants.RIGHT));

        mainPanel.add(infoPanelTop);

        JPanel rowDate = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        //TODO: Trích xuất dữ liệu từ CTHoadon.ngaythutiendukien
        rowDate.add(new JLabel("Ngày: "+ formattedDate)); //
        mainPanel.add(rowDate);

        // Thông tin người bán
        JPanel infoPanelSeller = new JPanel();
        infoPanelSeller.setLayout(new BoxLayout(infoPanelSeller, BoxLayout.Y_AXIS));
        infoPanelSeller.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

        // Hàng 1
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row1.add(new JLabel("Tên người bán:" + chutro.getFullName()));
        infoPanelSeller.add(row1);

        // Hàng 2
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row2.add(new JLabel("Mã số thuế:"));
        row2.add(new JLabel("Đang cập nhật")); // mã số thuê UIT
        infoPanelSeller.add(row2);

        // Hàng 3
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row3.add(new JLabel("Địa chỉ:"));
        row3.add(new JLabel("Khu Phố 6, Phường Linh Trung, Thành phố Thủ Đức, TP Hồ Chí Minh"));
        infoPanelSeller.add(row3);

        // Hàng 4 - Điện thoại & Số tài khoản chung
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row4.add(new JLabel("Điện thoại:" + chutro.getPhone()));
        //row4.add(new JLabel("0123456789"));
        row4.add(Box.createHorizontalStrut(100)); // thêm khoảng trống
        row4.add(new JLabel("Số tài khoản:"));
        row4.add(new JLabel("314.100.01210304 - Vietcombank"));
        infoPanelSeller.add(row4);
        mainPanel.add(infoPanelSeller);

        // Thông tin người mua
        JPanel infoPanelBuy = new JPanel();
        infoPanelBuy.setLayout(new BoxLayout(infoPanelBuy, BoxLayout.Y_AXIS));
        infoPanelBuy.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        infoPanelBuy.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

        // Hàng 1
        JPanel row_1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row_1.add(new JLabel("Họ tên người mua:" + nguoithuetro.getFullName()));
       // row_1.add(new JLabel("Nguyễn"));
        infoPanelBuy.add(row_1);

        // Hàng 2
        JPanel row_3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row_3.add(new JLabel("Mã số thuế:"));
        row_3.add(new JLabel(nguoithuetro.getCCCD()));
        infoPanelBuy.add(row_3);

        // Hàng 3
        JPanel row_4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row_4.add(new JLabel("Địa chỉ:"));
        row_4.add(new JLabel("Khu Phố 6, Phường Linh Trung, Thành phố Thủ Đức, TP Hồ Chí Minh"));
        infoPanelBuy.add(row_4);

        // Hàng 4 - Điện thoại & Số tài khoản chung
        JPanel row_5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row_5.add(new JLabel("Hình thức thanh toán:"));
        row_5.add(new JLabel("Chuyển khoản"));
        row_5.add(Box.createHorizontalStrut(100)); // thêm khoảng trống
        row_5.add(new JLabel("Số tài khoản:"));
        row_5.add(new JLabel("987654321"));
        row_5.add(Box.createHorizontalStrut(100));
        row_5.add(new JLabel("Đồng tiền thanh toán:"));
        row_5.add(new JLabel("VNĐ"));
        infoPanelBuy.add(row_5);
        mainPanel.add(infoPanelBuy);

        // Tạo bảng hóa đơn
        String[] columnNames = {
                "STT", "Tên hàng hóa, dịch vụ", "Đơn vị tính", "Số lượng", "Đơn giá",
                "Thành tiền chưa có thuế GTGT", "Thuế suất", "Tiền thuế GTGT", "Tổng cộng"
        };


        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane);

        // Populate table with data
        InvoicesController.populateInvoiceTable(table, (Object[]) invoiceDetail1);

        // Tổng cộng
        JPanel totalNoTax = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        totalNoTax.add(new JLabel("Tổng tiền chưa có thuế GTGT: " + ((int) Tongchiphichuathue) + "VNĐ" ) );
        totalNoTax.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        mainPanel.add(totalNoTax);


        JPanel totalTax = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        totalTax.add(new JLabel("Tổng tiền thuế GTGT: " + ((int) Tongtienthue) + " VNĐ"));
        totalTax.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        mainPanel.add(totalTax);

        JPanel totaInvoice = new JPanel();
        totaInvoice.setLayout(new BoxLayout(totaInvoice, BoxLayout.Y_AXIS));
        totaInvoice.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        JPanel row_in_1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        row_in_1.add(new JLabel("Tổng tiền thanh toán: " +( (int) (Tongchiphichuathue + Tongtienthue)) + "VNĐ") );
        totaInvoice.add(row_in_1);

        JPanel row_in_2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row_in_2.add(new JLabel("Số tiền viết bằng chữ:"));
        row_in_2.add(new JLabel("........................................................................................................................................................"));
        totaInvoice.add(row_in_2);
        mainPanel.add(totaInvoice);

        // Người ký
        JPanel signPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        signPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel leftSign = new JPanel(new GridLayout(2, 1));
        leftSign.add(new JLabel("Người mua hàng", SwingConstants.CENTER));
        leftSign.add(new JLabel("(Ký, ghi rõ họ tên)", SwingConstants.CENTER));
        signPanel.add(leftSign);

        JPanel rightSign = new JPanel(new GridLayout(4,1 ));
        rightSign.add(new JLabel("Người bán hàng", SwingConstants.CENTER));
        rightSign.add(new JLabel("(Ký, ghi rõ họ tên)", SwingConstants.CENTER));
        rightSign.add(new JLabel("                  ", SwingConstants.CENTER));
        rightSign.add(new JLabel("(đã ký)", SwingConstants.CENTER));
        signPanel.add(rightSign);

        mainPanel.add(signPanel);

        JPanel invoiceNote = new JPanel(new GridLayout(3, 1));
        invoiceNote.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        invoiceNote.add(new JLabel(""));
        invoiceNote.add(new JLabel(""));
        invoiceNote.add(new JLabel("(Cần kiểm tra, đối chiếu khi lập, nhận hóa đơn)", SwingConstants.LEFT));
        mainPanel.add(invoiceNote);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnInvoice = new JButton("Xuất hóa đơn");
        JButton backInvoice = new JButton("Thoát");
        btnInvoice.addActionListener(e -> {
            InvoicesController.goToSaveDataToHoadon(frame ,invoiceDetail1);

            JOptionPane.showMessageDialog(frame, "Hóa đơn đã được lưu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });
        backInvoice.addActionListener(e -> {
            //thoát frame
            InvoicesController.goToexistInvoiceView(frame);
        });

        footerPanel.add(backInvoice);
        footerPanel.add(btnInvoice);

        // Thêm panel chính vào frame
        frame.add(mainPanel, BorderLayout.CENTER);
        // Thông tin người bán
        //mainPanel.add(createSellerInfoPanel(chutro));

        // Thông tin người mua
        //mainPanel.add(createBuyerInfoPanel(nguoithuetro));

        frame.add(footerPanel, BorderLayout.SOUTH);
        // Hiển thị JFrame
        frame.setVisible(true);
    }



    private JPanel createSellerInfoPanel(Chutro chutro) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Tên người bán: " + chutro.getFullName()));
        //panel.add(new JLabel("Địa chỉ: " + chutro.getAddress()));
        panel.add(new JLabel("Điện thoại: " + chutro.getPhone()));
        return panel;
    }
    private JPanel createBuyerInfoPanel(NguoiThueTro tenant) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Tên người mua: " + tenant.getFullName()));
        panel.add(new JLabel("CCCD: " + tenant.getCCCD()));
        panel.add(new JLabel("Điện thoại: " + tenant.getPhone()));
        return panel;
    }





}
