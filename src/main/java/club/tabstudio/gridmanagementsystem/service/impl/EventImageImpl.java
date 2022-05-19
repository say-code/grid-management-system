/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.service.impl;

import club.tabstudio.gridmanagementsystem.mapper.EventImageMapper;
import club.tabstudio.gridmanagementsystem.model.EventImage;
import club.tabstudio.gridmanagementsystem.service.IEvnetImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangyihan
 */
@Service
public class EventImageImpl implements IEvnetImageService {

    @Autowired
    EventImageMapper eventImageMapper;


    @Override
    public int insertSelective(EventImage record) {
        return eventImageMapper.insertSelective(record);
    }
}
