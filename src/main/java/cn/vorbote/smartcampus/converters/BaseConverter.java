package cn.vorbote.smartcampus.converters;

import cn.vorbote.smartcampus.dtos.BaseDto;
import cn.vorbote.smartcampus.pos.BasePo;
import cn.vorbote.smartcampus.vos.BaseVo;

/**
 * BaseConverter<br>
 * Created at 8/5/2022 3:44 PM
 *
 * @param <D> Specify the dto class.
 * @param <P> Specify the po class.
 * @param <V> Specify the vo class.
 * @author theod
 */
public interface BaseConverter
        <D extends BaseDto, P extends BasePo, V extends BaseVo> {

    /**
     * Convert a Data Transform Object to Plain Object.
     *
     * @param dto a Data Transform Object
     * @return Plain Object
     */
    P toPlain(D dto);

    /**
     * Convert a Plain Object to View Object.
     *
     * @param po a Plain Object
     * @return View Object
     */
    V toView(P po);

}
