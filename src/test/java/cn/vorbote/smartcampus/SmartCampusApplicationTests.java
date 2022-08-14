package cn.vorbote.smartcampus;

import cn.vorbote.core.constants.Hash;
import cn.vorbote.core.utils.HashUtil;
import cn.vorbote.core.utils.SnowFlake;
import cn.vorbote.smartcampus.enums.Gender;
import cn.vorbote.smartcampus.pos.*;
import cn.vorbote.smartcampus.services.*;
import cn.vorbote.smartcampus.utils.GuidGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private IStudentService studentService;

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
        // var klassen = Arrays.asList(
        //         new Klasse()
        //                 .setId(guidGenerator.nextKlasseId())
        //                 .setName("2018软件工程一班")
        //                 .setNumber(30)
        //                 .setIntroduction("这是2018级软件工程一班")
        //                 .setHeadmaster("TEC24409474210988037")
        //                 .setGradeId("GRA24402620042969088"),
        //         new Klasse()
        //                 .setId(guidGenerator.nextKlasseId())
        //                 .setName("2018软件工程二班")
        //                 .setNumber(30)
        //                 .setIntroduction("这是2018级软件工程二班")
        //                 .setHeadmaster("TEC24409474210988037")
        //                 .setGradeId("GRA24402620042969088"),
        //         new Klasse()
        //                 .setId(guidGenerator.nextKlasseId())
        //                 .setName("2019软件工程一班")
        //                 .setNumber(32)
        //                 .setIntroduction("这是2019级软件工程一班")
        //                 .setHeadmaster("TEC24409474210988036")
        //                 .setGradeId("GRA24402620047163392"),
        //         new Klasse()
        //                 .setId(guidGenerator.nextKlasseId())
        //                 .setName("2019软件工程二班")
        //                 .setNumber(34)
        //                 .setIntroduction("这是2019级软件工程二班")
        //                 .setHeadmaster("TEC24409474210988036")
        //                 .setGradeId("GRA24402620047163392")
        // );
        // klasseService.saveBatch(klassen);
        // endregion

        // region 学生生成
        // var students = Arrays.asList(
        //         new Student()
        //                 .setId(guidGenerator.nextStudentId())
        //                 .setStudentNumber("201808010001")
        //                 .setUsername("yanjipeng")
        //                 .setName("颜纪鹏")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "yanjipeng"))
        //                 .setEmail("yanjipeng@example.org")
        //                 .setTelephone("19322518618")
        //                 .setAddress("云南省曲靖市沾益县温泉路664号")
        //                 .setIntroduction("")
        //                 .setAvatar(DEFAULT_AVATAR)
        //                 .setKlasseId("CLS24411361383546880"),
        //         new Student()
        //                 .setId(guidGenerator.nextStudentId())
        //                 .setStudentNumber("201808010002")
        //                 .setUsername("wanglingyong")
        //                 .setName("汪林勇")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "wanglingyong"))
        //                 .setEmail("wanglingyong@example.org")
        //                 .setTelephone("18142828092")
        //                 .setAddress("安徽省滁州市凤阳县华夏路719号")
        //                 .setIntroduction("")
        //                 .setAvatar(DEFAULT_AVATAR)
        //                 .setKlasseId("CLS24411361383546880"),
        //         new Student()
        //                 .setId(guidGenerator.nextStudentId())
        //                 .setStudentNumber("201808020001")
        //                 .setUsername("renjianfei")
        //                 .setName("任建飞")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "renjianfei"))
        //                 .setEmail("renjianfei@example.org")
        //                 .setTelephone("15658172591")
        //                 .setAddress("陕西省咸阳市泾阳县银泰路376号")
        //                 .setIntroduction("")
        //                 .setAvatar(DEFAULT_AVATAR)
        //                 .setKlasseId("CLS24411361383546881"),
        //         new Student()
        //                 .setId(guidGenerator.nextStudentId())
        //                 .setStudentNumber("201808020002")
        //                 .setUsername("luguosong")
        //                 .setName("卢国松")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "luguosong"))
        //                 .setEmail("luguosong@example.org")
        //                 .setTelephone("19397492689")
        //                 .setAddress("福建省福州市永泰县西小路951号")
        //                 .setIntroduction("")
        //                 .setAvatar(DEFAULT_AVATAR)
        //                 .setKlasseId("CLS24411361383546881"),
        //         new Student()
        //                 .setId(guidGenerator.nextStudentId())
        //                 .setStudentNumber("201908010001")
        //                 .setUsername("chenwanhong")
        //                 .setName("陈万洪")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "chenwanhong"))
        //                 .setEmail("chenwanhong@example.org")
        //                 .setTelephone("17548300445")
        //                 .setAddress("福建省福州市闽清县光明路643号")
        //                 .setIntroduction("")
        //                 .setAvatar(DEFAULT_AVATAR)
        //                 .setKlasseId("CLS24411361383546882"),
        //         new Student()
        //                 .setId(guidGenerator.nextStudentId())
        //                 .setStudentNumber("201908010002")
        //                 .setUsername("weiyafang")
        //                 .setName("魏雅芳")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "weiyafang"))
        //                 .setEmail("weiyafang@example.org")
        //                 .setTelephone("16615784817")
        //                 .setAddress("浙江省衢州市开化县文林路199号")
        //                 .setIntroduction("")
        //                 .setAvatar(DEFAULT_AVATAR)
        //                 .setKlasseId("CLS24411361383546882"),
        //         new Student()
        //                 .setId(guidGenerator.nextStudentId())
        //                 .setStudentNumber("201908020001")
        //                 .setUsername("zhengxingguo")
        //                 .setName("郑行国")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "zhengxingguo"))
        //                 .setEmail("zhengxingguo@example.org")
        //                 .setTelephone("13483972624")
        //                 .setAddress("湖北省荆门市京山县")
        //                 .setIntroduction("")
        //                 .setAvatar(DEFAULT_AVATAR)
        //                 .setKlasseId("CLS24411361383546883"),
        //         new Student()
        //                 .setId(guidGenerator.nextStudentId())
        //                 .setStudentNumber("201908020002")
        //                 .setUsername("zhengtianji")
        //                 .setName("郑天基")
        //                 .setGender(Gender.MALE.getCode())
        //                 .setPassword(HashUtil.encrypt(Hash.MD5, "zhengtianji"))
        //                 .setEmail("zhengtianji@example.org")
        //                 .setTelephone("13246206597")
        //                 .setAddress("安徽省淮南市凤台县")
        //                 .setIntroduction("")
        //                 .setAvatar(DEFAULT_AVATAR)
        //                 .setKlasseId("CLS24411361383546883")
        // );
        // studentService.saveBatch(students);
        // endregion

        gradeService.isGradeExistById("GRA24402620042969088");

    }

}
