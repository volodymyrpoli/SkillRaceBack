package com.volodymyrpoli.skillrace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.volodymyrpoli.skillrace.security.model.Authority;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authority> authorities;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "status",
            joinColumns = {@JoinColumn(name = "application_user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "subtopic_id", referencedColumnName = "id")}
    )
    private List<Subtopic> doneSubtopics;

}
