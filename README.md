# Quan_ky_nha_tro_Java
# Hiệu chỉnh cấu trúc file

src/
├── backend/                   # Lớp xử lý dữ liệu và cơ sở dữ liệu
│   ├── model/                 # Các lớp mô hình dữ liệu
│   │   ├── NguoiThueTro.java               đã có
│   │   ├── Rooms.java                      đã có
│   │   ├── Chutro.java                     đã có
│   │   └── Admin.java              
│   │   ├── Invoices.java       
│   │   ├── InvoicesDetail.java
│   │   └── User.java
│   ├── connectDatabase.java            # Lớp kết nối cơ sở dữ liệu
│   └── utilities/                      # Các tiện ích backend
│       ├── DatabaseHelper.java
│       └── ValidationHelper.java
├── controller/                         # Xử lý logic và kết nối giữa Model và View
│   ├── LoginController.java                đã có
│   ├── RegisterController.java             đã có
│   ├── RoomController.java                 đã có
│   ├── DashboardChutroController.java      đã có
│   ├── AdminController.java                đã có
│   └── NguoiThueTroController.java         đã có
├── frontend/                           # Lớp hiển thị giao diện (View)
│   ├── view/                           # Các giao diện cụ thể
            
│   │   ├── adminDashboard.java ==> Show View Admin Dashboard
│   │   ├── ChutroCreateRoomView.java ==> Show view tạo phòng trọ mới
│   │   ├── ChutroDashboardView.java ==> Show View chủ trọ Dashboard
                Gồm các chức năng:
                    + Xem thông tin chủ trọ (1)
                    + Tạo phòng trọ
                    + Xem danh sách phòng trọ   
                    + Xem danh sách hóa đơn
                    + Đăng xuất khỏi sever trái đất
│   │   ├── ChutoInforView.java ==> hiển thị chức năng xem thông tin chủ trọ (1)
│   │   ├── ChutroRoomTableView.java ==> Xem danh sách phòng ==> nơi kết nối với chi tiết phòng
                    + ID phòng (hoàn thiện backend sẽ bỏ)
                    + Tên phòng 
                    + Người thuê 
                    + Xem chi tiết phòng ==> Nơi cập nhật, tạo các hóa đơn liên quan
│   │   ├── homeloginView.java ==> View đăng nhập/ đăng kí tài khoản
                    + Đăng nhập ==> đã tạo view(2)
                    + Đăng ký ==> đã tạo view liên kết ==> test dữ liệu vào CSDL OK
│   │   └── InvoiceDetailUpdateView.java ==>Cập nhật chi tiết hóa đơn (6)
                    + Thông tin phòng
                    + Số điện cũ / nước cũ 
                    + Số điện mới / nước mới
                    + Ngày thu tiền nhà tháng trước
                    + Ngày xuất hóa đơn
                    + Giảm giá
                    + Cập nhật
│   │   └── loginView.java
                    + Giao diện đăng nhập (2)
│   │   └── nguoiThueTroDashboardView.java ==> hiển thị Dashboard người thuê
                    + chưa tạo khỉ khô gì
│   │   └── RegisterView.java ==> Hiển thị giao diện đăng kí tài khoản
                    + Vai trò ( chủ trọ / người thuê trọ)
                    + Đăng kí tài khoản mật khấu ==> cần hàm check với database
                    + Nút Đăng kí , quay lại
│   │   └── RoomBaseUpdateView.java  ==> abstract class câp nhật giá trị mới (giá điện/ nước)
                    + Nút cập nhật (3)
                    + Nút quay lại
│   │   └── RoomUpdateInforRoomView.java (5)
                    + Tên phòng
                    + Tên người thuê
                    + Giá thuê phòng ==> Nút cập nhật (3)
                    + Giá điện ==> Nút cập nhât (3)
                    + Giá nước ==> Nút cập nhât (3)
                    + Giá rác ==> Nút cập nhât (3)
                    + Nút quay lại
