package cn.vorbote.smartcampus.services.impl;

import cn.vorbote.smartcampus.pos.Admin;
import cn.vorbote.smartcampus.mappers.AdminMapper;
import cn.vorbote.smartcampus.services.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
