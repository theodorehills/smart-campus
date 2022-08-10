package cn.vorbote.smartcampus.enums;

/**
 * Modules<br>
 * Created at 10/08/2022 17:29
 *
 * @author theod
 */
public enum Modules {

    ADMIN("/admin"),
    GRADE("/grade"),
    KLASSE("/klasse"),
    STUDENT("/student"),
    TEACHER("/teacher"),
    SYSTEM("/system");

    private final String uri;

    Modules(String uri) {
        this.uri = uri;
    }

    public final String getUri() {
        return uri;
    }

    public final String getPattern() {
        return getUri() + "/**";
    }
}
