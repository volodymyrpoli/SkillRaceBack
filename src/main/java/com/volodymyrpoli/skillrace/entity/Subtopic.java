package com.volodymyrpoli.skillrace.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Subtopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    private Topic topic;
    @ManyToOne
    private Level level;

}
