package com.volodymyrpoli.skillrace.util;

import com.volodymyrpoli.skillrace.entity.BaseEntity;
import com.volodymyrpoli.skillrace.entity.dto.BaseEntityDTO;

import java.util.Objects;

public class Mapper {

    public static void mapBaseEntity(BaseEntity baseEntity, BaseEntityDTO baseEntityDTO) {
        if (Objects.nonNull(baseEntityDTO.getName())) {
            baseEntity.setName(baseEntityDTO.getName());
        }
    }

}
