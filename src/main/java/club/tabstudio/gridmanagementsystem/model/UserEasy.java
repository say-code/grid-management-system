/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author wangyihan
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEasy {
    private String eventDesc;
    private Date createdAt;
}
