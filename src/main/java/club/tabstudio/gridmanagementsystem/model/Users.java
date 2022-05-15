/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @NotBlank(message = "该字段不能为空！", groups = {})
    private String userId;

    private String username;

    private String password;

    private String name;

    private String telephone;

    private String areaId;

    private String address;

    private String avatarUrl;

    private String openId;

    private byte[] unionId;

    private Date createdAt;

    private Date updatedAt;
}