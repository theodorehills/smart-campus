package cn.vorbote.smartcampus.vos;

import cn.vorbote.smartcampus.pos.BasePo;
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
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public final class StudentVo extends BaseVo implements Serializable {



    private String studentNumber;

    private String name;

    private Integer gender;

    private String email;

    private String telephone;

    private String address;

    private String introduction;

    private String avatar;

    private String klasseName;

}
