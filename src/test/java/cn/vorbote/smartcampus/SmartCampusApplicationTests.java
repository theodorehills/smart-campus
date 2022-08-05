package cn.vorbote.smartcampus;

import cn.vorbote.core.constants.Hash;
import cn.vorbote.core.utils.HashUtil;
import cn.vorbote.core.utils.SnowFlake;
import cn.vorbote.smartcampus.enums.Gender;
import cn.vorbote.smartcampus.pos.Admin;
import cn.vorbote.smartcampus.services.IAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartCampusApplicationTests {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private SnowFlake snowFlake;

    @Test
    void contextLoads() {

        var admin = new Admin()
                .setId(String.valueOf(snowFlake.nextId()))
                .setName("zhangsan")
                .setPassword(HashUtil.encrypt(Hash.MD5, "zhangsan"))
                .setGender(Gender.MALE.getCode())
                .setAvatar("/avatars/default.png")
                .setTelephone("18570642918")
                .setEmail("zhangsan@example.org")
                .setAddress("湖南省长沙市天心区");
        adminService.save(admin);

    }

}
