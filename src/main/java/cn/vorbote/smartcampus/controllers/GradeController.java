package cn.vorbote.smartcampus.controllers;

import cn.vorbote.core.time.DateTime;
import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.smartcampus.constants.HeaderConstants;
import cn.vorbote.smartcampus.constants.UriConstants;
import cn.vorbote.smartcampus.converters.GradeConverter;
import cn.vorbote.smartcampus.dtos.GradeDto;
import cn.vorbote.smartcampus.enums.ArchiveStatus;
import cn.vorbote.smartcampus.pos.Admin;
import cn.vorbote.smartcampus.pos.Grade;
import cn.vorbote.smartcampus.pos.Klasse;
import cn.vorbote.smartcampus.pos.Teacher;
import cn.vorbote.smartcampus.services.IGradeService;
import cn.vorbote.smartcampus.services.IKlasseService;
import cn.vorbote.smartcampus.services.ITeacherService;
import cn.vorbote.smartcampus.vos.GradeVo;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.exceptions.BizException;
import cn.vorbote.web.model.ResponseResult;
import cn.vorbote.web.utils.BizAssert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vorbote
 * @since 2022-08-05
 */
@Slf4j
@RestController
@RequestMapping(UriConstants.MODULE_GRADE)
public class GradeController {

    private final IGradeService gradeService;

    private final ITeacherService teacherService;

    private final IKlasseService klasseService;

    private final AccessKeyUtil accessKeyUtil;

    private final GradeConverter gradeConverter;

    @Autowired
    public GradeController(IGradeService gradeService,
                           ITeacherService teacherService,
                           IKlasseService klasseService,
                           GradeConverter gradeConverter,
                           AccessKeyUtil accessKeyUtil) {
        this.gradeService = gradeService;
        this.gradeConverter = gradeConverter;
        this.teacherService = teacherService;
        this.klasseService = klasseService;
        this.accessKeyUtil = accessKeyUtil;
    }

    @GetMapping("/list")
    public ResponseResult<IPage<GradeVo>> grades(@RequestParam(defaultValue = "1") Long pageIndex,
                                                 @RequestParam(defaultValue = "10") Long pageSize,
                                                 @RequestParam(required = false) String gradeName) {
        log.info("executing list grades command, pageIndex = {}, pageSize = {}", pageIndex, pageSize);
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

    @PostMapping("/")
    public ResponseResult<?> create(@RequestHeader(HeaderConstants.TOKEN_KEY) String token,
                                    @RequestBody GradeDto gradeDto) throws Exception {
        var currentUser = accessKeyUtil.getBean(token, Admin.class);

        BizAssert.notNull(gradeDto, "年级信息不可以为空！");
        BizAssert.hasText(gradeDto.getName(), "年级名称不可以为空！");

        var cnt = teacherService.count(Wrappers.<Teacher>lambdaQuery()
                .eq(Teacher::getId, gradeDto.getManager()));
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

    @DeleteMapping("/{gradeId}")
    public ResponseResult<?> delete(@RequestHeader(HeaderConstants.TOKEN_KEY) String token,
                                    @PathVariable String gradeId) throws Exception {
        var currentUser = accessKeyUtil.getBean(token, Admin.class);

        // 检查年级
        var gradeCounts = gradeService.count(Wrappers.<Grade>lambdaQuery()
                .eq(Grade::getId, gradeId));
        if (gradeCounts == 0) {
            throw new BizException(WebStatus.PRECONDITION_FAILED, "您需要删除的年级不存在！");
        }

        // 检查班级
        var klasseCount = klasseService.count(Wrappers.<Klasse>lambdaQuery()
                .eq(Klasse::getGradeId, gradeId));
        if (klasseCount == 0) {
            throw new BizException(WebStatus.PRECONDITION_FAILED, "被删除年级绑定了多个班级，请先删除这些班级再进行操作！");
        }

        var flag = gradeService.lambdaUpdate()
                .set(Grade::getArchived, ArchiveStatus.ARCHIVED.getCode())
                .set(Grade::getUpdateBy, currentUser.getId())
                .set(Grade::getUpdateAt, DateTime.now().unix())
                .eq(Grade::getId, gradeId)
                .update();

        if (flag) {
            return ResponseResult.success("年级删除成功！");
        } else {
            return ResponseResult.error("年级删除失败！");
        }
    }


}
