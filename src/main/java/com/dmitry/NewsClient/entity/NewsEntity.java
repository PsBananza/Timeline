package com.dmitry.NewsClient.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Entity
@Setter
@Getter
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private UserEntity user;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tag> tags;

}
