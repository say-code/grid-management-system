
package club.tabstudio.gridmanagementsystem.controller;

import club.tabstudio.gridmanagementsystem.model.Events;
import club.tabstudio.gridmanagementsystem.model.Response;
import club.tabstudio.gridmanagementsystem.model.UserWithPermissionList;
import club.tabstudio.gridmanagementsystem.request.EventsQueryRequest;
import club.tabstudio.gridmanagementsystem.service.IEventsService;
import club.tabstudio.gridmanagementsystem.validation.groups.CreateGroup;
import club.tabstudio.gridmanagementsystem.validation.groups.DeleteGroup;
import club.tabstudio.gridmanagementsystem.validation.groups.EditGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.UUID;

/**
 * 报事事项 控制层
 * @author wangyihan
 */
@Slf4j
@RestController
@RequestMapping("events")
public class EventsController {

    @Autowired
    private IEventsService eventsService;

    /* -------- insert 插入模块 --------*/

    /**
     * 插入新的报事事项
     * 权限：网格长、网格员、用户
     * createdAt 参数自动创建，无需传入
     * @param events 报事事项
     * @return Response响应状态
     */
    @PostMapping("insert")
    @PreAuthorize("hasAuthority('event:create')")
    public Response insert(@Validated({CreateGroup.class}) @RequestBody Events events){
        events.setEventStatus(0);
        events.setEventId(UUID.randomUUID().toString());
        if (eventsService.insertSelective(events) > 0) {
            return Response.success();
        }
        return Response.error();
    }

    /* -------- edit 修改模块 --------*/

    /**
     * 修改报事事项
     * 权限：网格长
     * @param events 事项信息 属性：
     *               String eventId 事件Id 【必要】
     *               String eventAreaAdminId 网格员Id
     *               String eventAreaAdminFeedback 网格员反馈
     *               String eventStatus 事件状态
     * @return Response响应状态
     */
    @PostMapping("edit")
    @PreAuthorize("hasAuthority('event:edit')")
    public Response editEvents(@Validated({EditGroup.class}) @RequestBody Events events) {
        Integer eventStatus = events.getEventStatus();
        if (eventStatus == 1){
            return Response.success(eventsService.updateByPrimaryKeySelective(events));
        }
        else if(eventStatus == 2 || eventStatus == 3){
            Date date = new Date(System.currentTimeMillis());
            events.setCompletedAt(date);
            return Response.success(eventsService.updateByPrimaryKeySelective(events));
        }
        else {
            return Response.error("eventStatus值应在1-3之间！");
        }
    }

    /**
     * 管理员查看报事时触发
     * 权限：网格员
     *
     * @param events 事项信息
     *               String eventId 事件Id 【必要】
     *               int eventStatus 0 【必要】
     *               其他参数将被忽略！
     * @return Response响应状态
     */
    @PostMapping("eventsAccept")
    @PreAuthorize("hasAuthority('event:edit:admin')")
    public Response eventsAcceptForAdmin(@Validated({EditGroup.class}) @RequestBody Events events) {
        Integer eventStatus = events.getEventStatus();
        if (eventStatus == 1){
            return Response.error("该事项已被接收！");
        }
        else if (eventStatus != 0){
            return Response.error("该事项已结束！");
        }
        String adminId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Events eventsNull = new Events();
        eventsNull.setEventStatus(1);
        return Response.success(eventsService.updateByEventIdAndEventAreaAdminId(eventsNull,events.getEventId(), adminId));
    }

    /**
     * 修改报事事项
     * 权限：网格员
     * @param events 事项信息
     *               String eventId 事件Id 【必要】
     *               String eventAreaAdminFeedback 网格员反馈
     *               String eventStatus 事件状态
     *               其他参数将被忽略！
     * @return Response响应状态
     */
    @PostMapping("editForAdmin")
    @PreAuthorize("hasAuthority('event:edit:admin')")
    public Response editEventsForAdmin(@Validated({EditGroup.class}) @RequestBody Events events) {
        Integer eventStatus = events.getEventStatus();
        String adminId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        events.setEventAreaAdminId(adminId);
        if (eventStatus == 1){
            return Response.success(eventsService.updateByPrimaryKeySelective(events));
        }
        else if(eventStatus == 2 || eventStatus == 3){
            Date date = new Date(System.currentTimeMillis());
            Events eventsNull = new Events();
            eventsNull.setEventId(events.getEventId());
            eventsNull.setEventStatus(events.getEventStatus());
            eventsNull.setCompletedAt(date);
            return Response.success(eventsService.updateByPrimaryKeySelective(eventsNull));
        }
        else {
            return Response.error("eventStatus值应在1-3之间！");
        }
    }

