/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.model;

import java.util.Date;

import club.tabstudio.gridmanagementsystem.validation.groups.CreateGroup;
import club.tabstudio.gridmanagementsystem.validation.groups.DeleteGroup;
import club.tabstudio.gridmanagementsystem.validation.groups.EditGroup;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * @author wangyihan
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GridArea {

    @NotBlank(message = "该字段不能为空！", groups = {EditGroup.class, DeleteGroup.class})
    private String areaId;

    @NotBlank(message = "该字段不能为空", groups = {CreateGroup.class})
    private String areaName;

    @NotBlank(message = "该字段不能为空", groups = {CreateGroup.class})
    private String areaGis;

    @Null(message = "非法字段", groups = {CreateGroup.class,EditGroup.class})
    private Date createdAt;

    @Null(message = "非法字段", groups = {CreateGroup.class,EditGroup.class})
    private Date updatedAt;
}