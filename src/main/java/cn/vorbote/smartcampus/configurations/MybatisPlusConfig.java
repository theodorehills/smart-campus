package cn.vorbote.smartcampus.configurations;

import cn.vorbote.core.time.DateTime;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.setFieldValByName("createAt", DateTime.now().unix(), metaObject);
                this.setFieldValByName("createBy", "SYSTEM", metaObject);
                this.setFieldValByName("archived", 0, metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("updateAt", DateTime.now().unix(), metaObject);
            }
        };
    }

}
