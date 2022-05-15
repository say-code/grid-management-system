package club.tabstudio.gridmanagementsystem.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Onion
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements GrantedAuthority {
    private Integer permissionId;

    private Integer permissionParentId;

    @JsonValue
    private String permissionName;

    private Date createdAt;

    private Date updatedAt;

    @Override
    public String getAuthority() {
        return permissionName;
    }
}