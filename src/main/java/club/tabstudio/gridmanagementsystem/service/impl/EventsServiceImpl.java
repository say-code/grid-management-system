/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.service.impl;

import club.tabstudio.gridmanagementsystem.mapper.EventsMapper;
import club.tabstudio.gridmanagementsystem.model.Events;
import club.tabstudio.gridmanagementsystem.service.IEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 报事事项 服务实现层
 * @author wangyihan
 */
@Service
public class EventsServiceImpl implements IEventsService {

    @Autowired
    private EventsMapper eventsMapper;

    @Override
    public List<Events> selectAllEvents() {
        return eventsMapper.selectAll();
    }

    @Override
    public void editEvents(Events events) {

    }

    @Override
    public List<Events> selectByEventAreaId(String eventAreaId) {
        return eventsMapper.selectAllByEventAreaId(eventAreaId);
    }

    @Override
    public List<Events> selectEventByName(String name) {
        return eventsMapper.selectEventByNameWithUsers(name);
    }

    @Override
    public List<Events> selectAllByEventAreaAdminId(String eventAreaAdminId) {
        return eventsMapper.selectAllByEventAreaAdminId(eventAreaAdminId);
    }

    @Override
    public List<Events> selectByTime(Date time) {
        return eventsMapper.selectByCreatedAt(time);
    }

    @Override
    public Events selectByPrimaryKey(String eventId) {
        return eventsMapper.selectByPrimaryKey(eventId);
    }

    @Override
    public int insertSelective(Events record) {
        return eventsMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Events record) {
        return eventsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Events> selectAllWithOthersSelective(Events events) {
        return eventsMapper.selectAllWithOthersSelective(events);
    }

    @Override
    public int deleteByPrimaryKey(String eventId) {
        return eventsMapper.deleteByPrimaryKey(eventId);
    }


}
