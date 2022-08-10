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
public final class GradeVo extends BaseVo implements Serializable {

    private String id;

    private String name;

    private String manager;

    private String introduction;

}
