package cn.vorbote.smartcampus.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * BaseDto<br>
 * Created at 8/5/2022 3:42 PM
 *
 * @author theod
 */
@Getter
@Setter
public sealed abstract class BaseDto permits AdminDto, GradeDto, KlasseDto, StudentDto, TeacherDto {

}
