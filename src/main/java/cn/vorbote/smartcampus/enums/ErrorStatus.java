package cn.vorbote.smartcampus.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * ErrorStatus<br>
 * Created at 8/5/2022 5:36 PM
 *
 * @author theod
 */
@Getter
public enum ErrorStatus {

    BAD_REQUEST(400, "错误的请求语法、无效的请求消息帧或欺骗性的请求路由！"),
    UNAUTHORIZED(401, "您还没有登录！"),
    PAYMENT_REQUIRED(402, "需要验证！"),
    FORBIDDEN(403, "您没有访问内容的权限！"),
    NOT_FOUND(404, "找不到请求的资源"),
    METHOD_NOT_ALLOWED(405, "目标资源不支持该方法。"),
    NOT_ACCEPTABLE(406, "没有发现任何符合用户代理给定标准的内容"),
    PROXY_AUTHENTICATION_REQUIRED(407, "代理未完成身份认证！"),
    REQUEST_TIMEOUT(408, "请求超时！"),
    CONFLICT(409, "请求与服务器的当前状态冲突！"),
    GONE(410, "请求的内容已从服务器中永久删除且没有转发地址！"),
    LENGTH_REQUIRED(411, "Content-Length 头部字段未定义！"),
    PRECONDITION_FAILED(412, "请求头中指出了服务器不满足条件！"),
    REQUEST_ENTITY_TOO_LARGE(413, "请求实体大于服务器定义的限制！"),
    REQUEST_URI_TOO_LONG(414, "URI 长度超过限制！"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持请求数据的媒体格式！"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "无法满足请求中 Range 标头字段指定的范围！"),
    EXPECTATION_FAILED(417, "服务器无法满足 Expect 请求标头字段所指示的期望！"),
    INTERNAL_SERVER_ERROR(500, "服务器遇到了不知道如何处理的情况！"),
    NOT_IMPLEMENTED(501, "服务器不支持该请求方法！"),
    BAD_GATEWAY(502, "服务器作为网关得到了错误的相应内容！"),
    SERVICE_UNAVAILABLE(503, "没有准备好处理请求！"),
    GATEWAY_TIMEOUT(504, "服务器无法及时获得响应！"),
    HTTP_VERSION_NOT_SUPPORTED(505, "服务器不支持请求中使用的 HTTP 版本！"),
    TIMEOUT(600, "请求超时！");

    private final int code;

    private final String label;

    ErrorStatus(int code, String label) {
        this.code = code;
        this.label = label;
    }

    /**
     * 根据 code 查找枚举值
     *
     * @param code 枚举code
     * @return 找到的枚举值，未找到返回 INTERNAL_SERVER_ERROR
     */
    public static ErrorStatus byCode(Integer code) {
        return Arrays.stream(values())
                .filter((item) -> Objects.equals(item.code, code))
                .findFirst()
                .orElse(INTERNAL_SERVER_ERROR);
    }

    /**
     * 根据 label 查找枚举值
     *
     * @param label 枚举label
     * @return 找到的枚举值，未找到返回 INTERNAL_SERVER_ERROR
     */
    public static ErrorStatus byLabel(String label) {
        return Arrays.stream(values())
                .filter((item) -> Objects.equals(item.label, label))
                .findFirst()
                .orElse(INTERNAL_SERVER_ERROR);
    }
    
}
