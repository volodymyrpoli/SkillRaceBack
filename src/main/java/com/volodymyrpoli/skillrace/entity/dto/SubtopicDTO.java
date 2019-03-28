package com.volodymyrpoli.skillrace.entity.dto;

import com.volodymyrpoli.skillrace.entity.Attachment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubtopicDTO extends BaseEntityDTO {

    private Integer levelId;
    private Integer topicId;
    private List<Attachment> attachments;

}
