package club.tabstudio.gridmanagementsystem.controller;

import club.tabstudio.gridmanagementsystem.model.GridArea;
import club.tabstudio.gridmanagementsystem.model.Response;
import club.tabstudio.gridmanagementsystem.service.IGridAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Response queryAll(){
        return Response.success(gridAreaService.selectAll());
    }

    /**
     * 插入网格区域信息
     * @param gridArea 网格区域信息
     * @return 1表示插入成功
     */
    @PostMapping("insert")
    public Response insert(@RequestBody GridArea gridArea){
        gridArea.setAreaId(UUID.randomUUID().toString());
        gridArea.setCreatedAt(new Date(System.currentTimeMillis()));
        return Response.success(gridAreaService.insertSelective(gridArea));
    }

    /**
     * 通过areaId更新网格区域信息
     * @param gridArea 网格区域信息
     * @return 1表示更新成功
     */
    @PostMapping("update")
    public Response update(@RequestBody GridArea gridArea){
        gridArea.setUpdatedAt(new Date(System.currentTimeMillis()));
        return  Response.success(gridAreaService.updateByPrimaryKeySelective(gridArea));
    }

    /**
     * 根据网格区域Id删除
     * @param gridArea 传入Id即可
     * @return 是否删除成功
     */
    @PostMapping("delete")
    public Response delete(@RequestBody GridArea gridArea){
        return Response.success(gridAreaService.deleteByPrimaryKey(gridArea.getAreaId()));
    }


}
