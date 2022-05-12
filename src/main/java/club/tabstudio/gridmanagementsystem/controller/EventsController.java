
/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.controller;

import club.tabstudio.gridmanagementsystem.model.Events;
import club.tabstudio.gridmanagementsystem.service.IEventsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


    /**
     * 修改报事事项
     * @param events 报事事项
     * @return 1表示参数修改成功
     */
    @PostMapping("edit")
    public int editEvents(@RequestBody Events events) {
        Integer eventStatus = events.getEventStatus();
        if (eventStatus == 1 && events.getAcceptedAt() == null){
            Date date = new Date(System.currentTimeMillis());
            events.setAcceptedAt(date);
            return eventsService.updateByPrimaryKeySelective(events);
        }
        else if (eventStatus == 1){
            return eventsService.updateByPrimaryKeySelective(events);
        }
        else if(eventStatus == 2){
            Date date = new Date(System.currentTimeMillis());
            events.setCompletedAt(date);
            return eventsService.updateByPrimaryKeySelective(events);
        }
        else {
            return 0;
        }
    }

    /**
     * 通过事件网格区域Id查找报事事项
     * 废弃
     * @param eventAreaId 报事网格区域Id
     * @return 报事事项数组
     */
    @GetMapping("queryByEventAreaId")
    public List<Events> queryByEventAreaId(@RequestParam("eventAreaId") String eventAreaId){
        return eventsService.selectByEventAreaId(eventAreaId);
    }

    /**
     * 通过网格员姓名查找相应报事事项
     * 废弃
     * @param name 网格员姓名
     * @return 报事事项数组
     */
    @GetMapping("queryByName")
    public List<Events> queryByName(@RequestParam("name") String name){
        return eventsService.selectEventByName(name);
    }

    /**
     *  通过网格员Id查找报事事项
     *  废弃
     * @param eventAreaAdminId 网格员Id
     * @return 报事事项数组
     */
    @GetMapping("queryByAdminId")
    public List<Events> queryByAdminId(@RequestParam("eventAdminId") String eventAreaAdminId){
        return eventsService.selectAllByEventAreaAdminId(eventAreaAdminId);
    }

    /**
     * 根据报事时间查找报事事项
     * 废弃
     * @param year 年
     * @param month 月
     * @return 报事事项数组
     */
    @GetMapping("queryByTime")
    public List<Events> queryByTime(@RequestParam("year") Integer year,
                                    @RequestParam("month") Integer month){
        Date overDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        try {
            overDate = format.parse(year.toString()+month.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return eventsService.selectByTime(overDate);
    }

    /**
     *  插入新的报事事项
     *  createdAt 参数自动创建，无需传入
     * @param events 报事事项
     * @return 1表示插入成功
     */
    @PostMapping("insert")
    public int insert(@RequestBody Events events){
        events.setEventStatus(0);
        return eventsService.insertSelective(events);
    }

    /**
     * 使用本接口时请至少携带一个参数！！！！(绝不是因为我不会）
     * 无参请使用queryAll。
     * 联合筛选返回所有相关信息。
     * 联合查询 - 网格区域名 网格员姓名 报事用户姓名。
     * 支持筛选的参数：
     *      String eventId 通过事件Id查询；
     *      String eventAreaId 通过网格区域Id查询；
     *      String eventAreaAdminId 通过网格员Id查询；
     *      String eventUserId 通过报事用户Id查询；
     *      {
     *          String startTimeYear 开始年份
     *          String startTimeMonth 开始月份
     *          String startTimeDate 开始日期
     *      }startTime 通过时间区间查询 必须和endTime联合使用
     *      {
     *          String endTimeYear 结束年份
     *          String endTimeMonth 结束月份
     *          String endTimeDate 结束日期
     *      }endTime 通过时间区间查询 必须和startTime联合使用。
     *      !!!注意：返回的年份必须是4位，月份和日期必须是两位，如"01","22"!!!
     * @param events 报事事项筛选参数
     * @return 返回包含 event所有信息 网格区域名 网格员姓名 报事用户姓名的数组
     */
    @PostMapping("queryAllByPara")
    public List<Events> queryAllByPara(@RequestBody Events events){
        events.timeStatusCheck();
        return eventsService.selectAllWithOthersSelective(events);
    }

    @PostMapping("queryAll")
    public List<Events> queryAllByParaPost(){
        return eventsService.selectAllEvents();
    }

    @GetMapping("queryAll")
    public List<Events> queryAllByParaGet(){
        return eventsService.selectAllEvents();
    }

    /**
     * 根据时间Id删除
     * @param eventId 事件Id
     * @return 1表示删除成功
     */
    @GetMapping("delete")
    public int deleteByEventId(@RequestParam String eventId){
        return eventsService.deleteByPrimaryKey(eventId);
    }

}
