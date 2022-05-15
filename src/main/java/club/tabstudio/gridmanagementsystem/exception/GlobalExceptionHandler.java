/*
 * @Author: wangyihan
 */

package club.tabstudio.gridmanagementsystem.exception;

import club.tabstudio.gridmanagementsystem.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Response> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Response.error("您无权限访问", 401));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Response> handleBizException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Response.error());
    }
}

