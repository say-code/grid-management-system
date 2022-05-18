/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.Events;
import club.tabstudio.gridmanagementsystem.model.User;
import club.tabstudio.gridmanagementsystem.model.UserEasy;
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
     *  根据eventId查找报事事项
     *
     * @param eventId 报事Id
     * @return 报事事项
     */
    Events selectByPrimaryKey(String eventId);

    /**
     *  插入新的报事事项
     * @param record 报事事项具体信息
     * @return 是否插入成功
     */
    int insertSelective(Events record);

    /**
     * 根据eventId 更新数据
     * @param record 报事事项具体信息
     * @return 是否更新成功
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

    /**
     * 简单查询
     * @param eventsQueryRequest 报事信息
     * @return 返回事件描述和事件建立时间
     */
    List<Events> selectEventDescAndCreatedAtByEventUserId(EventsQueryRequest eventsQueryRequest);

    /**
     * 通过事件Id和网格员Id修改
     * @param updated 报事事项
     * @param eventId 事件Id
     * @param eventAreaAdminId 网格员Id
     * @return 是否修改成功
     */
    int updateByEventIdAndEventAreaAdminId(@Param("updated")Events updated,@Param("eventId")String eventId,@Param("eventAreaAdminId")String eventAreaAdminId);



}
