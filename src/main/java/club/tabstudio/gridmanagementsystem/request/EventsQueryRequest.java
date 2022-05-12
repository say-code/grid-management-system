/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.request;

import club.tabstudio.gridmanagementsystem.model.Events;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangyihan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventsQueryRequest extends Events {
    private String eventAreaAdminName;

    private String eventUserName;

    private String eventAreaName;

    private int timeStatus = 0;

    @JsonFormat(pattern = "yyyy-MM")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM")
    private Date endTime;

//    public void timeStatusCheck() {
//        if (endTimeDate != null) {
//            this.timeStatus = 3;
//            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//            try {
//                this.startTime = format.parse(startTimeYear + startTimeMonth + startTimeDate);
//                this.endTime = format.parse(endTimeYear + endTimeMonth + endTimeDate);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        } else if (endTimeMonth != null) {
//            this.timeStatus = 2;
//            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
//            try {
//                this.startTime = format.parse(startTimeYear + startTimeMonth);
//                this.endTime = format.parse(endTimeYear + endTimeMonth);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        } else if (endTimeYear != null) {
//            this.timeStatus = 1;
//            SimpleDateFormat format = new SimpleDateFormat("yyyy");
//            try {
//                this.startTime = format.parse(startTimeYear);
//                this.endTime = format.parse(endTimeYear);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
