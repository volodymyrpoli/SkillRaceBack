package com.volodymyrpoli.skillrace.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Domain extends BaseEntity {

    private Integer rank;
    @OneToMany(mappedBy = "domain")
    private List<Topic> topics;



}
