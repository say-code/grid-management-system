/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.controller;

import club.tabstudio.gridmanagementsystem.model.GridArea;
import club.tabstudio.gridmanagementsystem.service.IGridAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 网格区域 控制层
 * @Author: wangyihan
 */
@Slf4j
@RestController
@RequestMapping("gridArea")
public class GridAreaController {

    @Autowired
    private IGridAreaService gridAreaService;

    /**
     * 查找所有网格区域信息
     * @return 网格信息数组
     */
    @GetMapping("queryAll")
    public List<GridArea> queryAll(){
        return gridAreaService.selectAll();
    }

    /**
     * 插入网格区域信息
     * @param gridArea 网格区域信息
     * @return 1表示插入成功
     */
    @PostMapping("insert")
    public int insert(@RequestBody GridArea gridArea){
        log.warn(gridArea.toString());
        gridArea.setAreaId(UUID.randomUUID().toString());
        gridArea.setCreatedAt(new Date(System.currentTimeMillis()));
        return gridAreaService.insertSelective(gridArea);
    }

//    @PostMapping("update")



}
