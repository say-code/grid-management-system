
package club.tabstudio.gridmanagementsystem.service.impl;

import club.tabstudio.gridmanagementsystem.mapper.EventsMapper;
import club.tabstudio.gridmanagementsystem.model.Events;
import club.tabstudio.gridmanagementsystem.model.User;
import club.tabstudio.gridmanagementsystem.request.EventsQueryRequest;
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
    public int deleteByPrimaryKey(String eventId) {
        return eventsMapper.deleteByPrimaryKey(eventId);
    }

    @Override
    public List<Events> selectEventDescAndCreatedAtByEventUserId(EventsQueryRequest eventsQueryRequest) {
        return eventsMapper.selectEventDescAndCreatedAtByEventUserId(eventsQueryRequest);
    }

    @Override
    public int deleteByEventIdAndEventAreaAdminId(String eventId, String eventAreaAdminId) {
        return eventsMapper.deleteByEventIdAndEventAreaAdminId(eventId,eventAreaAdminId);
    }

    @Override
    public int deleteByEventIdAndEventUserId(String eventId, String eventUserId) {
        return eventsMapper.deleteByEventIdAndEventUserId(eventId, eventUserId);
    }

    @Override
    public int updateByEventIdAndEventAreaAdminId(Events updated, String eventId, String eventAreaAdminId) {
        return eventsMapper.updateByEventIdAndEventAreaAdminId(updated,eventId,eventAreaAdminId);
    }

    @Override
    public List<Events> selectWithAllRelation(EventsQueryRequest events) {
        return eventsMapper.selectWithAllRelation(events);
    }


}
