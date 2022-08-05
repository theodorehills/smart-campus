package cn.vorbote.smartcampus.converters;

import cn.vorbote.smartcampus.dtos.AdminDto;
import cn.vorbote.smartcampus.dtos.KlasseDto;
import cn.vorbote.smartcampus.pos.Admin;
import cn.vorbote.smartcampus.pos.Klasse;
import cn.vorbote.smartcampus.vos.AdminVo;
import cn.vorbote.smartcampus.vos.KlasseVo;
import org.mapstruct.Mapper;

/**
 * AdminConverter<br>
 * Created at 8/5/2022 5:17 PM
 *
 * @author theod
 */
@Mapper(componentModel = "spring")
public abstract class KlasseConverter implements BaseConverter<KlasseDto, Klasse, KlasseVo> {

    /**
     * Convert a Data Transform Object to Plain Object.
     *
     * @param dto a Data Transform Object
     * @return Plain Object
     */
    @Override
    public abstract Klasse toPlain(KlasseDto dto);

    /**
     * Convert a Plain Object to View Object.
     *
     * @param po a Plain Object
     * @return View Object
     */
    @Override
    public abstract KlasseVo toView(Klasse po);
}
