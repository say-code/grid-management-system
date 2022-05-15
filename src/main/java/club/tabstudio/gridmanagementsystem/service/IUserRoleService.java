package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.UserRole;

public interface IUserRoleService {

    int deleteByPrimaryKey(String userId,String roleId);


    int insert(UserRole record);


    int insertSelective(UserRole record);
}
