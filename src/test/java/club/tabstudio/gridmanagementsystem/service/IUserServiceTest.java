package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.UserWithPermissionList;
import club.tabstudio.gridmanagementsystem.model.UserWithRoleList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.util.Password;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IUserServiceTest {

    @Resource
    private IUserService userService;

    @Autowired
    private PasswordEncoder encoder;


    @Test
    public void test() {
//        UserWithPermissionList s = userService.selectWithPermissionListByUsername("root");
//        assertEquals(3, s.getPermissionList().size());
//        System.out.println(s.getName());
        System.out.println(encoder.encode("user"));
    }
}