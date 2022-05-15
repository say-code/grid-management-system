package club.tabstudio.gridmanagementsystem.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import club.tabstudio.gridmanagementsystem.mapper.UserRoleMapper;
import club.tabstudio.gridmanagementsystem.model.UserRole;
/**
 * @author Onion
 */
@Service
public class UserRoleServiceImpl {

    @Resource
    private UserRoleMapper userRoleMapper;

    
    public int deleteByPrimaryKey(String userId,String roleId) {
        return userRoleMapper.deleteByPrimaryKey(userId,roleId);
    }

    
    public int insert(UserRole record) {
        return userRoleMapper.insert(record);
    }

    
    public int insertSelective(UserRole record) {
        return userRoleMapper.insertSelective(record);
    }

}
