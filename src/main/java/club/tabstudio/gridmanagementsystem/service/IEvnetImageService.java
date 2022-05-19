/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.EventImage;

/**
 * @author wangyihan
 */
public interface IEvnetImageService {

    /**
     * 插入图片相关信息
     * @param record 报事图片相关信息
     * @return
     */
    int insertSelective(EventImage record);
}
