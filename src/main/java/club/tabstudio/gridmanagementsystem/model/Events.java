/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.model;

import java.util.Date;
import java.util.Map;

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

    public void mapToEvents(Map<String,Object> map){
        if (map.containsKey("eventId")){
            this.eventId = String.valueOf(map.get("eventId"));
        }
        if (map.containsKey("eventAreaId")){
            this.eventAreaId = String.valueOf(map.get("eventAreaId"));
        }
        if (map.containsKey("eventAreaAdminId")){
            this.eventAreaAdminId = String.valueOf(map.get("eventAreaAdminId"));
        }
        if (map.containsKey("eventDesc")){
            this.eventDesc = String.valueOf(map.get("eventDesc"));
        }
        if (map.containsKey("eventPosition")){
            this.eventPosition = String.valueOf((map.get("eventPosition")));
        }
        if (map.containsKey("eventAddress")){
            this.eventAddress = String.valueOf(map.get("eventAddress"));
        }
        if (map.containsKey("eventUserId")){
            this.eventUserId = String.valueOf(map.get("eventUserId"));
        }
        if (map.containsKey("eventUserEvaluation")){
            this.eventUserEvaluation = String.valueOf(map.get("eventUserEvaluation"));
        }
        if (map.containsKey("eventUserScore")){
            this.eventUserScore = Double.valueOf(map.get("eventUserScore").toString());
        }
        if (map.containsKey("eventStatus")){
            this.eventStatus = Integer.valueOf(map.get("eventStatus").toString());;
        }

    }
}