package cn.vorbote.smartcampus;

import cn.vorbote.core.constants.Hash;
import cn.vorbote.core.utils.HashUtil;
import cn.vorbote.core.utils.SnowFlake;
import cn.vorbote.smartcampus.enums.Gender;
import cn.vorbote.smartcampus.pos.Admin;
import cn.vorbote.smartcampus.pos.Grade;
import cn.vorbote.smartcampus.pos.Klasse;
import cn.vorbote.smartcampus.pos.Teacher;
import cn.vorbote.smartcampus.services.IAdminService;
import cn.vorbote.smartcampus.services.IGradeService;
import cn.vorbote.smartcampus.services.IKlasseService;
import cn.vorbote.smartcampus.services.ITeacherService;
import cn.vorbote.smartcampus.utils.GuidGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class SmartCampusApplicationTests {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IKlasseService klasseService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IGradeService gradeService;

    @Autowired
    private GuidGenerator guidGenerator;

    private static final String DEFAULT_AVATAR = "/images/default-avatar.png";

    @Test
    void contextLoads() {
        // region 教师生成
        // var teachers = Arrays.asList(
        //         new Teacher()
        //                 .setId(guidGenerator.nextTeacherId())
        //                 .setTeacherNumber("1307288328")
        //                 .setUsername("yezhizhi")
        //                 .setName("叶治治")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "yezhizhi"))
        //                 .setEmail("yezhizhi@example.org")
        //                 .setTelephone("17482568328")
        //                 .setAddress("河北省张家口市怀安县")
        //                 .setAvatar(DEFAULT_AVATAR),
        //         new Teacher()
        //                 .setId(guidGenerator.nextTeacherId())
        //                 .setTeacherNumber("4452223062")
        //                 .setUsername("zhoukaixia")
        //                 .setName("周开霞")
        //                 .setGender(Gender.FEMALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "zhoukaixia"))
        //                 .setEmail("zhoukaixia@example.org")
        //                 .setTelephone("17417993062")
        //                 .setAddress("广东省揭阳市揭西区")
        //                 .setAvatar(DEFAULT_AVATAR),
        //         new Teacher()
        //                 .setId(guidGenerator.nextTeacherId())
        //                 .setTeacherNumber("3506252777")
        //                 .setUsername("dongzijun")
        //                 .setName("董紫君")
        //                 .setGender(Gender.FEMALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "dongzijun"))
        //                 .setEmail("dongzijun@example.org")
        //                 .setTelephone("13915172777")
        //                 .setAddress("福建省漳州市长泰县中心路531号")
        //                 .setAvatar(DEFAULT_AVATAR),
        //         new Teacher()
        //                 .setId(guidGenerator.nextTeacherId())
        //                 .setTeacherNumber("3055764262")
        //                 .setUsername("lishuwei")
        //                 .setName("李书伟")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "lishuwei"))
        //                 .setEmail("lishuwei@example.org")
        //                 .setTelephone("16486924264")
        //                 .setAddress("湖南省岳阳市湘阴县红艳路445号")
        //                 .setAvatar(DEFAULT_AVATAR),
        //         new Teacher()
        //                 .setId(guidGenerator.nextTeacherId())
        //                 .setTeacherNumber("3425736412")
        //                 .setUsername("chenchunshu")
        //                 .setName("陈春树")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "chenchunshu"))
        //                 .setEmail("chenchunshu@example.org")
        //                 .setTelephone("13366666412")
        //                 .setAddress("山西省太原市阳曲县北环路867号")
        //                 .setAvatar(DEFAULT_AVATAR),
        //         new Teacher()
        //                 .setId(guidGenerator.nextTeacherId())
        //                 .setTeacherNumber("5961522861")
        //                 .setUsername("limuyuan")
        //                 .setName("李牧原")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "limuyuan"))
        //                 .setEmail("limuyuan@example.org")
        //                 .setTelephone("14113392861")
        //                 .setAddress("湖北省宜昌市秭归县解放路571号")
        //                 .setAvatar(DEFAULT_AVATAR),
        //         new Teacher()
        //                 .setId(guidGenerator.nextTeacherId())
        //                 .setTeacherNumber("3829649110")
        //                 .setUsername("guoxiudan")
        //                 .setName("郭秀丹")
        //                 .setGender(Gender.FEMALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "guoxiudan"))
        //                 .setEmail("guoxiudan@example.org")
        //                 .setTelephone("17123649110")
        //                 .setAddress("河南省南阳市桐柏县健康路592号")
        //                 .setAvatar(DEFAULT_AVATAR)
        // );
        // teacherService.saveBatch(teachers);
        // endregion

        // region 年级生成
        // var grades = Arrays.asList(
        //         new Grade()
        //                 .setId("GRA" + snowFlake.nextId())
        //                 .setName("2018级")
        //                 .setIntroduction("这是帅气阳光的2018级")
        //                 .setManager("TEC24401716501807104"),
        //         new Grade()
        //                 .setId("GRA" + snowFlake.nextId())
        //                 .setName("2019级")
        //                 .setIntroduction("这是倒霉催的2019级")
        //                 .setManager("TEC24401716556333056"),
        //         new Grade()
        //                 .setId("GRA" + snowFlake.nextId())
        //                 .setName("2020级")
        //                 .setIntroduction("这是虽然倒霉但是没有2019级倒霉的2020级")
        //                 .setManager("TEC24401716556333057"),
        //         new Grade()
        //                 .setId("GRA" + snowFlake.nextId())
        //                 .setName("2021级")
        //                 .setIntroduction("这是一群小萌新")
        //                 .setManager("TEC24401716556333058"),
        //         new Grade()
        //                 .setId("GRA" + snowFlake.nextId())
        //                 .setName("2022级")
        //                 .setIntroduction("这是还在等待录取通知书的2022级")
        //                 .setManager("TEC24401716556333059"),
        //         new Grade()
        //                 .setId("GRA" + snowFlake.nextId())
        //                 .setName("2023级")
        //                 .setIntroduction("这是还在准备高考的2023级")
        //                 .setManager("TEC24401716556333060")
        // );
        // gradeService.saveBatch(grades);
        // endregion

        // region 班级生成
        // Arrays.asList(
        //         new Klasse()
        //                 .setId("CLS" + snowFlake.nextId())
        //                 .setName("2018软件工程一班")
        //                 .setNumber(30)
        //                 .setIntroduction("这是2018级软件工程一班")
        //                 .setHeadmaster("")
        //                 .setGradeId("GRA24402620042969088")
        // );
        // endregion

    }

}
