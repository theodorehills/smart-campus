package cn.vorbote.smartcampus.services.impl;

import cn.vorbote.smartcampus.mappers.KlasseMapper;
import cn.vorbote.smartcampus.pos.Klasse;
import cn.vorbote.smartcampus.services.IKlasseService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author vorbote
 * @since 2022-08-05
 */
@Service
public class KlasseServiceImpl extends ServiceImpl<KlasseMapper, Klasse> implements IKlasseService {

    @Override
    public boolean isKlasseNameAvailable(String klasseName) {
        return baseMapper.exists(Wrappers.<Klasse>lambdaQuery()
                .eq(Klasse::getName, klasseName));
    }
}
