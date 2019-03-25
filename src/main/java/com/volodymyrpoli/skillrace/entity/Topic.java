package com.volodymyrpoli.skillrace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonIgnore
    @ManyToOne
    private Domain domain;
    @OneToMany(mappedBy = "topic")
    private List<Subtopic> subtopics;

}
