package cn.vorbote.smartcampus.configurations;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlusConfig<br>
 * Created at 8/5/2022 3:49 PM
 *
 * @author theod
 */
@Configuration
@MapperScan("cn.vorbote.smartcampus.mappers")
public class MybatisPlusConfig {
}
