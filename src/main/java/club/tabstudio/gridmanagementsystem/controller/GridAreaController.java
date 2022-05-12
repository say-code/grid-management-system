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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private List<GridArea> queryAll(){
        log.warn("俺动了一下！");
        return gridAreaService.selectAll();
    }


}
