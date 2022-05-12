/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */


package club.tabstudio.gridmanagementsystem.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private String eventAreaAdminName;

    private String eventUserName;

    private String eventAreaName;

    private String startTimeYear;

    private String startTimeMonth;

    private String startTimeDate;

    private String endTimeYear;

    private String endTimeMonth;

    private String endTimeDate;

    private int timeStatus = 0;

    private Date startTime;

    private Date endTime;

    public void timeStatusCheck(){
        if ( endTimeDate != null){
            this.timeStatus = 3;
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            try {
                this.startTime = format.parse(startTimeYear+startTimeMonth+startTimeDate);
                this.endTime = format.parse(endTimeYear+endTimeMonth+endTimeDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if ( endTimeMonth != null){
            this.timeStatus = 2;
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            try {
                this.startTime = format.parse(startTimeYear+startTimeMonth);
                this.endTime = format.parse(endTimeYear+endTimeMonth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if ( endTimeYear != null){
            this.timeStatus = 1;
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            try {
                this.startTime = format.parse(startTimeYear);
                this.endTime = format.parse(endTimeYear);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


    }
