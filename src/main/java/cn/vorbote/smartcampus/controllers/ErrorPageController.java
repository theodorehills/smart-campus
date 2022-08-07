package cn.vorbote.smartcampus.controllers;

import cn.vorbote.core.time.DateTime;
import cn.vorbote.smartcampus.enums.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ErrorPageController<br>
 * Created at 8/5/2022 5:31 PM
 *
 * @author theod
 */
@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorPageController extends BasicErrorController {

    public ErrorPageController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        var status = this.getStatus(request);

        log.info("Error occurred, web status is {}", status.value());

        var result = new HashMap<String, Object>() {{
            put("code", status.value());
            put("message", ErrorStatus.byCode(status.value()).getLabel());
            put("data", "");
            put("timestamp", DateTime.now().unix());
        }};

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
