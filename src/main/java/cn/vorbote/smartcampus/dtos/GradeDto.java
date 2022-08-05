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
public final class GradeDto extends BaseDto implements Serializable {

    private String id;

    private String name;

    private String manager;

    private String email;

    private String telephone;

    private String introduction;

}
