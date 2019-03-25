package com.volodymyrpoli.skillrace.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer rank;
    @OneToMany(mappedBy = "domain")
    private List<Topic> topics;

}
