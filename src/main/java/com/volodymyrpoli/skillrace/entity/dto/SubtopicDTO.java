package com.volodymyrpoli.skillrace.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubtopicDTO extends BaseEntityDTO {

    private Integer levelId;
    private Integer topicId;

}
