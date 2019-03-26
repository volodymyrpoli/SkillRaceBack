package com.volodymyrpoli.skillrace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Level extends BaseEntity {

    private Integer rank;
    private String color;
    @JsonIgnore
    @OneToMany(mappedBy = "level")
    private List<Subtopic> subtopics;

}
