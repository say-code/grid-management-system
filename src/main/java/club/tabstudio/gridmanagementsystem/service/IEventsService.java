
/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.Events;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 报事事项 Service接口层
 * @author wangyihan
 */
public interface IEventsService {

    /**
     * 查询所有事件
     * @return 事件数组
     */
    List<Events> selectAllEvents();

    /**
     * 修改事件
     * @param events 事件
     */
    void editEvents(Events events);


    /**
     * 通过区域Id查找事件
     * @param eventAreaId 网格区域Id
     * @return 事件数组
     */
    List<Events> selectByEventAreaId(String eventAreaId);

    /**
     * 根据网格员姓名查找报事事项
     *
     * @param name 网格员姓名
     * @return 报事事项数组
     */
    List<Events> selectEventByName(String name);


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
    List<Events> selectByTime(@Param("time") Date time);

    /**
     *  根据eventId查找报事事项
     *
     * @param eventId 报事Id
     * @return 报事事项
     */
    Events selectByPrimaryKey(String eventId);

    /**
     *  插入新的报事事项
     * @param record 报事事项具体信息
     * @return 待研究
     */
    int insertSelective(Events record);

    /**
     * 根据eventId 更新数据
     * @param record 报事事项具体信息
     * @return 待研究
     */
    int updateByPrimaryKeySelective(Events record);

}
