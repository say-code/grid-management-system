package club.tabstudio.gridmanagementsystem.mapper;

import club.tabstudio.gridmanagementsystem.model.Events;import club.tabstudio.gridmanagementsystem.request.EventsQueryRequest;import org.apache.ibatis.annotations.Param;import java.util.Date;import java.util.List;

public interface EventsMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(Events record);

    int insertSelective(Events record);

    Events selectByPrimaryKey(String eventId);

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
    List<Events> selectAllByEventAreaAdminId(@Param("eventAreaAdminId") String eventAreaAdminId);

    /**
     * 根据年月来查找报事事项
     *
     * @param time 时间
     * @return 报事事项数组
     */
    List<Events> selectByCreatedAt(@Param("time") Date time);

    /**
     * 联合筛选返回所有相关信息
     * 联合查询 - 网格区域名 网格员姓名 报事用户姓名
     * 支持筛选的参数：
     * eventId 通过事件Id查询
     * eventAreaId 通过网格区域Id查询
     * eventAreaAdminId 通过网格员Id查询
     * eventUserId 通过报事用户Id查询
     * startTime 通过时间区间查询 必须和endTime联合使用
     * endTime 通过时间区间查询 必须和startTime联合使用
     *
     * @param events 报事事项筛选参数
     * @return 返回包含 event所有信息 网格区域名 网格员姓名 报事用户姓名的数组
     */
//    List<EventsQueryRequest> selectAllWithOthersSelective(EventsQueryRequest events);

    List<Events> selectWithAllRelation(EventsQueryRequest events);
}