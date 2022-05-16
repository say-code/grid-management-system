
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

    @NotBlank(message = "eventId字段不能为空！", groups = {EditGroup.class, DeleteGroup.class})
    @Null(message = "事件ID必须为空", groups = {CreateGroup.class})
    private String eventId;

    @NotBlank(message = "eventAreaId字段不能为空！", groups = {CreateGroup.class})
    private String eventAreaId;

    private GridArea gridArea;

    @NotBlank(message = "eventAreaAdminId字段不能为空！", groups = {CreateGroup.class})
    private String eventAreaAdminId;

    private User areaAdmin;

    private String eventAreaAdminFeedback;

    @NotBlank(message = "eventDesc字段不能为空！", groups = {CreateGroup.class})
    private String eventDesc;

    private String eventPosition;

    private String eventAddress;

    @NotBlank(message = "eventUserId字段不能为空！", groups = {EditGroup.class, CreateGroup.class})
    private String eventUserId;

    private User user;

    @Null(message = "创建时不能传入eventUserEvaluation字段！", groups = {CreateGroup.class})
    private String eventUserEvaluation;

    @Null(message = "创建时不能传入eventUserScore字段！", groups = {CreateGroup.class})
    private Double eventUserScore;

    @Null(message = "创建时不能传入eventStatus字段！", groups = {CreateGroup.class})
    private Integer eventStatus;

    @Null(message = "createdAt非法提交", groups = {CreateGroup.class,EditGroup.class})
    private Date createdAt;

    @Null(message = "acceptedAt非法提交", groups = {CreateGroup.class,EditGroup.class})
    private Date acceptedAt;

    @Null(message = "completedAt非法提交", groups = {CreateGroup.class,EditGroup.class})
    private Date completedAt;




    }
