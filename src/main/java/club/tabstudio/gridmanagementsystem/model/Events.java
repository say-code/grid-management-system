
/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.model;

import java.util.Date;

import club.tabstudio.gridmanagementsystem.validation.groups.CreateGroup;
import club.tabstudio.gridmanagementsystem.validation.groups.DeleteGroup;
import club.tabstudio.gridmanagementsystem.validation.groups.EditGroup;
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
public class Events {

    @NotBlank(message = "该字段不能为空！", groups = {EditGroup.class, DeleteGroup.class})
    @Null(message = "事件ID必须为空", groups = {CreateGroup.class})
    private String eventId;

    @NotBlank(message = "该字段不能为空！", groups = {CreateGroup.class})
    private String eventAreaId;

    @NotBlank(message = "该字段不能为空！", groups = {CreateGroup.class})
    private String eventAreaAdminId;

    @NotBlank(message = "该字段不能为空！", groups = {CreateGroup.class})
    private String eventDesc;

    private String eventPosition;

    private String eventAddress;

    @NotBlank(message = "该字段不能为空！", groups = {EditGroup.class, CreateGroup.class})
    private String eventUserId;

    @Null(message = "创建时不能传入该字段！", groups = {CreateGroup.class})
    private String eventUserEvaluation;

    @Null(message = "创建时不能传入该字段！", groups = {CreateGroup.class})
    private Double eventUserScore;

    @Null(message = "创建时不能传入该字段！", groups = {CreateGroup.class})
    private Integer eventStatus;

    @Null(message = "时间会自动加载，请勿传入！", groups = {CreateGroup.class,EditGroup.class})
    private Date createdAt;

    @Null(message = "时间会自动加载，请勿传入！", groups = {CreateGroup.class,EditGroup.class})
    private Date acceptedAt;

    @Null(message = "时间会自动加载，请勿传入！", groups = {CreateGroup.class,EditGroup.class})
    private Date completedAt;




    }
