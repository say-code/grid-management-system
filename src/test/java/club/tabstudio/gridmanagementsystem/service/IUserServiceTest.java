package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.UserWithPermissionList;
import club.tabstudio.gridmanagementsystem.model.UserWithRoleList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IUserServiceTest {

    @Resource
    private IUserService userService;


    @Test
    public void test() {
        UserWithPermissionList s = userService.selectWithPermissionListByUsername("root");
        assertEquals(3, s.getPermissionList().size());
        System.out.println(s.getName());
    }
}