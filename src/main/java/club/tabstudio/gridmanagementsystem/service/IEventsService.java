
/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.Events;

import java.util.List;

/**
 * 报事事项 Service接口层
 * @author wangyihan
 */
public interface IEventsService {

    /**
     * 查询所有事件
     * @return 事件数组
     */
    List<Events> selectAllEvents();
}
