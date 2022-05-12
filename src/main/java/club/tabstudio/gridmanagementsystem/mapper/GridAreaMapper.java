/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import club.tabstudio.gridmanagementsystem.model.GridArea;

/**
 * @author wangyihan
 */
public interface GridAreaMapper {
    /**
     * 通过areaId删除网格信息
     * @param areaId 删除网格信息
     * @return 1表示删除成功
     */
    int deleteByPrimaryKey(String areaId);

    /**
     * 增加网格区域细信息
     * @param record 网格区域信息
     * @return 1为插入成功
     */
    int insertSelective(GridArea record);

    /**
     *  通过areaId更新网格区域信息
     * @param record 网格区域信息
     * @return 1表示更新成功
     */
    int updateByPrimaryKeySelective(GridArea record);


    /**
     * 查找所有的网格区域
     * @return 网格区域数组
     */
    List<GridArea> selectAll();


}