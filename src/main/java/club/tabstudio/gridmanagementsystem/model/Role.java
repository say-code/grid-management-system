package club.tabstudio.gridmanagementsystem.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Onion
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer roleId;

    private String roleName;

    private String roleRemark;

    private String roleEnabled;

    private Date createdAt;

    private Date updateAt;
}