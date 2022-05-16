package club.tabstudio.gridmanagementsystem.filter;

import club.tabstudio.gridmanagementsystem.constant.SecurityConstant;
import club.tabstudio.gridmanagementsystem.model.UserWithPermissionList;
import club.tabstudio.gridmanagementsystem.service.IUserService;
import club.tabstudio.gridmanagementsystem.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Onion
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Resource
    private IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(SecurityConstant.AUTHORIZATION_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        Claims claims = null;
        try {
            claims = JwtUtils.parseToken(token);
        } catch (MalformedJwtException ignored) {

        }
        if (claims == null) {
            filterChain.doFilter(request, response);
            return;
        }


        // Todo: cache the user details to redis.
        UserWithPermissionList userDetails = userService
                .selectWithPermissionListByUsername(JwtUtils.getUserName(token));


        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        new ArrayList<>() : userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
