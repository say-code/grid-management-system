/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem;

import club.tabstudio.gridmanagementsystem.model.Events;
import club.tabstudio.gridmanagementsystem.service.IEventsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 报事事项 测试类
 * @author wangyihan
 */
@SpringBootTest
public class EventServiceTests {

    @Autowired
    private IEventsService eventsService;

    @Test
    public void selectAll() {
        List<Events> events = eventsService.selectWithAllRelation(null);
        for (Events event: events) {
            System.out.println(event.getUser().getName());
        }
    }
}
