package club.tabstudio.gridmanagementsystem.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户登入请求
 * @author Onion
 */
@Data
public class LoginRequest {

    @NotBlank(message = "用户名不可为空")
    private String username;

    @NotBlank(message = "密码不可为空")
    private String password;
}
