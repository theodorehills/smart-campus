package cn.vorbote.smartcampus.configurations;

import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.smartcampus.interceptors.AdminInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * InterceptorConfig<br>
 * Created at 8/5/2022 11:24 PM
 *
 * @author theod
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final AccessKeyUtil accessKeyUtil;

    private final ObjectMapper objectMapper;

    public InterceptorConfig(AccessKeyUtil accessKeyUtil, ObjectMapper objectMapper) {
        this.accessKeyUtil = accessKeyUtil;
        this.objectMapper = objectMapper;
    }

    private final List<String> EXCLUDE_PATHS = Arrays.asList(
            "/error",
            "/**/login",
            "/**/sign-up",
            "/**/register",
            "/demo/**/*"
    );

    @Bean
    public AdminInterceptor adminInterceptor() {
        return new AdminInterceptor(accessKeyUtil, objectMapper);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }
}
