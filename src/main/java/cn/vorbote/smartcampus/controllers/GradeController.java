package cn.vorbote.smartcampus.controllers;

import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.smartcampus.constants.HeaderConstants;
import cn.vorbote.smartcampus.converters.GradeConverter;
import cn.vorbote.smartcampus.dtos.GradeDto;
import cn.vorbote.smartcampus.pos.Admin;
import cn.vorbote.smartcampus.pos.Grade;
import cn.vorbote.smartcampus.pos.Teacher;
import cn.vorbote.smartcampus.services.IGradeService;
import cn.vorbote.smartcampus.services.ITeacherService;
import cn.vorbote.smartcampus.services.impl.GradeServiceImpl;
import cn.vorbote.smartcampus.services.impl.TeacherServiceImpl;
import cn.vorbote.smartcampus.vos.GradeVo;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.exceptions.BizException;
import cn.vorbote.web.model.ResponseResult;
import cn.vorbote.web.utils.BizAssert;
import com.auth0.jwt.interfaces.Header;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vorbote
 * @since 2022-08-05
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    private final IGradeService gradeService;

    private final ITeacherService teacherService;

    private final AccessKeyUtil accessKeyUtil;

    private final GradeConverter gradeConverter;

    @Autowired
    public GradeController(IGradeService gradeService,
                           ITeacherService teacherService,
                           GradeConverter gradeConverter,
                           AccessKeyUtil accessKeyUtil) {
        this.gradeService = gradeService;
        this.gradeConverter = gradeConverter;
        this.teacherService = teacherService;
        this.accessKeyUtil = accessKeyUtil;
    }

    @GetMapping("/list")
    public ResponseResult<IPage<GradeVo>> grades(@RequestParam(defaultValue = "1") Long pageIndex,
                                                 @RequestParam(defaultValue = "10") Long pageSize,
                                                 @RequestParam(required = false) String gradeName) {
        var wrapper = Wrappers.<Grade>lambdaQuery()
                .like(Grade::getName, Optional.ofNullable(gradeName).orElse(""));

        var page = gradeService.page(new Page<>(pageIndex, pageSize), wrapper)
                .convert(gradeConverter::toView);
        var result = ResponseResult.success(page, "查询成功！");

        if (page.getRecords().size() == 0) {
            result.code(WebStatus.NO_CONTENT);
        }
        return result;
    }

    @PostMapping("/new")
    public ResponseResult<?> create(@RequestHeader(HeaderConstants.TOKEN_KEY) String token,
                                    @RequestBody GradeDto gradeDto) throws Exception {
        var currentUser = accessKeyUtil.getBean(token, Admin.class);

        BizAssert.notNull(gradeDto, "年级信息不可以为空！");
        BizAssert.hasText(gradeDto.getName(), "年级名称不可以为空！");

        var cnt = teacherService.count(Wrappers.<Teacher>lambdaQuery()
                .eq(Teacher::getId, gradeDto.getId()));
        if (cnt == 0) {
            throw new BizException(WebStatus.PRECONDITION_FAILED, "绑定的教师不存在！");
        }

        var flag = gradeService.save(gradeConverter.toPlain(gradeDto)
                .setCreateBy(currentUser.getId()));
        if (flag) {
            return ResponseResult.success("新增年级成功！");
        } else {
            return ResponseResult.error("新增年级失败！");
        }
    }


}
