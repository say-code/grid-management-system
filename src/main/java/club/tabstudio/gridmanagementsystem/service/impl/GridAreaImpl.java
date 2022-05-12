/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.service.impl;

import club.tabstudio.gridmanagementsystem.mapper.GridAreaMapper;
import club.tabstudio.gridmanagementsystem.model.GridArea;
import club.tabstudio.gridmanagementsystem.service.IGridAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyihan
 */
@Service
public class GridAreaImpl implements IGridAreaService {

    @Autowired
    GridAreaMapper gridAreaMapper;

    @Override
    public List<GridArea> selectAll() {
        return gridAreaMapper.selectAll();
    }
}
