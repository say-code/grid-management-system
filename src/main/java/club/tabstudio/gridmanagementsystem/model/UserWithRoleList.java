package club.tabstudio.gridmanagementsystem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Onion
 */
public class UserWithRoleList extends User {

    @Getter
    @Setter
    private List<Role> roleList;
}