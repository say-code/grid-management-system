package club.tabstudio.gridmanagementsystem.controller;

import club.tabstudio.gridmanagementsystem.model.Response;
import club.tabstudio.gridmanagementsystem.model.UserWithPermissionList;
import club.tabstudio.gridmanagementsystem.request.LoginRequest;
import club.tabstudio.gridmanagementsystem.service.IUserService;
import club.tabstudio.gridmanagementsystem.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登入
 *
 * @author Onion
 */
@RestController
public class LoginController {

    @Resource
    private IUserService userService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Response login(@Valid @RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
            );
            Authentication authenticate = authenticationManager
                    .authenticate(authRequest);

            UserWithPermissionList user = (UserWithPermissionList) authenticate.getPrincipal();

            String token = JwtUtils.createToken(user);
            Map<String, Object> resMap = new HashMap<>(1);
            resMap.put("access_token", token);

            return Response.success(resMap);
        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
            return Response.error("用户名或密码错误");
        }
    }
}
