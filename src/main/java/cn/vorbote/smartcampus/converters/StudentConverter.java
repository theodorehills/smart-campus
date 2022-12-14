package cn.vorbote.smartcampus.converters;

import cn.vorbote.smartcampus.dtos.StudentDto;
import cn.vorbote.smartcampus.pos.Student;
import cn.vorbote.smartcampus.vos.StudentVo;
import org.mapstruct.Mapper;

/**
 * StudentConverter<br>
 * Created at 8/5/2022 5:21 PM
 *
 * @author theod
 */
@Mapper(componentModel = "spring")
public abstract class StudentConverter implements BaseConverter<StudentDto, Student, StudentVo> {

    /**
     * Convert a Data Transform Object to Plain Object.
     *
     * @param dto a Data Transform Object
     * @return Plain Object
     */
    @Override
    public abstract Student toPlain(StudentDto dto);

    /**
     * Convert a Plain Object to View Object.
     *
     * @param po a Plain Object
     * @return View Object
     */
    @Override
    public abstract StudentVo toView(Student po);
}
