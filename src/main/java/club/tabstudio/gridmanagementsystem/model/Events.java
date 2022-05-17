
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

    @NotBlank(message = "缺少关键字段！", groups = {EditGroup.class, DeleteGroup.class})
    @Null(message = "非法提交！", groups = {CreateGroup.class})
    private String eventId;

    @NotBlank(message = "缺少关键字段！", groups = {CreateGroup.class})
    @Null(message = "无法被修改",groups = {EditGroup.class})
    private String eventAreaId;


    @Null(message = "无法被修改",groups = {EditGroup.class})
    private GridArea gridArea;

    @NotBlank(message = "缺少关键字段！", groups = {CreateGroup.class})
    private String eventAreaAdminId;

    private User areaAdmin;

    @Null(message = "非法提交！", groups = {CreateGroup.class})
    private String eventAreaAdminFeedback;

    @NotBlank(message = "缺少关键字段！", groups = {CreateGroup.class})
    @Null(message = "无法被修改",groups = {EditGroup.class})
    private String eventDesc;

//    @Null(message = "无法被修改",groups = {EditGroup.class})
    private String eventPosition;

//    @Null(message = "无法被修改",groups = {EditGroup.class})
    private String eventAddress;

    @NotBlank(message = "缺少关键字段！！", groups = {CreateGroup.class})
    @Null(message = "无法被修改",groups = {EditGroup.class})
    private String eventUserId;

    private User user;

    @Null(message = "非法提交！", groups = {CreateGroup.class})
    private String eventUserEvaluation;

    @Null(message = "非法提交！", groups = {CreateGroup.class})
    private Double eventUserScore;

    @Null(message = "非法提交！", groups = {CreateGroup.class})
    private Integer eventStatus;

    @Null(message = "非法提交！", groups = {CreateGroup.class,EditGroup.class})
    @Null(message = "无法被修改",groups = {EditGroup.class})
    private Date createdAt;

    @Null(message = "非法提交！", groups = {CreateGroup.class,EditGroup.class})
    @Null(message = "无法被修改",groups = {EditGroup.class})
    private Date acceptedAt;

    @Null(message = "非法提交！", groups = {CreateGroup.class,EditGroup.class})
    @Null(message = "无法被修改",groups = {EditGroup.class})
    private Date completedAt;




    }
