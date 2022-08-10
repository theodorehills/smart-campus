package cn.vorbote.smartcampus.requests;

import java.util.List;

/**
 * QueryKlasseListRequest<br>
 * Created at 11/08/2022 03:32
 *
 * @author theod
 */
public record KlasseRequest(
        String name,
        List<String> gradeIds) {
}
