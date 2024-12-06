package backend;


public class Register {
    public static boolean registerUser(String role, String username, String password, String confirmPassword) {
        // Kiểm tra tính hợp lệ của thông tin
        if (username == null || username.isEmpty()) {
            System.out.println("Tên tài khoản không được để trống.");
            return false;
        }
        if (password == null || password.isEmpty()) {
            System.out.println("Mật khẩu không được để trống.");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            System.out.println("Mật khẩu xác nhận không khớp.");
            return false;
        }

        // Thực hiện logic lưu thông tin người dùng vào cơ sở dữ liệu (giả lập)
        System.out.println("Đang đăng ký người dùng:");
        System.out.println("Vai trò: " + role);
        System.out.println("Tên tài khoản: " + username);

        // Trả về true nếu đăng ký thành công
        return true;
    }
}
