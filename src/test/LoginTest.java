package test;

import org.junit.jupiter.api.Test;

import static backend.Login.checkLogin;
import static org.junit.jupiter.api.Assertions.*;
import backend.Login;
public class LoginTest {

//    @Test
//    public void testLogin_Success() {
//        Login.LoginResult result = checkLogin("admin_1", "pass123");
//        assertNotNull(result, "Đăng nhập thành công.");
//    }
//
//    @Test
//    public void testLogin_Fail_InvalidPassword() {
//        Login.LoginResult role = checkLogin("testuser", "wrongpass");
//        assertEquals(-1, role, "Đăng nhập thành công dù mật khẩu sai.");
//    }
//
//    @Test
//    public void testLogin_Fail_InvalidUsername() {
//        Login.LoginResult role = checkLogin("nonexistentuser", "password123");
//        assertEquals(-1, role, "Đăng nhập thành công dù tài khoản không tồn tại.");
//    }

    @Test
    public void testValidLogin() {
        String username = "admin_1";
        String password = "pass123";

        Login.LoginResult loginResult = Login.checkLogin(username, password);

        // Assert that the login was successful
        assertNotNull(loginResult);
        assertEquals("1", loginResult.getUserId());
        assertEquals("admin", loginResult.getRole()); // Replace "USER_ROLE" with the expected role for valid credentials
    }

    @Test
    public void testInvalidUsername() {
        String username = "invalid_username";
        String password = "pass123";

        Login.LoginResult loginResult = Login.checkLogin(username, password);

        // Assert that the login failed
        assertNull(loginResult);
    }

    @Test
    public void testInvalidPassword() {
        String username = "admin_1";
        String password = "invalid_password";

        Login.LoginResult loginResult = Login.checkLogin(username, password);

        // Assert that the login failed
        assertNull(loginResult);
    }
}
