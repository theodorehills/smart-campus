package cn.vorbote.smartcampus.controllers;

import cn.vorbote.smartcampus.converters.GradeConverter;
import cn.vorbote.smartcampus.pos.Grade;
import cn.vorbote.smartcampus.services.IGradeService;
import cn.vorbote.smartcampus.services.impl.GradeServiceImpl;
import cn.vorbote.smartcampus.vos.GradeVo;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.model.ResponseResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    private final GradeConverter gradeConverter;

    @Autowired
    public GradeController(IGradeService gradeService,
                           GradeConverter gradeConverter) {
        this.gradeService = gradeService;
        this.gradeConverter = gradeConverter;
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


}
