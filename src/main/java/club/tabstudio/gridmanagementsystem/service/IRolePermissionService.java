package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.RolePermission;

public interface IRolePermissionService {

    int deleteByPrimaryKey(Integer roleId,Integer permissionId);


    int insert(RolePermission record);


    int insertSelective(RolePermission record);
}
