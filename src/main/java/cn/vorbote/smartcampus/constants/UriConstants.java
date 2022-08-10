package cn.vorbote.smartcampus.constants;

import cn.vorbote.smartcampus.enums.Modules;

/**
 * UriConstants<br>
 * Created at 8/7/2022 4:49 PM
 *
 * @author theod
 */
public final class UriConstants {

    private UriConstants() {
    }

    public static final String MODULE_ADMIN = "/admin";
    public static final String MODULE_GRADE = "/grade";
    public static final String MODULE_KLASSE = "/klasse";
    public static final String MODULE_STUDENT = "/student";
    public static final String MODULE_TEACHER = "/teacher";
    public static final String MODULE_SYSTEM = "/system";

    public static String getPattern(Modules module) {
        return module.getPattern();
    }


}
