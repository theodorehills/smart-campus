package cn.vorbote.smartcampus.exceptions.handlers;

import cn.vorbote.smartcampus.exceptions.NotImplementedException;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.exceptions.BizException;
import cn.vorbote.web.model.ResponseResult;
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

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult<?> handle(Exception ex) {
        return ResponseResult.error("出现异常，请联系开发者！")
                .code(WebStatus.INTERNAL_SERVER_ERROR);
    }

}
