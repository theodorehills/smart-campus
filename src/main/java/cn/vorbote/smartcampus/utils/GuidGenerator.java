package cn.vorbote.smartcampus.utils;

import cn.vorbote.core.utils.SnowFlake;
import org.springframework.stereotype.Component;

/**
 * GuidGenerator<br>
 * Created at 8/7/2022 8:17 AM
 *
 * @author theod
 */
@Component
public class GuidGenerator {

    private final SnowFlake snowFlake;

    public GuidGenerator(SnowFlake snowFlake) {
        this.snowFlake = snowFlake;
    }

    /**
     * 生成一个教师ID
     *
     * @return 教师ID
     */
    public String nextTeacherId() {
        return nextId("TEC");
    }

    /**
     * 生成一个年级ID
     *
     * @return 年级ID
     */
    public String nextGradeId() {
        return nextId("GRA");
    }

    /**
     * 生成一个管理员ID
     *
     * @return 管理员ID
     */
    public String nextAdminId() {
        return nextId("ADM");
    }

    /**
     * 生成一个班级ID
     *
     * @return 班级ID
     */
    public String nextKlasseId() {
        return nextId("CLS");
    }

    /**
     * 生成一个学生ID
     *
     * @return 学生ID
     */
    public String nextStudentId() {
        return nextId("STU");
    }

    private String nextId(String prefix) {
        return String.format("%s%d", prefix, snowFlake.nextId());
    }
}
