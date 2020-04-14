package com.iviui.platform.framework.exception;

import com.iviui.platform.framework.utils.ResultVO;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ChengPan
 * @Description:
 */

@RestControllerAdvice
public class RRExceptionHandler {
    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResultVO handle401(ShiroException e) {
        if(e.getMessage() ==null){
            System.out.println("shiro异常信息为空");
        }else{
            System.out.println("shiro----->"+e.getMessage());
        }
        return new ResultVO(401, e.getMessage(), null);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    public ResultVO handle401(UnauthorizedException e) {
        if(e.getMessage() ==null){
            System.out.println("UnauthorizedException异常信息为空");
        }else{
            System.out.println("UnauthorizedException------->"+e.getMessage());
        }
        return new ResultVO(401, e.getMessage(), null);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResultVO handleAuthorizationException(AuthorizationException e) {
        return new ResultVO(401,"请联系管理员授权后,登陆操作");
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO globalException(HttpServletRequest request, Throwable ex) {
        return new ResultVO(getStatus(request).value(), ex.getMessage(), null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