│   │   └── RoomUpdateNguoithueView.java ==> Cập nhât người thuê vào phòng trọ (4)
                    + Nhập mã số CCCD
                    + Nút xác nhận
                    + Nút quay lại
│   │   └── RoomUpdatePriceView.java
                    + Cập nhật giá cho (3)
│   │   └── RoomView.java ==> có thể gọi là Dashboard phòng
                    + Cập nhật người thuê (4) ==> đã hiệu chỉnh
                    + Cập nhật thông tin phòng (5)==> đã hiệu chỉnh tạo liên kết
                    + Cập nhật hóa đơn (6) ==> đã tạo liên kết
                    + Xuất hóa đơn ==> chưa tạo
                    + Xóa phòng   ==> chưa tạo  
                    + NÚt quay lại ==> tạo liên kết sai
│   │   └── AdminDashboardView.java ==> View giao diện dashboard Admin
                    + 


│   ├── components/            # Các thành phần giao diện dùng lại
│   │   ├── CustomButton.java
│   │   └── CustomPanel.java
│   └── styles/                # Các định dạng giao diện
│       ├── Fonts.java
│       └── Colors.java
├── Main.java                  # Điểm bắt đầu của chương trình
├── resources/                 # Tài nguyên tĩnh
│   ├── images/                # Hình ảnh
│   ├── fonts/                 # Font chữ tùy chỉnh
│   └── configs/               # File cấu hình, SQL, hoặc JSON
│       ├── database_config.json
│       └── roles_config.json
└── tests/                     # Thư mục kiểm thử
├── backend/               # Kiểm thử logic backend
│   ├── TestNguoiThueTro.java
│   └── TestRooms.java
├── controller/            # Kiểm thử logic controller
│   ├── TestRoomController.java
│   └── TestLoginController.java
└── frontend/              # Kiểm thử giao diện
├── TestLoginView.java
└── TestRoomView.java



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
    - ChutroRoomsTableView 
        Muốn show được list room thì phải tạo view của 1 room trước
        ==> Khi bấm vào sẽ hiển thị danh sách phòng:


