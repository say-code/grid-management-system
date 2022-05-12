/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.service;

import club.tabstudio.gridmanagementsystem.model.GridArea;

import java.util.List;

/**
 * 网格区域 Service接口层
 * @author wangyihan
 */
public interface IGridAreaService {
    /**
     * 查找所有的网格区域
     * @return 网格区域数组
     */
    List<GridArea> selectAll();
}
