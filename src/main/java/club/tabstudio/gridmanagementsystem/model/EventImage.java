package club.tabstudio.gridmanagementsystem.model;

import club.tabstudio.gridmanagementsystem.validation.groups.CreateGroup;
import club.tabstudio.gridmanagementsystem.validation.groups.DeleteGroup;
import club.tabstudio.gridmanagementsystem.validation.groups.EditGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventImage {
    @Null(message = "非法传参！", groups = {CreateGroup.class})
    private String eventImageId;

    @Null(message = "非法传参！", groups = {CreateGroup.class})
    private String eventId;

    @Null(message = "非法传参！", groups = {CreateGroup.class, DeleteGroup.class})
    private String eventImagePath;
}