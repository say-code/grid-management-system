/*
 * @Author: wangyihan
 */

/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author 994914269
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("club.tabstudio.gridmanagementsystem.mapper")
public class GridManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(GridManagementSystemApplication.class, args);
    }

}
