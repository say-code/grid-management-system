/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    private String eventId;

    private String eventAreaId;

    private String eventAreaAdminId;

    private String eventDesc;

    private String eventPosition;

    private String eventAddress;

    private String eventUserId;

    private String eventUserEvaluation;

    private Double eventUserScore;

    private Integer eventStatus;

    private Date createdAt;

    private Date acceptedAt;

    private Date completedAt;
}