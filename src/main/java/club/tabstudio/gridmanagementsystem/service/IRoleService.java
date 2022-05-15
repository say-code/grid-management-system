package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.Role;

public interface IRoleService {


    int deleteByPrimaryKey(Integer roleId);


    int insert(Role record);


    int insertSelective(Role record);


    Role selectByPrimaryKey(Integer roleId);


    int updateByPrimaryKeySelective(Role record);


    int updateByPrimaryKey(Role record);
}
