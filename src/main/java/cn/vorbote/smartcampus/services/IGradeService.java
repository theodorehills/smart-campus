package cn.vorbote.smartcampus.services;

import cn.vorbote.smartcampus.pos.Grade;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vorbote
 * @since 2022-08-05
 */
public interface IGradeService extends IService<Grade> {

    public boolean isGradeExistById(String gradeId);

}
