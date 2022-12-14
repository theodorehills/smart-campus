package cn.vorbote.smartcampus.controllers;

import cn.vorbote.core.constants.Hash;
import cn.vorbote.core.time.TimeSpan;
import cn.vorbote.core.utils.HashUtil;
import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.smartcampus.constants.HeaderConstants;
import cn.vorbote.smartcampus.constants.UriConstants;
import cn.vorbote.smartcampus.converters.AdminConverter;
import cn.vorbote.smartcampus.dtos.AdminDto;
import cn.vorbote.smartcampus.enums.ErrorStatus;
import cn.vorbote.smartcampus.pos.Admin;
import cn.vorbote.smartcampus.services.IAdminService;
import cn.vorbote.smartcampus.vos.AdminVo;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.model.ResponseResult;
import cn.vorbote.web.utils.BizAssert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vorbote
 * @since 2022-08-05
 */
@RestController
@RequestMapping(UriConstants.MODULE_ADMIN)
public class AdminController {

    private final AccessKeyUtil accessKeyUtil;

    private final IAdminService adminService;

    private final AdminConverter adminConverter;

    @Autowired
    public AdminController(IAdminService adminService,
                           AccessKeyUtil accessKeyUtil,
                           AdminConverter adminConverter) {
        this.adminService = adminService;
        this.accessKeyUtil = accessKeyUtil;
        this.adminConverter = adminConverter;
    }

    /**
     * 管理员登录功能
     */
    @PostMapping("/login")
    public ResponseResult<AdminVo> login(HttpServletResponse response,
                                         @RequestBody AdminDto adminDto) throws Exception {
        // 检查数据
        BizAssert.notNull(adminDto, "管理员信息不能为空！");
        BizAssert.hasText(adminDto.getUsername(), "管理员名称不能为空！");
        BizAssert.hasText(adminDto.getPassword(), "管理员密码不能为空！");

        // 查找数据库
        var admin = adminService.getOne(Wrappers.<Admin>lambdaQuery()
                .eq(Admin::getUsername, adminDto.getUsername())
                .eq(Admin::getPassword, HashUtil.encrypt(Hash.MD5, adminDto.getPassword())));
        if (admin != null) {
            // 生成令牌
            var token = accessKeyUtil.createTokenWithBean(
                    TimeSpan.builder().hours(1).build(),
                    "管理员", new String[]{admin.getId()},
                    admin);

            // 写入响应头
            response.setHeader(HeaderConstants.TOKEN_KEY, token);

            // 返回数据
            return ResponseResult.success(adminConverter.toView(admin), "登录成功！");
        } else {
            return ResponseResult.<AdminVo>error("用户名账户密码不匹配，请重新登录！")
                    .code(WebStatus.PAYMENT_REQUIRED);
        }
    }

}
