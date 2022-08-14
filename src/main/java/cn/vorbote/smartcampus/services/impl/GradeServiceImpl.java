package cn.vorbote.smartcampus.services.impl;

import cn.vorbote.smartcampus.mappers.GradeMapper;
import cn.vorbote.smartcampus.pos.Grade;
import cn.vorbote.smartcampus.services.IGradeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vorbote
 * @since 2022-08-05
 */
@Service
@Slf4j
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements IGradeService {

    @Override
    public boolean isGradeExistById(String gradeId) {
        var result = baseMapper.exists(Wrappers.<Grade>lambdaQuery()
                .eq(Grade::getId, gradeId));
        log.info("{}", result);
        return result;
    }
}
