/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 报事事项 Model
 * @author wangyihan
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    private String eventId;

    private Integer eventAreaId;

    private String eventDesc;

    private String eventPosition;

    private String eventAddress;

    private Integer eventStatus;

    private Integer eventAreaAdminId;

    private String eventUserEvaluation;

    private Double eventUserScore;

    private Date createdAt;

    private Date acceptedAt;

    private Date completedAt;
}