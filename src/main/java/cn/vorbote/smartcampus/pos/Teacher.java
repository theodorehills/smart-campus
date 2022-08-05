package cn.vorbote.smartcampus.pos;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author vorbote
 * @since 2022-08-05
 */
@TableName("sc_teacher")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public final class Teacher extends BasePo implements Serializable {

    @TableId
    private String id;

    private String teacherNumber;

    private String name;

    private Integer gender;

    private String password;

    private String email;

    private String telephone;

    private String address;

    private String avatar;

    private String klasseName;

}
