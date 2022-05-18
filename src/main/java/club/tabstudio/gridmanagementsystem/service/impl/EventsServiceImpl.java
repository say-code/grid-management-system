
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
    public int updateByEventIdAndEventAreaAdminId(Events updated, String eventId, String eventAreaAdminId) {
        return eventsMapper.updateByEventIdAndEventAreaAdminId(updated,eventId,eventAreaAdminId);
    }

    @Override
    public List<Events> selectWithAllRelation(EventsQueryRequest events) {
        return eventsMapper.selectWithAllRelation(events);
    }


}
