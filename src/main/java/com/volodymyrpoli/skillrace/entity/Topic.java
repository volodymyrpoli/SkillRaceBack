package com.volodymyrpoli.skillrace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Topic extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    private Domain domain;
    @OneToMany(mappedBy = "topic")
    private List<Subtopic> subtopics;

}
