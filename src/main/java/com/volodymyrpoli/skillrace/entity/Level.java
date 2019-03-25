package com.volodymyrpoli.skillrace.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer rank;
    private String color;
    @OneToMany(mappedBy = "level")
    private List<Subtopic> subtopics;

}
