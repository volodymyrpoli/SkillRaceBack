package com.volodymyrpoli.skillrace.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LevelDTO extends BaseEntityDTO {

    private Integer rank;
    private String color;

}
