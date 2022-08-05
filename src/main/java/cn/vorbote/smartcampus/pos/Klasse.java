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
@TableName("sc_klasse")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public final class Klasse extends BasePo implements Serializable {

    @TableId
    private String id;

    private String name;

    private Integer number;

    private String introduction;

    private String headmaster;

    private String email;

    private String telephone;

    private String gradeName;

}
