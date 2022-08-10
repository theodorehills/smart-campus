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
import cn.vorbote.smartcampus.requests.DeleteGradeBatchRequest;
import cn.vorbote.smartcampus.services.IGradeService;
import cn.vorbote.smartcampus.services.IKlasseService;
import cn.vorbote.smartcampus.services.ITeacherService;
import cn.vorbote.smartcampus.utils.GuidGenerator;
import cn.vorbote.smartcampus.vos.GradeVo;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.exceptions.BizException;
import cn.vorbote.web.model.ResponseResult;
import cn.vorbote.web.utils.BizAssert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * <p>
 * 前端控制器
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

    private final GuidGenerator guidGenerator;

    @Autowired
    public GradeController(IGradeService gradeService,
                           ITeacherService teacherService,
                           IKlasseService klasseService,
                           GradeConverter gradeConverter,
                           AccessKeyUtil accessKeyUtil,
                           GuidGenerator guidGenerator) {
        this.gradeService = gradeService;
        this.gradeConverter = gradeConverter;
        this.teacherService = teacherService;
        this.klasseService = klasseService;
        this.accessKeyUtil = accessKeyUtil;
        this.guidGenerator = guidGenerator;
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

        Optional.ofNullable(gradeService.getOne(Wrappers.<Grade>lambdaQuery()
                        .eq(Grade::getName, gradeDto.getName())))
                .ifPresent((item) -> {
                    throw new BizException(WebStatus.CONFLICT, "已存在该年级，请勿重复创建！");
                });

        var cnt = teacherService.count(Wrappers.<Teacher>lambdaQuery()
                .eq(Teacher::getId, gradeDto.getManager()));
        if (cnt == 0) {
            throw new BizException(WebStatus.PRECONDITION_FAILED, "绑定的教师不存在！");
        }

        var flag = gradeService.save(
                gradeConverter.toPlain(gradeDto)
                        .setId(guidGenerator.nextGradeId())
                        .setCreateBy(currentUser.getId())
                        .setCreateAt(DateTime.now().unix()));
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
        if (klasseCount > 0) {
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

    @DeleteMapping("/")
    public ResponseResult<?> deleteBatch(@RequestHeader(HeaderConstants.TOKEN_KEY) String token,
                                         @RequestBody DeleteGradeBatchRequest gradeBatchRequest) throws Exception {
        var currentUser = accessKeyUtil.getBean(token, Admin.class);

        var gradeIds = gradeBatchRequest.gradeIds();

        var klassenCount = klasseService.count(Wrappers.<Klasse>lambdaQuery()
                .in(Klasse::getGradeId, gradeIds));
        if (klassenCount >= 0) {
            return ResponseResult.error("这些年级仍然绑定有班级，无法删除")
                    .code(WebStatus.PRECONDITION_FAILED);
        }

        var flag = gradeService.lambdaUpdate()
                .set(Grade::getArchived, ArchiveStatus.ARCHIVED.getCode())
                .set(Grade::getUpdateAt, DateTime.now().unix())
                .set(Grade::getUpdateBy, currentUser.getId())
                .in(Grade::getId, gradeIds)
                .update();
        if (flag) {
            return ResponseResult.success("删除成功！");
        } else {
            return ResponseResult.success("删除失败！")
                    .code(WebStatus.NO_CONTENT);
        }
    }

    @PutMapping("/")
    public ResponseResult<?> updateGrade(@RequestHeader(HeaderConstants.TOKEN_KEY) String token,
                                         @RequestBody GradeDto grade) throws Exception {
        var admin = accessKeyUtil.getBean(token, Admin.class);

        BizAssert.notNull(grade, "年级数据不能为空！");
        BizAssert.hasText(grade.getId(), "年纪数据不能为空！");

        var flag = gradeService.lambdaUpdate()
                .set(Grade::getUpdateBy, admin.getId())
                .set(Grade::getUpdateAt, DateTime.now().unix())
                .eq(Grade::getId, grade.getId())
                .update(gradeConverter.toPlain(grade));
        if (flag) {
            return ResponseResult.success("年级修改成功！");
        } else {
            return ResponseResult.error("年级修改失败！");
        }
    }

    @GetMapping("/{gradeId}")
    public ResponseResult<GradeVo> getGrade(@PathVariable String gradeId) {
        var grade = gradeService.getById(gradeId);
        return ResponseResult.success(
                        Optional.ofNullable(grade)
                                .map(gradeConverter::toView).orElse(null), "查询成功！")
                .code(grade == null ? WebStatus.NO_CONTENT : WebStatus.OK);
    }

}
