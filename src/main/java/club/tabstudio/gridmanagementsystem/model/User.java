package club.tabstudio.gridmanagementsystem.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

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

    private String unionId;

    private Date createdAt;

    private Date updatedAt;
}