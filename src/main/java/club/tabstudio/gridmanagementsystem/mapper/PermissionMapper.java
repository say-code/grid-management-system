package club.tabstudio.gridmanagementsystem.mapper;

import club.tabstudio.gridmanagementsystem.model.Permission;

/**
 * @author Onion
 */
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}