package cn.vorbote.smartcampus.configurations;

import cn.vorbote.core.utils.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SnowFlakeConfig<br>
 * Created at 8/5/2022 8:26 PM
 *
 * @author theod
 */
@Configuration
public class SnowFlakeConfig {

    @Bean
    public SnowFlake snowFlake() {
        return new SnowFlake(0, 0);
    }

}
