package cn.vorbote.smartcampus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * MybatisPlusGenerator<br>
 * Created at 8/5/2022 4:42 PM
 *
 * @author theod
 */
public class MybatisPlusGenerator {

    public static void main(String... args) {
        FastAutoGenerator.create("jdbc:mysql://111.230.187.250:24094/smart_campus?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false",
                        "root", "200927CysDECN")
                .globalConfig(builder -> {
                    builder.author("vorbote")
                            .outputDir("D://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.vorbote.smartcampus.pos")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addTablePrefix("sc_"); // 设置过滤表前缀
                })
                .execute();
    }
}
