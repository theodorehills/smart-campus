package cn.vorbote.smartcampus.vos;

import lombok.Getter;
import lombok.Setter;

/**
 * BaseVo<br>
 * Created at 8/5/2022 3:42 PM
 *
 * @author theod
 */
public sealed abstract class BaseVo permits AdminVo, GradeVo, KlasseVo, StudentVo, TeacherVo {



}
