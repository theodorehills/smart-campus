package cn.vorbote.smartcampus.vos;

/**
 * BaseVo<br>
 * Created at 8/5/2022 3:42 PM
 *
 * @author theod
 */
public sealed abstract class BaseVo permits AdminVo, GradeVo, KlasseVo, StudentVo, TeacherVo {

    protected String id;

}