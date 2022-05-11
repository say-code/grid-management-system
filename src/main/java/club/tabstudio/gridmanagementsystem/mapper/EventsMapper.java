/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.mapper;
import java.util.Date;

import club.tabstudio.gridmanagementsystem.model.Events;import org.apache.ibatis.annotations.Param;import java.util.List;

/**
 * @author wangyihan
 */
public interface EventsMapper {
    int deleteByPrimaryKey(String eventId);


    int insert(Events record);

    /**
     *  插入新的报事事项
     * @param record 报事事项具体信息
     * @return 待研究
     */
    int insertSelective(Events record);

    /**
     *  根据eventId查找报事事项
     *
     * @param eventId 报事Id
     * @return 报事事项
     */
    Events selectByPrimaryKey(String eventId);


    /**
     * 根据eventId 更新数据
     * @param record 报事事项具体信息
     * @return 待研究
     */
    int updateByPrimaryKeySelective(Events record);

    int updateByPrimaryKey(Events record);

    /**
     * 查询所有的报事事项
     *
     * @return 报事事项数组
     */
    List<Events> selectAll();

    /**
     * 根据区域ID查找报事事项
     *
     * @param eventAreaId 网格区域Id
     * @return 报事事项数组
     */
    List<Events> selectAllByEventAreaId(@Param("eventAreaId") String eventAreaId);

    /**
     * 根据网格员姓名查找报事事项
     *
     * @param name 网格员姓名
     * @return 报事事项数组
     */
    List<Events> selectEventByNameWithUsers(@Param("name") String name);

    /**
     * 根据网格员Id来查找报事事项
     *
     * @param eventAreaAdminId 网格员Id
     * @return 报事事项数组
     */
    List<Events> selectAllByEventAreaAdminId(@Param("eventAreaAdminId")String eventAreaAdminId);

    /**
     * 根据年月来查找报事事项
     * @param time 时间
     * @return 报事事项数组
     */
    List<Events> selectByCreatedAt(@Param("time")Date time);






}