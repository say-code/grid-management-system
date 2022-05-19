package club.tabstudio.gridmanagementsystem.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import club.tabstudio.gridmanagementsystem.model.EventImage;

public interface EventImageMapper {
    int deleteByPrimaryKey(String eventImageId);

    int insert(EventImage record);

    /**
     * 插入图片相关信息
     * @param record 报事图片相关信息
     * @return
     */
    int insertSelective(EventImage record);

    EventImage selectByPrimaryKey(String eventImageId);

    int updateByPrimaryKeySelective(EventImage record);

    int updateByPrimaryKey(EventImage record);

    List<EventImage> selectAll();

    List<String> selectEventImageId();

    int deleteByEventId(@Param("eventId")String eventId);

    List<EventImage> selectByEventId(@Param("eventId")String eventId);

    List<String> selectEventImagePathByEventId(@Param("eventId")String eventId);

    int updateBatchByImageId(@Param("eventId") String eventId, @Param("eventImageIds") List<String> eventImageIds);










}