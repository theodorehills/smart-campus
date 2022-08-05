package cn.vorbote.smartcampus.converters;

import cn.vorbote.smartcampus.dtos.AdminDto;
import cn.vorbote.smartcampus.pos.Admin;
import cn.vorbote.smartcampus.vos.AdminVo;
import org.mapstruct.Mapper;

/**
 * AdminConverter<br>
 * Created at 8/5/2022 5:17 PM
 *
 * @author theod
 */
@Mapper(componentModel = "spring")
public interface AdminConverter extends BaseConverter<AdminDto, Admin, AdminVo> {

    /**
     * Convert a Data Transform Object to Plain Object.
     *
     * @param dto a Data Transform Object
     * @return Plain Object
     */
    @Override
    Admin toPlain(AdminDto dto);

    /**
     * Convert a Plain Object to View Object.
     *
     * @param po a Plain Object
     * @return View Object
     */
    @Override
    AdminVo toView(Admin po);
}
