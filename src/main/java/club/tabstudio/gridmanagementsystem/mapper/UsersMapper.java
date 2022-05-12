/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.mapper;

import club.tabstudio.gridmanagementsystem.model.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(String userId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}