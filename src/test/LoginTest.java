package test;

import org.junit.jupiter.api.Test;

import static backend.Login.checkLogin;
import static org.junit.jupiter.api.Assertions.*;
import backend.Login;
public class LoginTest {

    @Test
    public void testLogin_Success() {
        Login.LoginResult role = checkLogin("testuser", "password123");
        assertEquals(1, role, "Đăng nhập thất bại dù thông tin hợp lệ.");
    }

    @Test
    public void testLogin_Fail_InvalidPassword() {
        Login.LoginResult role = checkLogin("testuser", "wrongpass");
        assertEquals(-1, role, "Đăng nhập thành công dù mật khẩu sai.");
    }

    @Test
    public void testLogin_Fail_InvalidUsername() {
        Login.LoginResult role = checkLogin("nonexistentuser", "password123");
        assertEquals(-1, role, "Đăng nhập thành công dù tài khoản không tồn tại.");
    }
}
