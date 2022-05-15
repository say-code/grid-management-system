package club.tabstudio.gridmanagementsystem.mapper;

import club.tabstudio.gridmanagementsystem.model.User;
import club.tabstudio.gridmanagementsystem.model.UserWithPermissionList;
import club.tabstudio.gridmanagementsystem.model.UserWithRoleList;

import java.util.List;


/**
 * @author Onion
 */
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    UserWithRoleList selectAllWithRoleListByUsername(String username);

    UserWithPermissionList selectWithPermissionByUsername(String username);
}