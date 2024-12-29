package test;

import backend.Register;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {

    @Test
    public void testRegisterUser_Success() {
        boolean result = Register.registerUser("chutro", "testuser", "password123", "password123");
        assertTrue(result, "Đăng ký tài khoản thất bại dù thông tin hợp lệ.");
    }

    @Test
    public void testRegisterUser_Fail_PasswordMismatch() {
        boolean result = Register.registerUser("chutro", "testuser", "password123", "wrongpass");
        assertFalse(result, "Đăng ký tài khoản thành công dù mật khẩu không khớp.");
    }

    @Test
    public void testRegisterUser_Fail_EmptyUsername() {
        boolean result = Register.registerUser("chutro", "", "password123", "password123");
        assertFalse(result, "Đăng ký tài khoản thành công dù tên người dùng rỗng.");
    }
}
