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

import javax.validation.constraints.PastOrPresent;
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

    @PastOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

}
