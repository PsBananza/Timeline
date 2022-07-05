package com.dmitry.NewsClient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

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
    private String username;
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JsonIgnore
    private UserEntity user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tag> tags;
}
