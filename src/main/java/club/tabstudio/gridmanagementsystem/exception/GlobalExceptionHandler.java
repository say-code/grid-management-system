/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.exception;

import club.tabstudio.gridmanagementsystem.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author wangyihan
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response exceptionHandler(MethodArgumentNotValidException e)
    {
        return Response.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler({Exception.class})
    public Response handleBizException(Exception ex) {
        log.error(ex.getMessage());
        return Response.error();
    }
}

