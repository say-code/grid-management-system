
package club.tabstudio.gridmanagementsystem.service.impl;

import club.tabstudio.gridmanagementsystem.mapper.EventImageMapper;
import club.tabstudio.gridmanagementsystem.mapper.EventsMapper;
import club.tabstudio.gridmanagementsystem.model.Events;
import club.tabstudio.gridmanagementsystem.model.EventImage;
import club.tabstudio.gridmanagementsystem.model.Response;
import club.tabstudio.gridmanagementsystem.model.UserWithPermissionList;
import club.tabstudio.gridmanagementsystem.request.EventImageRequest;
import club.tabstudio.gridmanagementsystem.request.EventsQueryRequest;
import club.tabstudio.gridmanagementsystem.service.IEventsService;
import com.sun.java.accessibility.util.EventID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 报事事项 服务实现层
 * @author wangyihan
 */
@Service
public class EventsServiceImpl implements IEventsService {

    @Autowired
    private EventsMapper eventsMapper;

    @Autowired
    private EventImageMapper eventImageMapper;

    @Override
    public Events selectByPrimaryKey(String eventId) {
        return eventsMapper.selectByPrimaryKey(eventId);
    }

    @Override
    public int insertSelective(EventImageRequest events) {
        events.setEventId(UUID.randomUUID().toString());
        String authorId = ((UserWithPermissionList) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        events.setEventUserId(authorId);
        if (events.getEventImageIdList() != null){
            List<String> eventImageIdList= events.getEventImageIdList();
            eventImageMapper.updateBatchByImageId(events.getEventId(),eventImageIdList);

        }
        return eventsMapper.insertSelective(events);
    }

    @Override
    public int updateByPrimaryKeySelective(Events record) {

        return eventsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String eventId) {
        List<String> pathList = eventImageMapper.selectEventImagePathByEventId(eventId);
        for (String path: pathList) {
            File directory = new File("");
            try {
                String courseFile = directory.getCanonicalPath();
                System.out.println(path);
                String filePath = courseFile+"/src/main/resources/"+path;
                File file = new File(filePath);
                System.out.println(file.delete());
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        eventImageMapper.deleteByEventId(eventId);
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
