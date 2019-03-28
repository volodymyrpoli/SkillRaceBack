package com.volodymyrpoli.skillrace.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AttachmentDTO extends BaseEntityDTO {

    private String url;
    private Integer subtopicId;

}
