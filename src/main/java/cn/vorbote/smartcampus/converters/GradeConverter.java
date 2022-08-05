package cn.vorbote.smartcampus.converters;

import cn.vorbote.smartcampus.dtos.AdminDto;
import cn.vorbote.smartcampus.dtos.GradeDto;
import cn.vorbote.smartcampus.pos.Admin;
import cn.vorbote.smartcampus.pos.Grade;
import cn.vorbote.smartcampus.vos.AdminVo;
import cn.vorbote.smartcampus.vos.GradeVo;
import org.mapstruct.Mapper;

/**
 * AdminConverter<br>
 * Created at 8/5/2022 5:17 PM
 *
 * @author theod
 */
@Mapper(componentModel = "spring")
public abstract class GradeConverter implements BaseConverter<GradeDto, Grade, GradeVo> {

    /**
     * Convert a Data Transform Object to Plain Object.
     *
     * @param dto a Data Transform Object
     * @return Plain Object
     */
    @Override
    public abstract Grade toPlain(GradeDto dto);

    /**
     * Convert a Plain Object to View Object.
     *
     * @param po a Plain Object
     * @return View Object
     */
    @Override
    public abstract GradeVo toView(Grade po);
}