RoomView
    - Tạo 1 class room hoạt động riêng biệc, khi gọi sẽ add vào 1 dashboard độc lập
    - roomView -> sẽ là class con của quản lý danh sách phòng trọ
       * class : RoomView
        lấy thông tin từ database của các thông tin về phòng
        Các chức năng:
            + Cập nhật người thuê:
                * class: RoomUpdateNguoiThueView ==> cập nhật người thuê trọ bằng CCCD
                * Đã liên kết với class: UpdateNguoiThueInRoom trong backend.NguoiThueTro
            + Cập nhật thông tin phòng: class: UpdateInforRoomView
                + Thông tin phòng gồm: UpdateRoomInfo , GoToUpdateInfor
                    - Cập nhật số điện GetNumberElectricAndWater, 
                    - Cập nhật số nước GetNumberElectricAndWater, 
            # phát sinh lỗi khi mở cửa sổ cập nhật số điện số nước ==> mỏ lại cửa sổ RoomView

                * class: RoomUpdateInforRoomView ==> Cập nhật thông tin phòng
                *  
            
            + Cập nhật thông tin phòng
            + Cập nhật hóa đơn
            + Xuất hóa đơn ==> hóa đơn sẽ được thông báo đén dashboard người thuê trọ
            + Xóa phòng
            + Quay lại
        - Trong roomView sẽ có thêm:
            + UpdateInforRoomView (id_room)
                - vãn cho phép cập nhật thông tin phòng khi không có người thuê
            + UpdateRoomerRoomView,
            + UpdateInvoicesRoomView,
            + CreateInvoiceRoomView,
                - room không có id_nguoithue sẽ không cho phép xuất hóa đơn
            + DeleteRoomView, ==> chỉ xóa không hiển thị phòng, tạo chỉ mục tồn tại 0 hoặc 1 trong database


        - view_room
            - Các chức năng xử lý trong room
                + Thêm người thuê trọ (thêm bằng CCCD) 
                + Cập nhật thông tin phòng (điện- nước - giá nhà, rác, chi phí khác)
                + Xem hóa đơn phòng
                + Tạo hóa đơn cho phòng
    - show_list_invoices.


    28/12 BigUpdate
        - ChutroCreateRoomsView




    |____ Test các chức năng
        - Đăng ký: homelogin->Logincontroller->RegisterView->RegisterController-<Rigister
            ==> Tạo tài khoản chủ trọ => data đã đưa về CSDL => Đang xử lý  
        - Đăng nhập: Đã đăng nhập được
        - 

    |_____ 



















    ;
    - Xây dựng CSDL:
    Create table Users (
	UserID INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(50),
    Password NVARCHAR(255),
	Role NVARCHAR(12)
	CONSTRAINT chk_role CHECK (Role IN ('admin', 'chutro', 'nguoithuetro'))
);
    CREATE TABLE Admins (
    AdminID INT PRIMARY KEY IDENTITY(1,1),
    FullName NVARCHAR(255),
    Username NVARCHAR(50),
    Password NVARCHAR(255)
    );
    
    CREATE TABLE Chutro (
    IDChutro INT PRIMARY KEY IDENTITY(1,1),
    HoTen NVARCHAR(255),
    CCCD NVARCHAR(12) UNIQUE,
    Phone NVARCHAR(15)
    );
    ALter table Chutro add UserID INT;
    ALTER TABLE Chutro ADD CONSTRAINT FK_Chutro_UserID
    FOREIGN KEY (UserID) REFERENCES Users(UserID);

    CREATE TABLE TTPhongtro (
    IDPhong INT PRIMARY KEY IDENTITY(1,1),
    TenPhong NVARCHAR(100),
    Address NVARCHAR(255),
    Giadien DECIMAL(10,2),
    GIanuoc DECIMAL(10,2),
    GiaPhong DECIMAL(10,2),
    Sodienhientai INT,
    Sonuochientai INT
);
    ALter table TTPhongtro add Tinhtrang NVARCHAR(50);
    ALter table TTPhongtro add IDChutro INT;
    ALTER TABLE TTPhongtro ADD CONSTRAINT FK_TTPhongtro_IDChutro
    FOREIGN KEY (IDChutro) REFERENCES Chutro(IDChutro);
    
    CREATE TABLE NguoiThueTro (
    IDnguoithue INT PRIMARY KEY IDENTITY(1,1), -- IDnguoithue
    Hoten NVARCHAR(255),
    CCCD NVARCHAR(12) UNIQUE,
    Phone NVARCHAR(15),
    Ngaybatdauthue DATE,
    Ngayketthucthue DATE
);
    ALter table NguoiThueTro add UserID INT;
    ALTER TABLE NguoiThueTro ADD CONSTRAINT FK_NguoiThueTro_UserID
    FOREIGN KEY (UserID) REFERENCES Users(UserID);
    
    - Tạo bảng hóa đơn
    CREATE TABLE HoaDon (
    BillID INT PRIMARY KEY IDENTITY(1,1), -- Mã hóa đơn tự động tăng
    Tiennha DECIMAL(10,2),               -- Phí thuê nhà hàng tháng
    Tiendien DECIMAL(10,2),        -- Phí điện
    Tiennuoc DECIMAL(10,2),              -- Phí nước
    Tienrac DECIMAL(10,2),              -- Phí rác
    Chiphikhac DECIMAL(10,2),             -- Các phí khác
    Giamgia DECIMAL(10,2),              -- Giảm giá
    Tongchiphi DECIMAL(10,2),           -- Tổng số tiền phải trả
    Ngayxuathoadon DATE                        -- Ngày xuất hóa đơn
    );
    -- Them khoa ngoai idPhong cho Bills
    alter table HoaDon add IDPhong INT;
    
    ALTER TABLE HoaDon ADD CONSTRAINT FK_HoaDon_IDPhong
    FOREIGN KEY (IDPhong) REFERENCES TTPhongtro(IDPhong);
    
    alter table HoaDon add idCTHD INT;


    CREATE TABLE CTHoadon (
    idCTHD INT PRIMARY KEY IDENTITY(1,1),
    BillID INT FOREIGN KEY REFERENCES HoaDon(BillID),
    SodienUsed INT, -- Số điện tiêu thụ = số hiện tại - số trước
    SonuocUsed INT,       -- Số nước tiêu thụ = số hiện tại - số trước
    DaysInMonth INT,     -- Số ngày ở trong tháng
    Tiennha DECIMAL(10,2), -- Tiền nhà = giá phòng / 30 * số ngày
    Ngaythutienthangtruoc DATE, -- Ngày thu tiền tháng trước
    Ngaykiemtrasodiennuoc DATE,     -- Ngày kiểm tra số điện nước
    Chiphiphatsinh DECIMAL(10,2),  -- Chi phí phát sinh
    Tienrac DECIMAL(10,2),
    Giamgia DECIMAL(10,2)
    );
    