    /**
     * 修改报事事项
     * 权限：用户
     * @param events 事项信息
     *               String eventId 事件Id 【必要】
     *               String eventUserEvaluation 用户评价
     *               Integer eventStatus 事件状态 2或3
     *               String eventUserScore 用户评分 0-1
     *               其他参数将被忽略！
     * @return Response响应状态
     */
    @PostMapping("editForUser")
    @PreAuthorize("hasAuthority('event:edit:user')")
    public Response editEventsForUser(@Validated({EditGroup.class}) @RequestBody Events events) {
        Integer eventStatus = events.getEventStatus();
        String adminId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        events.setEventAreaAdminId(adminId);
        if(eventStatus == 2 || eventStatus == 3){
            Events eventsNull = new Events();
            eventsNull.setEventId(events.getEventId());
            eventsNull.setEventUserEvaluation(events.getEventUserEvaluation());
            eventsNull.setEventUserScore(events.getEventUserScore());
            return Response.success(eventsService.updateByPrimaryKeySelective(eventsNull));
        }
        else {
            return Response.error("eventStatus值应在2-3之间！");
        }
    }


    /* -------- query 查找模块 --------*/

    /**
     * 查找报事事项
     * 权限：网格长
     * 以下为【详细信息】
     * 联合筛选返回所有相关信息。
     * 联合查询 - 网格区域名 网格员姓名 报事用户姓名。
     * 支持筛选的参数：
     *      String eventId 通过事件Id查询；
     *      String eventAreaId 通过网格区域Id查询；
     *      String eventAreaAdminId 通过网格员Id查询；
     *      String eventUserId 通过报事用户Id查询；
     *      String startTime 通过时间区间查询 必须和endTime联合使用
     *      String endTime 通过时间区间查询 必须和startTime联合使用。
     *      注意：返回的数据格式为 yyyy-MM-dd！
     * @param events 报事事项筛选参数
     * @return 返回包含 报事事项所有信息 网格员所有信息 报事人所有信息 网格区域所有信息
     */
    @PostMapping("queryAllByPara")
    @PreAuthorize("hasAuthority('event:query')")
    public Response queryAllByPara(@RequestBody EventsQueryRequest events){
        return new Response(0, eventsService.selectWithAllRelation(events), "查询成功！");
    }

    /**
     * 查找报事事项
     * 【详细信息】
     * 权限：网格员
     * @param events 用于查找的报事事项
     * @return 报事事项数组
     */
    @PostMapping("queryAllForAdmin")
    @PreAuthorize("hasAuthority('event:query:admin')")
    public Response queryAllForAdminId(@RequestBody EventsQueryRequest events){
        String adminId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        events.setEventAreaAdminId(adminId);
        return new Response(0, eventsService.selectWithAllRelation(events), "查询成功！");
    }

    /**
     * 查找报事事项
     * 【详细信息】
     * 权限：用户
     * @param events 用于查找的报事事项
     * @return 报事事项数组
     */
    @PostMapping("queryAllForUser")
    @PreAuthorize("hasAuthority('event:query:user')")
    public Response queryAllForUser(@RequestBody EventsQueryRequest events){
        String userId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        events.setEventUserId(userId);
        return new Response(0, eventsService.selectWithAllRelation(events), "查询成功！");
    }

    /**
     * 查找报事事项
     * 【简略信息】
     * 包含事件描述 事件发生日期
     * 权限：用户
     * @param events 用于查找的报事事项
     * @return 报事事项数组
     */
    @PostMapping("queryEasyForUser")
    @PreAuthorize("hasAuthority('event:query:user')")
    public Response queryAllForUserSimple(@RequestBody EventsQueryRequest events){
        String userId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        events.setEventUserId(userId);
        return new Response(0, eventsService.selectEventDescAndCreatedAtByEventUserId(events), "查询成功！");
    }




    /* -------- delete 删除模块 --------*/

    /**
     * 根据事件Id删除
     * 权限：网格长
     * @param events 事项信息
     *               String eventId 事件Id 【必要】
     *               其他参数将被忽略！
     * @return Response响应状态
     */
    @PostMapping("delete")
    @PreAuthorize("hasAuthority('event:delete')")
    public Response deleteByEventId(@Validated({DeleteGroup.class})@RequestBody Events events){
        return Response.success(eventsService.deleteByPrimaryKey(events.getEventId()));
    }

    /**
     * 根据事件Id删除
     * 权限：网格员
     * @param events 事项信息
     *               String eventId 事件Id【必要】
     *               其他参数将被忽略！
     * @return Response响应状态
     */
    @PostMapping("deleteForAdmin")
    @PreAuthorize("hasAuthority('event:delete:admin')")
    public Response deleteByEventIdForAdmin(@Validated({DeleteGroup.class})@RequestBody Events events){
        String adminId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        events.setEventAreaAdminId(adminId);
        return Response.success(eventsService.deleteByEventIdAndEventAreaAdminId(events.getEventId(),events.getEventAreaAdminId()));
    }

    /**
     * 根据事件Id删除
     * 权限：用户
     * @param events 事项信息
     *               String eventId 事件Id【必要】
     * @return Response响应状态
     */
    @PostMapping("deleteForUser")
    @PreAuthorize("hasAuthority('event:delete:user')")
    public Response deleteByEventIdForUser(@Validated({DeleteGroup.class})@RequestBody Events events){
        String userId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        events.setEventUserId(userId);
        return Response.success(eventsService.deleteByEventIdAndEventUserId(events.getEventId(),events.getEventUserId()));
    }

}
