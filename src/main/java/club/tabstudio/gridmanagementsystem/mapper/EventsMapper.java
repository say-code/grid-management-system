/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import club.tabstudio.gridmanagementsystem.model.Events;

/**
 * 报事事项 Mapper层
 * @Author: wangyihan
 */
public interface EventsMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(Events record);

    int insertSelective(Events record);

    Events selectByPrimaryKey(String eventId);

    int updateByPrimaryKeySelective(Events record);

    int updateByPrimaryKey(Events record);

    /**
     * 查询所有的报事事项
     * @return 报事事项数组
     */
    List<Events> selectAll();
}