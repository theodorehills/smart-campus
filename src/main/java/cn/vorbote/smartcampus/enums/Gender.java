package cn.vorbote.smartcampus.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * Gender<br>
 * Created at 8/5/2022 4:59 PM
 *
 * @author theod
 */
@Getter
public enum Gender {
    FEMALE(0, "女性"),
    MALE(1, "男性"),
    UNKNOWN(10, "未知");

    private final int code;
    private final String label;

    Gender(int code, String label) {
        this.code = code;
        this.label = label;
    }

    /**
     * 根据 code 查找枚举值
     *
     * @param code 枚举code
     * @return 找到的枚举值，未找到返回 UNKNOWN
     */
    public static Gender byCode(Integer code) {
        return Arrays.stream(values())
                .filter((item) -> Objects.equals(item.code, code))
                .findFirst()
                .orElse(UNKNOWN);
    }

    /**
     * 根据 label 查找枚举值
     *
     * @param label 枚举label
     * @return 找到的枚举值，未找到返回 UNKNOWN
     */
    public static Gender byLabel(String label) {
        return Arrays.stream(values())
                .filter((item) -> Objects.equals(item.label, label))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
