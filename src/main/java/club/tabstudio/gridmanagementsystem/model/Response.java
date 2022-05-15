/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyihan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Integer code;

    private Object data;

    private String msg;

    public static Response success() {
        return new Response(0, null, "操作成功");
    }

    public static Response success(Object data) {
        return new Response(0, data, "操作成功");
    }

    public static Response error() {
        return new Response(-1, null, "操作失败");
    }

    public static Response error(String msg) {
        return new Response(-1, null, msg);
    }

    public static Response error(String msg, Integer code) {
        return new Response(code, null, msg);
    }
}
