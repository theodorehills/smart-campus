package cn.vorbote.smartcampus.exceptions.handlers;

import cn.vorbote.smartcampus.exceptions.NotImplementedException;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.exceptions.BizException;
import cn.vorbote.web.model.ResponseResult;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GlobalExceptionHandler<br>
 * Created at 8/5/2022 5:26 PM
 *
 * @author theod
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotImplementedException.class)
    @ResponseBody
    public ResponseResult<?> handle(NotImplementedException ex) {
        return ResponseResult.error("该接口尚未实现！")
                .code(WebStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResponseResult<?> handle(BizException ex) {
        return ex.respond();
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public ResponseResult<?> handle(TokenExpiredException ex) {
        return ResponseResult.error("您的认证信息已过期，请重新登录！")
                .code(WebStatus.PAYMENT_REQUIRED);
    }
}
