package backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Login {
    public static int check_login(String username, String pasword){
        // check login
        System.out.println(" login chưa được thiết lập");
        // sau kiểm tra login
        // nếu user thuộc role chủ trọ trả về tham so = 1
        // nếu user thuộc role người thuê trọ trả về tham so = 2
        // nếu user thuộc admin trả về tham so = 0
        int check_login = 1 ;
        return check_login;
    }




}
