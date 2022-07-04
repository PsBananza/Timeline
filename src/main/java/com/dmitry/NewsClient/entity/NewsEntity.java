package com.dmitry.NewsClient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "task")
@Accessors(chain = true)
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String image;
    private String title;
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JsonIgnore
    private UserEntity user;
    @OneToMany(mappedBy = "entity")
    @JsonIgnore
    private List<Tag> tags;
}
