package cn.vorbote.smartcampus.services.impl;

import cn.vorbote.smartcampus.mappers.StudentMapper;
import cn.vorbote.smartcampus.pos.Student;
import cn.vorbote.smartcampus.services.IStudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
