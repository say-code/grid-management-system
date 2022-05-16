
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
     *  插入新的报事事项
     *  createdAt 参数自动创建，无需传入
     * @param events 报事事项
     * @return Response响应状态
     */
    @PostMapping("insert")
    @PreAuthorize("hasAuthority('event:creat')")
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
     * @param events 报事事项
     * @return Response响应状态
     */
    @PostMapping("edit")
    public Response editEvents(@Validated({EditGroup.class}) @RequestBody Events events) {
        Integer eventStatus = events.getEventStatus();
        if (eventStatus == 1 && events.getAcceptedAt() == null){
            Date date = new Date(System.currentTimeMillis());
            events.setAcceptedAt(date);
            return Response.success(eventsService.updateByPrimaryKeySelective(events));
        }
        else if (eventStatus == 1){
            return Response.success(eventsService.updateByPrimaryKeySelective(events));
        }
        else if(eventStatus == 2 || eventStatus == 3){
            Date date = new Date(System.currentTimeMillis());
            events.setCompletedAt(date);
            return Response.success(eventsService.updateByPrimaryKeySelective(events));
        }
        else {
            return Response.error("eventStatus值应在0-2之间！");
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
        String adminId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        events.setEventUserId(adminId);
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
    @PostMapping("queryAllForUser")
    @PreAuthorize("hasAuthority('event:query:admin')")
    public Response queryAllForUserSimple(@RequestBody EventsQueryRequest events){
        String adminId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        events.setEventUserId(adminId);
        return new Response(0, eventsService.selectEventDescAndCreatedAtByEventUserId(events), "查询成功！");
    }


    /* -------- delete 删除模块 --------*/

    /**
     * 根据事件Id删除
     * @param events 事件Id
     * @return Response响应状态
     */
    @PostMapping("delete")
    @PreAuthorize("hasAuthority('event:delete')")
    public Response deleteByEventId(@Validated({DeleteGroup.class})@RequestBody Events events){
        return Response.success(eventsService.deleteByPrimaryKey(events.getEventId()));
    }

}
