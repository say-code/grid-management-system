package club.tabstudio.gridmanagementsystem.service.impl;

import club.tabstudio.gridmanagementsystem.model.UserWithPermissionList;
import club.tabstudio.gridmanagementsystem.model.UserWithRoleList;
import club.tabstudio.gridmanagementsystem.service.IUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import club.tabstudio.gridmanagementsystem.model.User;
import club.tabstudio.gridmanagementsystem.mapper.UserMapper;
/**
 * @author Onion
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public UserWithRoleList selectAllWithRoleListByUsername(String username) {
        return userMapper.selectAllWithRoleListByUsername(username);
    }

    @Override
    public UserWithPermissionList selectWithPermissionListByUsername(String username) {
        return userMapper.selectWithPermissionByUsername(username);
    }

}
