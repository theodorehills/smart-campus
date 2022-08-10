package cn.vorbote.smartcampus.dtos;

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
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public final class AdminDto extends BaseDto implements Serializable {

    private String id;

    private String username;

    private String name;

    private Integer gender;

    private String password;

    private String email;

    private String telephone;

    private String address;

    private String avatar;

}
