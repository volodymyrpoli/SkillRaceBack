package com.volodymyrpoli.skillrace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "attachment")
@Data
public class Attachment extends BaseEntity {

    @Column(name = "url", length = 256)
    @NotNull
    private String url;
    @JsonIgnore
    @ManyToOne
    private Subtopic subtopic;

}
