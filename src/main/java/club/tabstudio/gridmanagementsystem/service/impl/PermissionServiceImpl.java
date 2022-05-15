package club.tabstudio.gridmanagementsystem.service.impl;

import club.tabstudio.gridmanagementsystem.service.IPermissionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import club.tabstudio.gridmanagementsystem.model.Permission;
import club.tabstudio.gridmanagementsystem.mapper.PermissionMapper;
/**
 * @author Onion
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    
    public int deleteByPrimaryKey(Integer permissionId) {
        return permissionMapper.deleteByPrimaryKey(permissionId);
    }

    
    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    
    public int insertSelective(Permission record) {
        return permissionMapper.insertSelective(record);
    }

    
    public Permission selectByPrimaryKey(Integer permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }

    
    public int updateByPrimaryKeySelective(Permission record) {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

}
