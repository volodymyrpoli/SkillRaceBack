package com.volodymyrpoli.skillrace.entity;

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
    @ManyToOne
    private Domain domain;
    @OneToMany(mappedBy = "topic")
    private List<Subtopic> subtopics;

}
