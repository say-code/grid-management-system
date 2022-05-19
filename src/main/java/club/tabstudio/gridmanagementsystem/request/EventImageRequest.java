/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.request;

import club.tabstudio.gridmanagementsystem.model.Events;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventImageRequest extends Events {

    private List<String> eventImageIdList;
}
