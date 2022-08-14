package cn.vorbote.smartcampus.controllers;

import cn.vorbote.core.time.DateTime;
import cn.vorbote.core.utils.StringUtil;
import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.smartcampus.constants.HeaderConstants;
import cn.vorbote.smartcampus.constants.UriConstants;
import cn.vorbote.smartcampus.converters.KlasseConverter;
import cn.vorbote.smartcampus.dtos.KlasseDto;
import cn.vorbote.smartcampus.pos.Admin;
import cn.vorbote.smartcampus.pos.Grade;
import cn.vorbote.smartcampus.pos.Klasse;
import cn.vorbote.smartcampus.requests.KlasseRequest;
import cn.vorbote.smartcampus.services.IGradeService;
import cn.vorbote.smartcampus.services.IKlasseService;
import cn.vorbote.smartcampus.utils.GuidGenerator;
import cn.vorbote.smartcampus.vos.KlasseVo;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.model.ResponseResult;
import cn.vorbote.web.utils.BizAssert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author vorbote
 * @since 2022-08-05
 */
@RestController
@RequestMapping(UriConstants.MODULE_KLASSE)
public class KlasseController {

    private final IKlasseService klasseService;

    private final IGradeService gradeService;

    private final KlasseConverter klasseConverter;

    private final AccessKeyUtil accessKeyUtil;

    private final GuidGenerator guidGenerator;

    @Autowired
    public KlasseController(IKlasseService klasseService,
                            IGradeService gradeService,
                            KlasseConverter klasseConverter,
                            AccessKeyUtil accessKeyUtil,
                            GuidGenerator guidGenerator) {
        this.klasseService = klasseService;
        this.klasseConverter = klasseConverter;
        this.accessKeyUtil = accessKeyUtil;
        this.gradeService = gradeService;
        this.guidGenerator = guidGenerator;
    }

    @GetMapping("/list")
    public ResponseResult<IPage<KlasseVo>> list(@RequestParam(defaultValue = "1") Long pageIndex,
                                                @RequestParam(defaultValue = "10") Long pageSize,
                                                @RequestBody KlasseRequest klasseRequest) {
        var wrapper = Wrappers.<Klasse>lambdaQuery();
        if (StringUtil.hasText(klasseRequest.name()))
            wrapper.like(Klasse::getName, klasseRequest.name());
        if (klasseRequest.gradeIds() != null && klasseRequest.gradeIds().size() > 0) {
            wrapper.in(Klasse::getGradeId, klasseRequest.gradeIds());
        }

        var data = klasseService.page(new Page<>(pageIndex, pageSize), wrapper).convert(klasseConverter::toView);

        return ResponseResult.success(data, "查询成功！")
                .code(data.getRecords().size() > 0 ? WebStatus.OK : WebStatus.NO_CONTENT);
    }

    @GetMapping("/{klasseId}")
    public ResponseResult<KlasseVo> queryKlasse(@PathVariable String klasseId) {
        var klasse = klasseService.getById(klasseId);
        return ResponseResult.success(klasseConverter.toView(klasse), "查询成功！")
                .code(klasse != null ? WebStatus.OK : WebStatus.NO_CONTENT);
    }

    @PostMapping("/")
    public ResponseResult<?> createKlasse(@RequestHeader(HeaderConstants.TOKEN_KEY) String token,
                                          @RequestBody KlasseDto klasseDto) throws Exception {
        var admin = accessKeyUtil.getBean(token, Admin.class);
        BizAssert.notNull(klasseDto, "班级数据不能为空！");
        BizAssert.hasText(klasseDto.getName(), "班级名称不能为空！");
        BizAssert.hasText(klasseDto.getGradeId(), "年级不能为空！");
        BizAssert.hasText(klasseDto.getHeadmaster(), "班主任不能为空！");
        BizAssert.notNull(klasseDto.getNumber(), "班级人数不能为空！");
        BizAssert.isTrue(klasseDto.getNumber() > 0, "班级人数错误！");

        // 检测年级是否存在
        if (gradeService.isGradeExistById(klasseDto.getGradeId())) {
            return ResponseResult.error("班级所绑定的年级不存在，请重新创建！")
                    .code(WebStatus.PRECONDITION_FAILED);
        }

        // 检测班级名称是否重复
        if (klasseService.isKlasseNameAvailable(klasseDto.getName())) {
            return ResponseResult.error("班级名称重复！无法创建！")
                    .code(WebStatus.CONFLICT);
        }

        var flag = klasseService.save(klasseConverter.toPlain(klasseDto)
                .setId(guidGenerator.nextKlasseId())
                .setCreateBy(admin.getId())
                .setCreateAt(DateTime.now().unix()));
        if (flag) {
            return ResponseResult.success("创建班级成功！");
        } else {
            return ResponseResult.error("创建班级失败，请联系开发者！");
        }
    }

    @PutMapping("/")
    public ResponseResult<?> updateKlasse(@RequestHeader(HeaderConstants.TOKEN_KEY) String token,
                                          @RequestBody KlasseDto klasseDto) throws Exception {
        var admin = accessKeyUtil.getBean(token, Admin.class);

        BizAssert.notNull(klasseDto, "班级数据不能为空！");
        BizAssert.hasText(klasseDto.getId(), "班级不能为空！");

        if (StringUtil.hasText(klasseDto.getGradeId())) {
            if (gradeService.isGradeExistById(klasseDto.getGradeId())) {
                return ResponseResult.error("班级所绑定的年级不存在，请重新创建！")
                        .code(WebStatus.PRECONDITION_FAILED);
            }
        }

        var flag = klasseService.lambdaUpdate()
                .set(Klasse::getUpdateBy, admin.getId())
                .set(Klasse::getUpdateAt, DateTime.now().unix())
                .eq(Klasse::getId, klasseDto.getId())
                .update(klasseConverter.toPlain(klasseDto));
        if (flag) {
            return ResponseResult.success("修改成功！");
        } else {
            return ResponseResult.error("修改失败！");
        }
    }



}
