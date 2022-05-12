/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyihan
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GridArea {
    private String areaId;

    private String areaName;

    private String areaGis;

    private Date createdAt;

    private Date updatedAt;
}