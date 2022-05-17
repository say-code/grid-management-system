
package club.tabstudio.gridmanagementsystem.controller;

import club.tabstudio.gridmanagementsystem.model.Events;
import club.tabstudio.gridmanagementsystem.model.Permission;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
     * 权限：系统管理员 网格员 用户
     * @param events
     *            系统管理员 可修改属性：
     *               String eventId 事件Id 【必要】
     *               String eventAreaAdminId 网格员Id
     *               String eventAreaId 网格区域Id
     *               String eventPosition 事件经纬度坐标
     *               String eventAddress 事件发生位置
     *               String eventAreaAdminFeedback 网格员反馈
     *               String eventStatus 事件状态
     *             网格员 可修改属性：
     *               String eventId 事件Id 【必要】
     *               String eventAreaAdminFeedback 网格员反馈
     *               String eventStatus 事件状态
     *             用户 可修改属性：
     *               String eventId 事件Id 【必要】
     *               String eventUserEvaluation 用户评价
     *               String eventUserScore 用户打分
     *
     * @return Response响应状态
     */
    @PostMapping("edit")
    @PreAuthorize("hasAuthority('event:edit')")
    public Response editEvents(@Validated({EditGroup.class}) @RequestBody Events events) {

        //获取权限名字列表
        List<Permission> permissionList = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPermissionList();
        List<String> permissionNameList = new ArrayList<>();
        for (Permission permission : permissionList) {
            permissionNameList.add(permission.getPermissionName());
        }

        //获取登录Id
        String authorId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();

        //获取传入的事件状态参数
        Integer eventStatus = events.getEventStatus();

        Events eventsParam = new Events();
        eventsParam.setEventStatus(eventStatus);
        eventsParam.setEventId(events.getEventId());
        //根据权限筛查传入参数，过滤超越权限的参数
        if (permissionNameList.contains("event:edit:admin")){
            eventsParam.setEventAreaId(events.getEventAreaId());
            eventsParam.setEventAreaAdminId(events.getEventAreaAdminId());
            eventsParam.setEventPosition(events.getEventPosition());
            eventsParam.setEventAddress(events.getEventAddress());
            eventsParam.setEventAreaAdminFeedback(events.getEventAreaAdminFeedback());
        }else if (permissionNameList.contains("event:edit:grid")){
            if (!authorId.equals(events.getEventAreaAdminId())){
                return Response.error("您没有权限修改！");
            }
            eventsParam.setEventAreaAdminFeedback(events.getEventAreaAdminFeedback());
        }else if (permissionNameList.contains("event:edit")){
            if (!authorId.equals(events.getEventUserId())){
                return Response.error("您没有权限修改！");
            }
            eventsParam.setEventUserEvaluation(events.getEventUserEvaluation());
            eventsParam.setEventUserScore(events.getEventUserScore());
        }else {
            return Response.error("您没有权限修改！");
        }

        //根据传入事件Id从数据库获取相应数据
        Events trulyEvents = eventsService.selectByPrimaryKey(events.getEventId());
        //查看事件状态
        Integer trulyEventsStatus = trulyEvents.getEventStatus();
        //事件完成后，只允许用户修改评价和评分
        if ((trulyEventsStatus == 2 || trulyEventsStatus == 3) &&
                !permissionNameList.contains("event:edit") &&
                (trulyEvents.getEventUserScore() != null ||
                        trulyEvents.getEventUserEvaluation() != null)){
            return Response.error("事件已完成，无法修改！");
        }

        //传参、修改
        if (eventStatus == 1 && !permissionNameList.contains("event:edit")){
            return Response.success(eventsService.updateByPrimaryKeySelective(events));
        }
        else if(eventStatus == 2 || eventStatus == 3){
            if (trulyEvents.getCompletedAt() == null){
                Date date = new Date(System.currentTimeMillis());
                events.setCompletedAt(date);
            }
            return Response.success(eventsService.updateByPrimaryKeySelective(events));
        }
        else {
            return Response.error("事件状态码错误或权限不足！");
        }

    }

    /**
     * 网格员查看报事时触发
     * 权限：网格员
     *
     * @param events 事项信息
     *               String eventId 事件Id 【必要】
     *               int eventStatus 0 【必要】
     *               其他参数将被忽略！
     * @return Response响应状态
     */
    @PostMapping("eventsAccept")
    @PreAuthorize("hasAuthority('event:edit:grid')")
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


    /* -------- query 查找模块 --------*/

    /**
     * 查找报事事项
     * 权限：管理员 网格长 用户
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
    @GetMapping("queryAllByPara")
    @PreAuthorize("hasAuthority('event:query')")
    public Response queryAllByPara(EventsQueryRequest events){

        //获取权限名字列表
        List<Permission> permissionList = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPermissionList();
        List<String> permissionNameList = new ArrayList<>();
        for (Permission permission : permissionList) {
            permissionNameList.add(permission.getPermissionName());
        }

        //获取登录Id
        String authorId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();

        //根据权限筛查传入参数
        if (permissionNameList.contains("event:query:admin")){
            return Response.success(eventsService.selectWithAllRelation(events));
        }else if(permissionNameList.contains("event:query:grid")){
            events.setEventAreaAdminId(authorId);
            return Response.success(eventsService.selectWithAllRelation(events));
        }else if (permissionNameList.contains("event:query")){
            events.setEventUserId(authorId);
            return Response.success(eventsService.selectWithAllRelation(events));
        }else {
            return Response.error("权限不足！");
        }

    }

    /**
     * 查找报事事项
     * 【简略信息】
     * 包含报事事项
     * 权限：用户
     * @param events 用于查找的报事事项
     * @return 报事事项数组
     */
    @GetMapping("queryEasyForUser")
    @PreAuthorize("hasAuthority('event:query')")
    public Response queryAllForUserSimple(EventsQueryRequest events){
        String userId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        events.setEventUserId(userId);
        return Response.success(eventsService.selectEventDescAndCreatedAtByEventUserId(events));
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
        //根据传入事件Id从数据库获取相应数据
        Events trulyEvents = eventsService.selectByPrimaryKey(events.getEventId());

        //获取权限名字列表
        List<Permission> permissionList = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPermissionList();
        List<String> permissionNameList = new ArrayList<>();
        for (Permission permission : permissionList) {
            permissionNameList.add(permission.getPermissionName());
        }

        //获取登录Id
        String authorId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();


        //根据权限筛查传入参数
        if (permissionNameList.contains("event:delete:admin")){
            return Response.success(eventsService.deleteByPrimaryKey(events.getEventId()));
        }else if (permissionNameList.contains("event:delete:grid")){
            if (!authorId.equals(trulyEvents.getEventAreaAdminId())){
                return Response.error("您没有权限!");
            }
            return Response.success(eventsService.deleteByPrimaryKey(events.getEventId()));
        }else if (permissionNameList.contains("event:delete")){
            if (!authorId.equals(trulyEvents.getEventUserId())){
                return Response.error("您没有权限!");
            }
            return Response.success(eventsService.deleteByPrimaryKey(events.getEventId()));
        }else {
            return Response.error("您没有权限!");
        }

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
     *               其他参数将被忽略！
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
