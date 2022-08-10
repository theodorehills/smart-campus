package cn.vorbote.smartcampus.requests;

import java.util.List;

/**
 * DeleteGradeBatchRequest<br>
 * Created at 10/08/2022 18:45
 *
 * @author theod
 */
public record DeleteGradeBatchRequest(
        List<String> gradeIds) {

}
