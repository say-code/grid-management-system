package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.User;
import club.tabstudio.gridmanagementsystem.model.UserWithPermissionList;
import club.tabstudio.gridmanagementsystem.model.UserWithRoleList;

public interface IUserService {
    int deleteByPrimaryKey(String userId);


    int insert(User record);


    int insertSelective(User record);


    User selectByPrimaryKey(String userId);


    int updateByPrimaryKeySelective(User record);


    int updateByPrimaryKey(User record);


    UserWithRoleList selectAllWithRoleListByUsername(String userId);

    /**
     * 根据用户名查询指定用户
     * @param username 用户名
     * @return 用户关联权限
     */
    UserWithPermissionList selectWithPermissionListByUsername(String username);
}