#### Error
AdminController.loadInactiveUsers(inactiveUsersTable); ==> lỗi truy xuất dữ liệu từ database==> OK
Kiểm tra loadInactiveUsers(JTable table) ==> do sai tên is_active với database
AdminDashboardView ==> Đã xử lý set is_active vào database ==> ok
login ==> chưa đăng nhập được ==> không tìm thấy chủ trọ
loginView ==> LoginController.checkLogin(username, password.toCharArray(), f); ==>

- Lỗi logic Hiếu ư:
  - Sau khi tạo tài khoản xong ==> Chuyển sang đăng nhập
  - Check: RegisterView -> RegisterController.handleRegister -> new UpdateChutroInforView(username) 
  -> RegisterController.updateChutroInfo(fullName, cccd, phone, username, frame) 
===> fix thêm new loginView() vào updateChutroInfo;

TODO: - Lỗi: check trùng CCCD và Số điện thoại khi tạo tài khoản 

- Lỗi: Khi bấm đăng nhập login không tắt view đăng nhập
- Check: loginView -> LoginController.checkLogin(username, password, f) -> đã xử lý

TODO: - Xem thông tin chủ trọ chưa có View
Check: ChutroDashboardView- > DashboardChutroController.go_to_show_information_churtro(entryUsername)

Lỗi:
btnUpdateRoom -> RoomController.updateInforRoom(frame, idRoom, idChutro) ==> chưa hoạt động do chưa có phòng nào

ERROR TODO: Tạo người thuê trọ không dẫn đến View nhập thông tin
--> Cần dẫn đến View nhập thông tin trước khi gửi database lên SQL Sever
Check: RegisterView --> RegisterController --> handleRegister --
Check: String userID = Register.getUserIDFromUsers(username);
    Hiếu # TODO: - Hiếu chỉnh lại phương thức   
        + Register.updateChutroInfo ==> thành update được cho cả người thuê lẫn chủ trọ
    + Giải thích: Khi tạo tài khoản ==> cập nhật thông tin ở (UpdateChutroInforView) --> tạo xong hết đổi tên class cho clean

Fix case "nguoithuetro" --> new UpdateChutroInforView(username) --> RegisterController.updateChutroInfo(fullName, cccd, phone, username, frame);
--> 

ERROR TODO: Lõi View cập nhật thông tin phòng
+ RoomController.updateInforRoom(frame, idRoom, idChutro) -> new RoomUpdateInforRoomView(idPhong)
+ Check: Room.getRoomDetails(roomId)
  + Còn 4 nút cập nhật chưa có API
