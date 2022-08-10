package cn.vorbote.smartcampus.interceptors;

import cn.vorbote.core.time.DateTime;
import cn.vorbote.core.time.TimeSpan;
import cn.vorbote.core.utils.StringUtil;
import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.smartcampus.constants.HeaderConstants;
import cn.vorbote.smartcampus.pos.Admin;
import cn.vorbote.web.model.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * AdminInterceptor<br>
 * Created at 8/5/2022 11:21 PM
 *
 * @author theod
 */
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    private final AccessKeyUtil accessKeyUtil;

    private final ObjectMapper objectMapper;

    public AdminInterceptor(AccessKeyUtil accessKeyUtil, ObjectMapper objectMapper) {
        this.accessKeyUtil = accessKeyUtil;
        this.objectMapper = objectMapper;
    }

    public void write(PrintWriter writer, ResponseResult<?> responseResult) throws JsonProcessingException {
        writer.write(objectMapper.writeValueAsString(responseResult));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 检查是否有令牌
        var token = request.getHeader(HeaderConstants.TOKEN_KEY);
        if (StringUtil.hasText(token)) {
            var result = ResponseResult.unauthorized("您还没有登录，请登录后再试！");
            write(response.getWriter(), result);
            return false;
        }

        // 获取令牌信息
        var decodedJWT = accessKeyUtil.info(token);
        var expiresAt = new DateTime(decodedJWT.getExpiresAt());
        var now = DateTime.now();

        // 验证令牌
        var bean = accessKeyUtil.getBean(token, Admin.class);
        if (bean == null) {
            write(response.getWriter(), ResponseResult.unauthorized("登录信息已过期，请重新登录！"));
            return false;
        }

        // 检查令牌剩余有效期，低于 5 分钟自动续签
        if (now.minus(expiresAt).getMinutes() < 5) {
            var newToken = accessKeyUtil.renewWithBean(token,
                    TimeSpan.builder().minutes(30).build(), Admin.class);
            response.setHeader(HeaderConstants.TOKEN_KEY, newToken);
        }

        return true;
    }
}
