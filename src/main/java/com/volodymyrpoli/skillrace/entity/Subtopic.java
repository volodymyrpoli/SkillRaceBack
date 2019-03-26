package com.volodymyrpoli.skillrace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Subtopic extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    private Topic topic;
    @ManyToOne
    private Level level;

}
