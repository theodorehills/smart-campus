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
public final class KlasseDto extends BaseDto implements Serializable {

    private String id;

    private String name;

    private Integer number;

    private String introduction;

    private String headmaster;

    private String gradeId;

}
