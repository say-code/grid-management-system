/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.controller;

import club.tabstudio.gridmanagementsystem.model.Events;
import club.tabstudio.gridmanagementsystem.service.IEventsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * 请求所有报事事项
     * @return
     */
    @GetMapping("queryAll")
    public List<Events> queryAll() {
        return eventsService.selectAllEvents();
    }

    /**
     * 修改报事事项
     * @param map 报事事项
     * @return 参数是否修改成功
     */
    @PostMapping("edit")
    public int editEvents(@RequestBody Map<String,Object> map) {
        log.warn("我在这里！！！！！！！！"+String.valueOf(map));
        Integer eventStatus = Integer.valueOf(map.get("eventStatus").toString());
        String eventId = String.valueOf(map.get("eventId"));
        if (eventStatus == 0){
            Events events = new Events();
            events.mapToEvents(map);
            return eventsService.insertSelective(events);
        }
        Events events = eventsService.selectByPrimaryKey(eventId);
        if (eventStatus == 1 && events.getAcceptedAt() == null){
            events.mapToEvents(map);
            Date date = new Date(System.currentTimeMillis());
            events.setAcceptedAt(date);
            return eventsService.updateByPrimaryKeySelective(events);
        }
        else if (eventStatus == 1){
            return eventsService.updateByPrimaryKeySelective(events);
        }
        else {
            Date date = new Date(System.currentTimeMillis());
            events.setCompletedAt(date);
            return eventsService.updateByPrimaryKeySelective(events);
        }
    }

    /**
     * 通过事件网格区域Id查找报事事项
     * @param eventAreaId 报事网格区域Id
     * @return 报事事项数组
     */
    @GetMapping("queryByEventAreaId")
    public List<Events> queryByEventAreaId(@RequestParam("eventAreaId") String eventAreaId){
        return eventsService.selectByEventAreaId(eventAreaId);
    }

    /**
     * 通过网格员姓名查找相应报事事项
     * @param name 网格员姓名
     * @return 报事事项数组
     */
    @GetMapping("queryByName")
    public List<Events> queryByName(@RequestParam("name") String name){
        return eventsService.selectEventByName(name);
    }

    /**
     *  通过网格员Id查找报事事项
     * @param eventAreaAdminId
     * @return 报事事项数组
     */
    @GetMapping("queryByAdminId")
    public List<Events> queryByAdminId(@RequestParam("eventAdminId") String eventAreaAdminId){
        return eventsService.selectAllByEventAreaAdminId(eventAreaAdminId);
    }

    /**
     * 根据报事时间查找报事事项
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

}
