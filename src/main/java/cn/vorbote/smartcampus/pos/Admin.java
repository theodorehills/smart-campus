package cn.vorbote.smartcampus.pos;

import cn.vorbote.simplejwt.annotations.JwtIgnore;
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
@TableName("sc_admin")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public final class Admin extends BasePo implements Serializable {

    @TableId
    private String id;

    private String name;

    private Integer gender;

    @JwtIgnore
    private String password;

    private String email;

    private String telephone;

    private String address;

    private String avatar;

}