+ 
# STRIKE TODO: - Khi bấm đăng nhập login không tắt view đăng nhập ==> đã xử lý
# Phúc STRIKE TODO: - Chức năng xem thông tin chủ trọ chưa hoạt động ==> đã xử lý
# Hiếu STRIKE TODO: - Lỗi: check trùng CCCD và Số điện thoại khi tạo tài khoản  ==> đã xử lý
# Hiếu STRIKE TODO: - Chưa đăng nhập admin được => đã xử lý
# STRIKE TODO: - Xem thông tin chủ trọ chưa có View ==> đã xử lý
# ERROR STRIKE TODO: Vị trí quay lại trong RoomView không trả về Dashboard Chủ trọ => đã xử lý
# ERROR STRIKE TODO: Tạo người thuê trọ không dẫn đến View nhập thông tin
--> Cần dẫn đến View nhập thông tin trước khi gửi database lên SQL Sever => đã xử lý
# STRIKE TODO: - Chutro.getUsernameFromIdChutro ==> Trích tên username từ id_chủ trọ để back to Dashboard chủ trọ => đã xử lý
# Hiếu # STRIKE TODO: - Hiếu chỉnh lại phương thức ( lỗi khi tạo tài khoản người thuê trọ thiếu cập nhật thông tin)
    + Register.updateChutroInfo ==> thành update được cho cả người thuê lẫn chủ trọ
    + Giải thích: Khi tạo tài khoản ==> cập nhật thông tin ở (UpdateChutroInforView)
        --> tạo xong hết đổi tên class cho cleanca => đã xử lý

Phúc TODO: View Chủ trọ dashboardView -> Xem thông tin chủ trọ ->
DashboardChutroController.go_to_show_information_churtro(id_chutro) ->

# Phúc TODO: View Chủ trọ dashboardView -> Xem thông tin chủ trọ ==> đã thực hiện
+ Các View cập nhật chưa thực hiện ==> đã thực hiện
+ Cập nhật họ tên ==> đã thực hiện
+ Cập nhật SDT==> đã thực hiện
+ Đổi mật khẩu==> đã thực hiện
# Phúc TODO: View xóa phòng chưa có --> đã có
# Phúc TODO: - View Xem dánh sách hóa đơn chưa tạo ==> đã tạo
# Phúc TODO: liên kết mở và return xem danh sách phòng trọ lỗi -- Đã xử lý
# Hiếu ERROR TODO: Kiểm tra lại regiester user ==? => Anh Phúc kiểm tra lại nha, em thấy bên em bình thường ==> OK
# * Phúc TODO: Tạo view cho các:
        + Admin View Danh sách chủ trọ == đã xử lý 
        +
        + 
        +
Phúc : Lỗi truy xuất dữ liểu ra
-> Room.getEmptyRoomsForTenant(userId); -> đã xử lý


Kiểm tra Xem danh sách hóa đơn trong quyền chủ trọ
==> DashboardChutroController.go_to_show_list_invoices(id_chutro)
==> InvoiceListsView(idChutro, nameChutro, roomCount)
// Lỗi chưa xu lkys


Lỗi - Các mục chưa làm
Hiếu TODO: - XỬ lý chức năng Delete trong Admin Dashboard luôn nha, delete user
Hiếu TODO: - xử lý chức năng tạm ngưng hoạt động chủ trọ ở AdminShowAllChutroView.
+ buttonSetVisibleFalse

