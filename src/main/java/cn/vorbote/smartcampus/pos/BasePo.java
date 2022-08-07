package cn.vorbote.smartcampus.pos;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BasePo<br>
 * Created at 8/5/2022 3:42 PM
 *
 * @author theod
 */
@Getter
@Setter
@Accessors(chain = true)
public sealed abstract class BasePo permits Admin, Grade, Klasse, Student, Teacher {



}
