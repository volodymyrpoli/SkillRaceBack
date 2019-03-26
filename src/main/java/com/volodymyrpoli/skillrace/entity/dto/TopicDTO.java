package com.volodymyrpoli.skillrace.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TopicDTO extends BaseEntityDTO {

    private Integer domainId;

}
