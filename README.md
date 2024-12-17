# Quan_ky_nha_tro_Java
Tạo front end:
Dashboard chủ trọ - Chutrodashboard vỉew
    - view_show_infor_chutro ====> tạm ok giao diện
    - view_create_phong_tro
        + Tạo phòng trọ cần:
        id phòng sẽ được tạo tự động sau khi nhấn tạo phòng
            - Tên phòng
            - Địa chỉ.
            - Giá phòng
            - Giá điện
            - Giá nước
            - Giá rác
            - Lưu phòng
    - view_show_list_rooms
        Muốn show được list room thì phải tạo view của 1 room trước

    - Tạo 1 class room hoạt động riêng biệc, khi gọi sẽ add vào 1 dashboard độc lập
    - roomView -> sẽ là class con của quản lý danh sách phòng trọ
        lấy thông tin từ database của các thông tin về phòng
        Các chức năng:
            + Cập nhật người thuê
            + Cập nhật thông tin phòng
            + Cập nhật hóa đơn
            + Xuất hóa đơn ==> hóa đơn sẽ được thông báo đén dashboard người thuê trọ
            + Xóa phòng
            + Quay lại
        - Trong roomView sẽ có thêm:
            + UpdateInforRoomView, 
            + UpdateRoomerRoomView,
            + UpdateInvoicesRoomView,
            + CreateInvoiceRoomView,
            + DeleteRoomView, ==> chỉ xóa không hiển thị phòng, tạo chỉ mục tồn tại 0 hoặc 1 trong database


        - view_room
            - Các chức năng xử lý trong room
                + Thêm người thuê trọ (thêm bằng CCCD) 
                + Cập nhật thông tin phòng (điện- nước - giá nhà, rác, chi phí khác)
                + Xem hóa đơn phòng
                + Tạo hóa đơn cho phòng
    - show_list_invoices
    