Hiếu TODO: - Chức năng tạo phòng trọ ==> chưa lưu được phòng tro khi tạo
Hiếu TODO: - Chức năng Xem danh sách phòng ==> chưa hoạt động do Chức năng tạo phòng trọ chưa có
Hiếu TODO; - Một tá def trong Room.java chưa được viết hàm
Hiếu TODO: - Tạo Xử lý đổi mật khẩu cho chủ trọ và người thuê trọ (hiển thị trong ChutroInformationView)
=> Đợi anh Phúc tạo windows để cập
==> đã có View đổi mật khẩu cho chủ trọ
go_to_show_information_churtro(id_chutro) -> ChutroInformationView(id_chutro, chutro)
->ChutroInformationView -> UpdatePasswordView(idChutro)
Phúc + Hiếu: TODO: Tạo View cho Xuất hóa đơn cần lắm các phương thức truy xuất dữ liệu
+View đã có => chờ backend
Hiếu TODO: - Xử lý truy vấn + logic các thông tin liên quan đến hóa đơn, Xuất hóa đơn
ERROR TODO: Lõi View cập nhật thông tin phòng
+ RoomController.updateInforRoom(frame, idRoom, idChutro)
Hiếu ERROR TODO: updateRoomPrice(String roomId, double newValue, String type) chưa được xử lý
==> RoomUpdateInforRoomView -> RoomUpdatePriceView -->updateButton --> updateRoomPrice-->
--> RoomController.updateRoomPrice --> 4 nút cập nhật
Phúc TODO: View Chủ trọ dashboardView -> Xem thông tin chủ trọ ==> đã thực hiện
+ Khi bấm cập nhật không reset tên tại thời điểm bấm ==> chưa màn được
Hiếu TODO: Mở View Dashboard chủ trọ -> Xem danh sách phòng => Xem chi tiết phòng ==> Bổ sung các chức năng cập nhật hóa đơn
==> Xuất hóa đơn ==> View đã có
Hiếu TODO: Tạo xử lý cho Room.deleteRoom( String idRoom) nha
* Phúc TODO: Tạo view cho các:
  + Admin View Danh sách phòng trọ
  + Admin View Danh sách người thuê trọ
  + Admin View Danh sách hóa đơn trọ
  Hiếu   + Tạo nút delete cho những tài khoản không được active ==> đã xong chờ Hiếu thêm backend
* Phúc TODO: Tạo view cho Dashboard người thuê trọ
  Hiếu TODO: Room ==> cập nhật người thuê phòng ==> chưa cập nhật được người thuê
  + Room ==> goToUpdateNguoiThue ==> RoomUpdateNguoithueView(idPhong)
  + ==> GoToUpdateNguoiThue(CCCDValue.trim(),frame,id_room) ==
  + GoToUpdateNguoiThue ?? ==> updateNguoiThueTroInRoom
  + getIdNguoiThueFromCCCD ??
  + //TODO: Hiếu tạo truy vấn cho update thông tin người thuê vào phòng trọ lên database
  Hiếu TODO: Đưa phương thức checkLogin vào Login.class ==> Thêm check user đã được kích hoạt chưa
  + ==> nếu chưa thông báo user chưa được kích hoạt
  Hiếu TODO: TODO: Invoice list view đã tạo, Hiếu kiểm tra lại logic xem truy xuất dữ liệu cho Invoices.InvoiceListsView  nha

Hiếu TODO: Thực hiện thao tác delete User trong table User: Admincontroller.deleteUser
------
InvoiceFormView cần
Hiếu: TODO: Tạo 1 hàm chạy số cho ký hiệu hóa đơn
TODO: Tạo 1 hàm chạy số cho hóa đơn
HIếu: TODO: Tạo đối tượng cho Invoice và InvoiceDetail
Hiếu: TODO: Khi bấm thêm người thuê trọ vào phòng ==> Id người thuê trọ không được cập nhật vào Phòng
==> Check CSDL TTPHongTro ==> Id người thuê không được thêm
==> Truy update TTPhongTro thiếu
Hiếu: TODO getIdNguoiThueFromCCCD chưa có backend

TODO: Không thêm người thuê phòng vaào phòng được vì cập nhật không được id người thuê
==> không phải lỗi do update người thuê
==> đang kiểm tra khi bấm vào xem chi tiết cảu danh sách phòng trọ có lấy được id phòng kẹp theo không?


Hiếu TODO:  RoomController.goToRoomInforView(roomId);
// Điều hướng đến RoomInforView với roomId ==> lõi
===> Room.getRoomInForViewbyRoomId(roomId);
==> fix lại lấy idroom ở vị trí cột ẩn ==> cập nhật database vẫn trông
===> hiếu xem lại logic giúp
TODO: CSDL lúc nảy có fix chổ hóa đơn ==> thêm cột có thanh toán chưa nhớ giữ lại
// 11/01/2024
// TODO: Warning Hiếu lưu ý bên dưới:




Phúc fixx


// TODO: cần fix InvoiceButtonEditor_1
: openInvoiceDetails
updateButton.addActionListener
    +
InvoiceDetail currentDetail = InvoiceDetail.getInvoiceDetail(id_room);
