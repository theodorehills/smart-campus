package cn.vorbote.smartcampus.converters;

import cn.vorbote.smartcampus.dtos.StudentDto;
import cn.vorbote.smartcampus.dtos.TeacherDto;
import cn.vorbote.smartcampus.pos.Student;
import cn.vorbote.smartcampus.pos.Teacher;
import cn.vorbote.smartcampus.vos.StudentVo;
import cn.vorbote.smartcampus.vos.TeacherVo;
import org.mapstruct.Mapper;

/**
 * TeacherConverter<br>
 * Created at 8/5/2022 5:22 PM
 *
 * @author theod
 */
@Mapper(componentModel = "spring")
public interface TeacherConverter extends BaseConverter<TeacherDto, Teacher, TeacherVo> {

    /**
     * Convert a Data Transform Object to Plain Object.
     *
     * @param dto a Data Transform Object
     * @return Plain Object
     */
    @Override
    Teacher toPlain(TeacherDto dto);

    /**
     * Convert a Plain Object to View Object.
     *
     * @param po a Plain Object
     * @return View Object
     */
    @Override
    TeacherVo toView(Teacher po);
}
