/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.Events;
import club.tabstudio.gridmanagementsystem.request.EventsQueryRequest;
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

    /**
     * 使用本接口时请至少携带一个参数！
     * 联合筛选返回所有相关信息
     * 联合查询 - 网格区域名 网格员姓名 报事用户姓名
     * 支持筛选的参数：
     *      eventId 通过事件Id查询
     *      eventAreaId 通过网格区域Id查询
     *      eventAreaAdminId 通过网格员Id查询
     *      eventUserId 通过报事用户Id查询
     *      startTime 通过时间区间查询 必须和endTime联合使用
     *      endTime 通过时间区间查询 必须和startTime联合使用
     * @param events 报事事项筛选参数
     * @return 返回包含 event所有信息 网格区域名 网格员姓名 报事用户姓名的数组
     */
    List<Events> selectWithAllRelation(EventsQueryRequest events);

    /**
     * 根据时间Id删除
     * @param eventId 事件Id
     * @return 1表示删除成功
     */
    int deleteByPrimaryKey(String eventId);



}
