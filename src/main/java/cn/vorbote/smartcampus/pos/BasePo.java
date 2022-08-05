package cn.vorbote.smartcampus.pos;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

/**
 * BasePo<br>
 * Created at 8/5/2022 3:42 PM
 *
 * @author theod
 */
@Getter
@Setter
public sealed abstract class BasePo permits Admin, Grade, Klasse, Student, Teacher {

    protected String createBy;

    protected Long createAt;

    protected String updateBy;

    protected Long updateAt;

    protected Integer archived;

}
