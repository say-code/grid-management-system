package club.tabstudio.gridmanagementsystem.service.impl;

import club.tabstudio.gridmanagementsystem.service.IRolePermissionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import club.tabstudio.gridmanagementsystem.mapper.RolePermissionMapper;
import club.tabstudio.gridmanagementsystem.model.RolePermission;
/**
 * @author Onion
 */
@Service
public class RolePermissionServiceImpl implements IRolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int deleteByPrimaryKey(Integer roleId,Integer permissionId) {
        return rolePermissionMapper.deleteByPrimaryKey(roleId,permissionId);
    }

    
    public int insert(RolePermission record) {
        return rolePermissionMapper.insert(record);
    }

    
    public int insertSelective(RolePermission record) {
        return rolePermissionMapper.insertSelective(record);
    }

}
